package com.pzz.review.service.impl;

import com.pzz.review.domain.Project;
import com.pzz.review.domain.Review;
import com.pzz.review.dto.ProjectDetailDTO;
import com.pzz.review.mapper.ProjectMapper;
import com.pzz.review.service.ProjectService;
import com.pzz.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ReviewService reviewService;

    @Override
    public int addProject(Project project) {

        return projectMapper.insert(project);
    }

    @Override
    public boolean deleteProject(Integer projectId) {
        return projectMapper.delete(projectId) > 0;
    }

    @Override
    public boolean updateProject(Project project) {
        return projectMapper.update(project) > 0;
    }

    @Override
    public Project getProject(Integer projectId) {
        return projectMapper.getProject(projectId);
    }

    @Override
    public ProjectDetailDTO getProjectDetail(Integer projectId) {
        Project project = projectMapper.getProject(projectId);
        Review review = reviewService.getReview(BigInteger.valueOf(projectId));
        return new ProjectDetailDTO(project, review);
    }

    @Override
    public List<Project> listProjects() {
        return projectMapper.listProjects();
    }

    @Override
    public List<Project> listProjectsByUserId(Integer userId) {
        return projectMapper.listProjectsByUserId(userId);
    }

    @Override
    public List<Project> listProjectsByUserIdAndStatus(Integer userId, Integer status) {
        if (status == 0)
            return projectMapper.listProjectsByUserId(userId);
        else if (status == 1)
            return projectMapper.listProjectsByUserIdAndStatus(userId, 0);
        else if (status == 2)
            return projectMapper.listCheckedProjectsByUserId(userId);
        else
            return null;
    }
}
