package com.pzz.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class UserDTO {
    private Integer id;

    private String username;

    private String name;

    private Integer sex;

    private String email;

    private Timestamp createTime;
}
