package com.shawf.service;

import com.shawf.entity.Comment;

import java.util.List;

/**
 * @author shawf_lee
 * @date 2020/7/2 15:27
 * Content:
 */
public interface CommentService {

    List<Comment> listComentsByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
