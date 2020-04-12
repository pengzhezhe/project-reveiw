package com.pzz.review.service;

import com.pzz.review.domain.Project;
import com.pzz.review.dto.ProjectDetailDTO;
import com.pzz.review.dto.ResponseDTO;

import java.util.List;

public interface ProjectService {
    int addProject(Project project);

    boolean deleteProject(Integer projectId);

    boolean updateProject(Project project);

    Project getProject(Integer projectId);

    ProjectDetailDTO getProjectDetail(Integer projectId);

    List<Project> listProjects();

    List<Project> listProjectsByUserId(Integer userId);

    List<Project> listProjectsByUserIdAndStatus(Integer userId,Integer status);

}
