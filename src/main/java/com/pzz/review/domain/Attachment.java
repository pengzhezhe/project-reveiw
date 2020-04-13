package com.pzz.review.domain;

import lombok.Data;

import java.util.Date;

/**
 * 附件
 */
@Data
public class Attachment {
    private Integer id;

    private Integer projectId;

    private String filename;

    private String originalName;

    private Date createTime;

    private Date updateTime;
}