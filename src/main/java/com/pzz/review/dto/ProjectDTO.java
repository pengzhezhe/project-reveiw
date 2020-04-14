package com.pzz.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
public class ProjectDTO {
    private Integer id;

    private String name;

    private String introduction;

    private String userName;

    private Integer userId;

    private Integer status;

    private Timestamp createTime;

    private Timestamp updateTime;
}
