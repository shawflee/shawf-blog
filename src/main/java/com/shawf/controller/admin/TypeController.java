package com.shawf.controller.admin;

import com.shawf.entity.Type;
import com.shawf.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.print.DocFlavor;
import javax.validation.Valid;


/**
 * @author shawf_lee
 * @date 2020/5/29 15:42
 * Content:
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String listTypes(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC )
                                    Pageable pageable, Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";

    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String saveType(@Valid Type type, BindingResult result, RedirectAttributes redirectAttributes){
        Type type2=typeService.getTypeByName(type.getName());
        if(type2!=null){
            result.rejectValue("name","nameError","不能添加相同的分类");
        }
        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type type1=typeService.saveType(type);
        if(type1==null){
            redirectAttributes.addFlashAttribute("message","新增失败");
        }else {
            redirectAttributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types/{id}")
    public String saveType(@Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes redirectAttributes){
        Type type2=typeService.getTypeByName(type.getName());
        if(type2!=null){
            result.rejectValue("name","nameError","不能添加相同的分类");
        }
        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type type1=typeService.updateType(id,type);
        if(type1==null){
            redirectAttributes.addFlashAttribute("message","更新失败");
        }else {
            redirectAttributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
}
