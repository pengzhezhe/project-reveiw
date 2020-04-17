package com.pzz.review.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Attachment {
    private Integer id;

    private Integer projectId;

    private String filename;

    private String originalName;

    private Timestamp createTime;

    private Timestamp updateTime;
}