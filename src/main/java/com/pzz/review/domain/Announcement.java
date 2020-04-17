package com.pzz.review.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Announcement {
    private Integer id;

    private String title;

    private String content;

    private Timestamp createTime;

    private Timestamp updateTime;

}