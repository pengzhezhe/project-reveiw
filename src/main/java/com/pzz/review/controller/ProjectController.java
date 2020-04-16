package com.pzz.review.controller;

import com.pzz.review.ao.ProjectAO;
import com.pzz.review.ao.ProjectAddAO;
import com.pzz.review.dto.PageDTO;
import com.pzz.review.dto.ProjectDTO;
import com.pzz.review.dto.ResponseDTO;
import com.pzz.review.service.ProjectService;
import com.pzz.review.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @PostMapping("/project/add")
    @ResponseBody
    public ResponseDTO<Integer> addProject(@RequestBody Map<String, String> map, HttpSession httpSession) {
        String name = map.get("name");
        String introduction = map.get("introduction");
        String username = (String) httpSession.getAttribute("username");
        ProjectAddAO projectAddAO = new ProjectAddAO(name, introduction, username);
        int projectId = projectService.addProject(projectAddAO);
        return new ResponseDTO<>(0, "添加成功", projectId);
    }

    @GetMapping("/api/project")
    @ResponseBody
    public ResponseDTO<PageDTO<ProjectDTO>> listProject(@RequestParam(defaultValue = "1", name = "page") int pageNum, @RequestParam(defaultValue = "10", name = "limit") int pageSize) {
        PageDTO<ProjectDTO> pageDTO = projectService.listProjects(pageNum, pageSize);
        return new ResponseDTO<>(0, "Success", pageDTO);
    }

    @PutMapping("/api/project")
    @ResponseBody
    public ResponseDTO<String> updateProject(@RequestBody ProjectAO projectAO){
        if(projectService.updateProject(projectAO))
            return new ResponseDTO<>(0,"修改成功",null);
        else
            return new ResponseDTO<>(1,"修改失败",null);
    }

    @DeleteMapping("/api/project/{id}")
    @ResponseBody
    public ResponseDTO<String> deleteProject(@PathVariable("id") int projectId){
        if(projectService.deleteProject(projectId))
            return new ResponseDTO<>(0, "删除成功", null);
        else
            return new ResponseDTO<>(1, "删除失败", null);
    }
}
