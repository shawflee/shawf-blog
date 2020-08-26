package com.shawf.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.print.DocFlavor;
import javax.validation.constraints.NotBlank;

/**
 * @author shawf_lee
 * @date 2020/5/22 16:56
 * Content:
 */
@Entity
@Table(name = "lbg_type")
@Data
public class Type {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "分类名称不能为空")
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    private Integer createBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateTime;
    private Integer lastUpdateBy;

    @OneToMany(mappedBy = "type")
    private List<Blog> blogList=new ArrayList<>();


}
