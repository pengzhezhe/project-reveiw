package com.pzz.review.ao;

import lombok.Data;

@Data
public class RegisterAO {
    private String username;

    private String password;

    private String email;

    private String name;

    private Integer sex;
}
