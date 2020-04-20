package com.pzz.review.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDTO {
    private Integer id;

    private String username;

    private String name;

    private Integer sex;

    private String email;

    private Integer userType;

    private Timestamp createTime;
}
