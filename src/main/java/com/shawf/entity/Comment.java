package com.shawf.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * @author shawf_lee
 * @date 2020/5/25 15:54
 * Content:
 */
@Entity
@Table(name = "lbg_comment")
@Data
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private String nickName;
    private String email;
    private String content;
    private String headPhoto;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    private Integer createBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateTime;
    private Integer lastUpdateBy;

    @ManyToOne
    private Blog blog;
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> subCommentList=new ArrayList<>();
    @ManyToOne
    private Comment parentComment;

    private boolean adminFlag;
}
