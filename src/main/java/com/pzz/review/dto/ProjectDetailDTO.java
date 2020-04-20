package com.pzz.review.dto;

import com.pzz.review.domain.Project;
import com.pzz.review.domain.Review;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProjectDetailDTO {
    private Integer id;

    private String name;

    private String introduction;

    private String userName;

    private Integer status;

    private Integer reviewStatus;

    private String opinion;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Timestamp reviewTime;

    public ProjectDetailDTO(Project project, Review review) {
        this.id = project.getId();
        this.name = project.getName();
        this.introduction = project.getIntroduction();
        this.userName = project.getUserName();
        this.status = project.getStatus();
        this.createTime = project.getCreateTime();
        this.updateTime = project.getUpdateTime();
        this.reviewStatus = review.getStatus();
        this.opinion = review.getOpinion();
        this.reviewTime = review.getReviewTime();
    }
}
