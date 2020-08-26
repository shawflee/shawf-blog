package com.shawf.service.impl;

import com.shawf.dao.BlogDao;
import com.shawf.entity.Blog;
import com.shawf.entity.Type;
import com.shawf.exception.NotFoundException;
import com.shawf.pojo.BlogVO;
import com.shawf.service.BlogService;
import com.shawf.util.MarkdownUtil;
import com.shawf.util.MyBeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import javax.persistence.criteria.*;

/**
 * @author shawf_lee
 * @date 2020/6/8 9:48
 * Content:
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public Blog getBlog(Long id) {
        return blogDao.getOne(id);
    }

    @Transactional
    @Override
    public Blog getBlogHtml(Long id) {
        Blog blog = blogDao.findById(id).orElse(null);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        Blog blog1 = new Blog();
        BeanUtils.copyProperties(blog, blog1);
        String content = blog1.getContent();
        blog1.setContent(MarkdownUtil.markdownToHtmlExtensions(content));
        blogDao.updateViews(id);
        return blog1;
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogVO blog) {
        return blogDao.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    predicateList.add(criteriaBuilder.like(root.<String>get("title"),
                            "%" + blog.getTitle() + "%"));
                }
                if (blog.getTypeId() != null) {
                    predicateList.add(criteriaBuilder.equal(root.<Type>get("type").get("id"),
                            blog.getTypeId()));
                }
                if (blog.getRecommendFlag() != null && blog.getRecommendFlag()) {
                    predicateList.add(criteriaBuilder.equal(root.<Boolean>get("recommendFlag"),
                            blog.getRecommendFlag()));
                }
                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        }, pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogDao.findAll(pageable);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setLastUpdateTime(new Date());
        blog.setViews(0);
        return blogDao.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog blog1 = blogDao.findById(id).orElse(null);
        if (blog1 == null) {
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(blog, blog1, MyBeanUtil.getNullPropertyNames(blog));
        blog1.setLastUpdateTime(new Date());
        return blogDao.save(blog1);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteById(id);
    }

    @Override
    public List<Blog> listRecommendBlog(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "lastUpdateTime");
        Pageable pageable = PageRequest.of(0, size, sort);
        return blogDao.findRecommendBlog(pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, String query) {
        return blogDao.findByQuery(pageable, query);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, Long tagId) {
        return blogDao.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Join join = root.join("tagList");
                return criteriaBuilder.equal(join.get("id"), tagId);
            }
        }, pageable);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> yearList = blogDao.getYearList();
        Map<String, List<Blog>> blogMap = new HashMap<>();
        for (String year : yearList) {
            blogMap.put(year, blogDao.getByYear(year));
        }
        return blogMap;
    }

    @Override
    public Long countBlog() {
        return blogDao.count();
    }
}
