package com.pzz.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class ProjectDTO {
    private Integer id;

    private String name;
    
    private String userName;

    private Integer userId;

    private Integer status;

    private Timestamp createTime;
}
