package com.pzz.review.dto;

import com.pzz.review.domain.Project;
import com.pzz.review.domain.Review;
import lombok.Data;

import java.util.Date;

/**
 * 项目详情
 */
@Data
public class ProjectDetailDTO {
    private Integer id;

    private String name;

    private String introduction;

    private String userName;

    private Integer status;

    private String opinion;

    private Date createTime;

    private Date updateTime;

    private Date reviewTime;

    public ProjectDetailDTO(Project project, Review review) {
        this.id = project.getId();
        this.name = project.getName();
        this.introduction = project.getIntroduction();
        this.userName = project.getUserName();
        this.status = project.getStatus();
        this.createTime = project.getCreateTime();
        this.updateTime = project.getUpdateTime();
        this.opinion = review.getOpinion();
        this.reviewTime = review.getCreateTime();
    }
}
