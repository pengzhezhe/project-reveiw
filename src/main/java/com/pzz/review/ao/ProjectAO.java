package com.pzz.review.ao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectAO {
    private Integer id;

    private String name;

    private String introduction;

    private Integer userId;
}
