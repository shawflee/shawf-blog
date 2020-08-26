package com.shawf.controller;



import com.shawf.service.BlogService;
import com.shawf.service.TagService;
import com.shawf.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author shawf_lee
 * @date 2019/12/27 15:45
 * Content:
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 8,sort = {"lastUpdateTime"},direction = Sort.Direction.DESC)
                        Pageable pageable, Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listType(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBlogList",blogService.listRecommendBlog(6));
        return "index";
    }
    @GetMapping("/blog")
    public String Blog(){
        return "blog";
    }

    @GetMapping("/search")
    public String search(@PageableDefault(size = 8,sort = {"lastUpdateTime"},direction = Sort.Direction.DESC)
                                Pageable pageable, @RequestParam String query, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,"%"+query+"%"));
        model.addAttribute("query",query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getBlogHtml(id));
        return "blog";
    }

    @GetMapping("/footer/newBlog")
    public String newBlogList(Model model){
        model.addAttribute("newBlogList",blogService.listRecommendBlog(3));
        return "_modes::newBlogList";
    }
}
