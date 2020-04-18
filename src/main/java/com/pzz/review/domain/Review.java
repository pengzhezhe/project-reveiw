package com.pzz.review.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Review {
    private Integer projectId;

    private Integer status;

    private String opinion;

    private Timestamp reviewTime;

}
