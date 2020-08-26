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
 * @date 2020/8/19 15:08
 * Content:
 */
@Controller
public class msgBoardController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.headphoto}")
    private String headPhoto;

    @GetMapping("/msgboard")
    public String getMsgBoard(Model model){
        model.addAttribute("comments",commentService.listComentsByBlogId(-1L));
        return "msgboard";
    }

    //局部刷新
    @GetMapping("/refboard")
    public String refMsgBoard(Model model){
        model.addAttribute("comments",commentService.listComentsByBlogId(-1L));
        return "msgboard::commentList";
    }

    @PostMapping("/savemsgboard")
    public String saveMsgBoard(Comment comment, HttpSession session){
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
        return "redirect:/refboard";
    }
}
