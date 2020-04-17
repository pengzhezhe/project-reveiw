package com.pzz.review.ao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewAO {
    private Integer projectId;

    private Integer status;

    private String opinion;

}
