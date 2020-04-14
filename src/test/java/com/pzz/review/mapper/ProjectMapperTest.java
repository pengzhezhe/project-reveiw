package com.pzz.review.mapper;

import com.pzz.review.domain.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProjectMapperTest {
    @Autowired
    private ProjectMapper projectMapper;

    @Test
    public void listProjects() {
        List<Project> projects = projectMapper.listProjects();
        System.out.println(projects);
    }

    @Test
    public void listProjectsByUserId() {
        List<Project> projects = projectMapper.listProjectsByUserId(100);
        System.out.println(projects);
    }

    @Test
    public void listProjectsByStatus() {
        List<Project> projects = projectMapper.listProjectsByStatus(0);
        System.out.println(projects);
    }

    @Test
    public void listProjectsByUserIdAndStatus() {
    }
}