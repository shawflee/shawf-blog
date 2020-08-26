package com.shawf.dao;

import com.shawf.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author shawf_lee
 * @date 2020/6/4 17:14
 * Content:
 */
public interface BlogDao extends JpaRepository<Blog,Long>,JpaSpecificationExecutor<Blog>{

    @Query("select t from Blog t where t.recommendFlag=true")
    List<Blog> findRecommendBlog(Pageable pageable);

    @Query("select t from Blog t where t.title like ?1 or t.content like ?1 or t.description like ?1")
    Page<Blog> findByQuery(Pageable pageable,String query);

    @Transactional
    @Modifying
    @Query("update Blog t set t.views=t.views+1 where t.id = ?1")
    int updateViews(Long id);

    @Query("select function('date_format',t.lastUpdateTime,'%Y') as yearList from Blog t " +
            "group by function('date_format',t.lastUpdateTime,'%Y') order by yearList desc")
    List<String> getYearList();

    @Query("select t from Blog t where function('date_format',t.lastUpdateTime,'%Y') = ?1 order by t.lastUpdateTime desc" )
    List<Blog> getByYear(String year);
}
