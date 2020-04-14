package com.pzz.review.domain;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class Project {
    private Integer id;

    private String name;

    private String introduction;

    private String userName;

    private Integer userId;

    private Integer status;

    private Timestamp createTime;

    private Timestamp updateTime;
}