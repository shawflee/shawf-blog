package com.shawf.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * @author shawf_lee
 * @date 2020/5/25 16:43
 * Content:
 */
@Entity
@Table(name = "lbg_user")
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String nickName;
    private String userName;
    private String password;
    private String email;
    private String headPhoto;
    private Integer type;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    private Integer createBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateTime;
    private Integer lastUpdateBy;

    @OneToMany(mappedBy = "user")
    private List<Blog> blogList=new ArrayList<>();
}
