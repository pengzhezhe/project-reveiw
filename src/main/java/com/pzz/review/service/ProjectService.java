package com.pzz.review.service;

import com.pzz.review.ao.ProjectAO;
import com.pzz.review.ao.ProjectAddAO;
import com.pzz.review.ao.ReviewAO;
import com.pzz.review.dto.PageDTO;
import com.pzz.review.dto.ProjectDTO;
import com.pzz.review.dto.ProjectDetailDTO;

public interface ProjectService {
    int addProject(ProjectAddAO projectAddAO);

    boolean deleteProject(Integer projectId);

    boolean updateProject(ProjectAO projectAO);

    ProjectDTO getProject(Integer projectId);

    PageDTO<ProjectDTO> listProjects(int pageNum, int pageSize);

    PageDTO<ProjectDTO> listProjectsByStatus(int status, int pageNum, int pageSize);

    PageDTO<ProjectDTO> listProjectsByUserIdAndType(Integer userId, Integer type, int pageNum, int pageSize);

    ProjectDetailDTO getProjectDetail(Integer projectId);

    boolean reviewProject(ReviewAO reviewAO);

}
