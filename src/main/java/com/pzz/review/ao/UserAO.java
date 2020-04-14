package com.pzz.review.ao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAO {

    private String username;

    private String email;

    private String name;

    private Integer sex;
}
