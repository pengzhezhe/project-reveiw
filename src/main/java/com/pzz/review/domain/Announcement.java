package com.pzz.review.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Announcement {
    private Integer id;

    private String title;

    private String content;

    private Date createTime;

    private Date updateTime;

}