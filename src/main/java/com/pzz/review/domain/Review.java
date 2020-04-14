package com.pzz.review.domain;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class Review {
    private Integer projectId;

    private Integer status;

    private String opinion;

    private Timestamp createTime;

    private Timestamp updateTime;
}
