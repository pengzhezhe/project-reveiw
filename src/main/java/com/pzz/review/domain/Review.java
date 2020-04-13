package com.pzz.review.domain;

import lombok.Data;

import java.util.Date;

/**
 * 评审信息
 */
@Data
public class Review {
    private Integer projectId;

    private Integer status;

    private String opinion;

    private Date createTime;

    private Date updateTime;
}
