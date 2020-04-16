package com.pzz.review.ao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnnouncementAO {
    private Integer id;

    private String title;

    private String content;
}
