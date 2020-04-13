package com.pzz.review.domain;

import lombok.Data;

import java.util.Date;

/**
 * 项目
 */
@Data
public class Project {
    private Integer id;

    private String name;

    private String introduction;

    private String userName;

    private Integer userId;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}