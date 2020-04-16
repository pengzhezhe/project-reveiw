package com.pzz.review.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzz.review.ao.ProjectAO;
import com.pzz.review.ao.ProjectAddAO;
import com.pzz.review.domain.Project;
import com.pzz.review.domain.Review;
import com.pzz.review.domain.User;
import com.pzz.review.dto.PageDTO;
import com.pzz.review.dto.ProjectDTO;
import com.pzz.review.dto.ProjectDetailDTO;
import com.pzz.review.exception.AppException;
import com.pzz.review.mapper.ProjectMapper;
import com.pzz.review.mapper.UserMapper;
import com.pzz.review.service.ProjectService;
import com.pzz.review.service.ReviewService;
import com.pzz.review.utils.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReviewService reviewService;

    @Override
    public int addProject(ProjectAddAO projectAddAO) {
        String username = projectAddAO.getUsername();
        User user = userMapper.getUserByUsername(username);
        Project project = new Project();
        BeanUtils.copyProperties(projectAddAO, project);
        project.setUserName(user.getName());
        project.setUserId(user.getId());
        project.setStatus(0);
        projectMapper.insert(project);
        return project.getId();
    }

    @Override
    public boolean deleteProject(Integer projectId) {
        return projectMapper.delete(projectId) > 0;
    }

    @Override
    public boolean updateProject(ProjectAO projectAO) {
        Project project = projectMapper.getProject(projectAO.getId());
        if (project == null)
            throw new AppException("项目不存在");
        project.setName(projectAO.getName());
        project.setIntroduction(projectAO.getIntroduction());
        return projectMapper.update(project) > 0;
    }

    @Override
    public ProjectDTO getProject(Integer projectId) {
        Project project = projectMapper.getProject(projectId);
        ProjectDTO projectDTO = new ProjectDTO();
        BeanUtils.copyProperties(project, projectDTO);
        return projectDTO;
    }

    @Override
    public ProjectDetailDTO getProjectDetail(Integer projectId) {
        Project project = projectMapper.getProject(projectId);
        Review review = reviewService.getReview(BigInteger.valueOf(projectId));
        return new ProjectDetailDTO(project, review);
    }

    @Override
    public PageDTO<ProjectDTO> listProjects(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Project> projects = projectMapper.listProjects();
        PageInfo pageInfo = new PageInfo(projects);
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        try {
            CommonUtils.copyListProperties(projects, projectDTOS, ProjectDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageDTO<>(projectDTOS, pageInfo.getTotal());
    }

    @Override
    public PageDTO<ProjectDTO> listProjectsByUserId(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Project> projects = projectMapper.listProjectsByUserId(userId);
        PageInfo pageInfo = new PageInfo(projects);
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        try {
            CommonUtils.copyListProperties(projects, projectDTOS, ProjectDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageDTO<>(projectDTOS, pageInfo.getTotal());
    }

    @Override
    public PageDTO<ProjectDTO> listProjectsByUserIdAndType(Integer userId, Integer type, int pageNum, int pageSize) {
        List<Project> projects;
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        PageHelper.startPage(pageNum, pageSize);
        if (type == 1) {
            projects = projectMapper.listProjectsByUserIdAndStatus(userId, 0);
        } else if (type == 2)
            projects = projectMapper.listCheckedProjectsByUserId(userId);
        else
            projects = projectMapper.listProjectsByUserId(userId);
        PageInfo pageInfo = new PageInfo(projects);
        try {
            CommonUtils.copyListProperties(projects, projectDTOS, ProjectDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageDTO<>(projectDTOS, pageInfo.getTotal());
    }
}
