package com.shawf.dao;

import com.shawf.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author shawf_lee
 * @date 2020/7/2 15:29
 * Content:
 */
public interface CommentDao extends JpaRepository<Comment,Long> {

    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);

}
