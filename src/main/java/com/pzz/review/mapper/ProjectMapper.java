package com.pzz.review.mapper;

import com.pzz.review.domain.Project;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectMapper {
    int delete(Integer id);

    int insert(Project project);

    int update(Project project);

    Project getProject(Integer id);

    List<Project> listProjects();

    List<Project> listProjectsByUserId(Integer userId);

    List<Project> listCheckedProjectsByUserId(Integer userId);

    List<Project> listProjectsByStatus(Integer status);

    List<Project> listProjectsByUserIdAndStatus(Integer userId, Integer status);

}