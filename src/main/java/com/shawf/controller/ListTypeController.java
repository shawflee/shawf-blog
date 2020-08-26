package com.shawf.controller;

import com.shawf.entity.Type;
import com.shawf.pojo.BlogVO;
import com.shawf.service.BlogService;
import com.shawf.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author shawf_lee
 * @date 2020/7/9 16:11
 * Content:
 */
@Controller
public class ListTypeController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/type/{id}")
    public String type(@PageableDefault(size = 8,sort = {"lastUpdateTime"},direction = Sort.Direction.DESC)
                               Pageable pageable, @PathVariable Long id, Model model){
        List<Type> typeList=typeService.listType(1000);
        if(id==-1){
            id=typeList.get(0).getId();
        }
        BlogVO blogVO=new BlogVO();
        blogVO.setTypeId(id);
        model.addAttribute("types",typeList);
        model.addAttribute("page",blogService.listBlog(pageable,blogVO));
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}
