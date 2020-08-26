package com.shawf.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * @author shawf_lee
 * @date 2020/5/22 17:15
 * Content:
 */
@Entity
@Table(name = "lbg_tag")
@Data
public class Tag {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    private Integer createBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateTime;
    private Integer lastUpdateBy;

    @ManyToMany(mappedBy = "tagList")
    private List<Blog> blogList=new ArrayList<>();
}
