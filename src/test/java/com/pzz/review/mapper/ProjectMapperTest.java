package com.pzz.review.mapper;

import com.pzz.review.ao.ProjectAddAO;
import com.pzz.review.domain.Project;
import com.pzz.review.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProjectMapperTest {
    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private UserMapper userMapper;

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

    @Test
    @Rollback
    public void add() {
        ProjectAddAO projectAddAO = new ProjectAddAO("Test add", "test", "pengzhezhe");
        User user = userMapper.getUserByUsername(projectAddAO.getUsername());
        Project project = new Project();
        BeanUtils.copyProperties(projectAddAO, project);
        project.setUserName(user.getName());
        project.setUserId(user.getId());
        project.setStatus(0);
        projectMapper.insert(project);
        System.out.println(project);
    }

    @Test
    public void update() {

    }
}