package com.shawf.controller;

import com.shawf.entity.Tag;
import com.shawf.pojo.BlogVO;
import com.shawf.service.BlogService;
import com.shawf.service.TagService;
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
 * @date 2020/7/14 15:52
 * Content:
 */
@Controller
public class ListTagController {
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/tag/{id}")
    public String tag(@PageableDefault(size = 8,sort = {"lastUpdateTime"},direction = Sort.Direction.DESC)
                               Pageable pageable, @PathVariable Long id, Model model){
        List<Tag> tagList=tagService.listTagTop(1000);
        if(id==-1){
            id=tagList.get(0).getId();
        }
        model.addAttribute("tags",tagList);
        model.addAttribute("page",blogService.listBlog(pageable,id));
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}
