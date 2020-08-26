package com.shawf.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * @author shawf_lee
 * @date 2020/5/22 15:57
 * Content:
 */
@Entity
@Table(name = "lbg_blog")
@Data
public class Blog {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private String picture;
    private String description;
    /**
     *标记
     */
    private String flag;
    /**
     *浏览次数
     */
    private Integer views;
    /**
     * 赞赏开启
     */
    private Boolean appreciateFlag;
    /**
     * 版权是否开启
     */
    private Boolean shareFlag;
    /**
     * 评论是否开启
     */
    private Boolean commentFlag;
    /**
     * 是否发布
     */
    private Boolean publishFlag;
    /**
     * 是否推荐
     */
    private Boolean recommendFlag;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    private Integer createBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateTime;
    private Integer lastUpdateBy;

    @Transient
    private String tagIds;

    @ManyToOne
    private Type type;
    @ManyToMany
    private List<Tag> tagList=new ArrayList<>();
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "blog")
    private List<Comment> commentList=new ArrayList<>();

    public void init(){
        this.tagIds=tagsToIds(this.getTagList());
    }

    private String tagsToIds(List<Tag> tagList){
        if(!tagList.isEmpty()){
            StringBuffer tagIds=new StringBuffer();
            boolean flag=false;
            for(Tag tag:tagList){
                if(flag){
                    tagIds.append(",");
                }else {
                    flag=true;
                }
                tagIds.append(tag.getId());
            }
            return tagIds.toString();
        }else {
            return tagIds;
        }
    }
}
