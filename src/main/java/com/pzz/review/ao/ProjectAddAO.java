package com.pzz.review.ao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectAddAO {
    private String name;

    private String introduction;

    private String username;
}
