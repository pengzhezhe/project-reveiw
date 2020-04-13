package com.pzz.review.domain;

import lombok.Data;

import java.util.Date;

/**
 * 用户信息
 */
@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String name;

    private Integer sex;

    private String email;

    private Integer userType;

    private Date createTime;

    private Date updateTime;
}