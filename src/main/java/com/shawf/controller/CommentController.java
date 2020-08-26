package com.shawf.controller;

import com.shawf.entity.Comment;
import com.shawf.entity.User;
import com.shawf.service.BlogService;
import com.shawf.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author shawf_lee
 * @date 2020/7/2 15:22
 * Content:
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @Value("${comment.headphoto}")
    private String headPhoto;

    @GetMapping("/comments/{blogId}")
    public String getComments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.listComentsByBlogId(blogId));
        return "blog::commentList";
    }

    @PostMapping("/comments")
    public String saveComment(Comment comment, HttpSession session){
        Long blogId=comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        User user=(User)session.getAttribute("user");
        if(user!=null){
            comment.setHeadPhoto(user.getHeadPhoto());
            comment.setAdminFlag(true);

        }else {
            comment.setHeadPhoto(headPhoto);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/"+comment.getBlog().getId();
    }
}
