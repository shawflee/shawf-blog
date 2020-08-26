package com.shawf.service;

import com.shawf.entity.Blog;
import com.shawf.pojo.BlogVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author shawf_lee
 * @date 2020/6/8 9:45
 * Content:
 */
public interface BlogService {

    Blog getBlog(Long id);

    Blog getBlogHtml(Long id);

    Page<Blog> listBlog(Pageable pageable,BlogVO blog);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Pageable pageable,String query);

    Page<Blog> listBlog(Pageable pageable,Long tagId);

    List<Blog> listRecommendBlog(Integer size);

    Map<String,List<Blog>> archiveBlog();

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);

    Long countBlog();
}
