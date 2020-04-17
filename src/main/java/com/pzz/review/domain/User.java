package com.pzz.review.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String name;

    private Integer sex;

    private String email;

    private Integer userType;

    private Timestamp createTime;

    private Timestamp updateTime;
}