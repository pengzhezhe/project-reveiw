package com.pzz.review.controller;

import com.pzz.review.ao.ProjectAddAO;
import com.pzz.review.domain.Announcement;
import com.pzz.review.dto.PageDTO;
import com.pzz.review.dto.ProjectDTO;
import com.pzz.review.dto.ResponseDTO;
import com.pzz.review.service.AnnouncementService;
import com.pzz.review.service.AttachmentService;
import com.pzz.review.service.ProjectService;
import com.pzz.review.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private UserService userService;




    /**
     * 添加项目
     *
     * @param map         项目信息
     * @param httpSession Session
     * @return response
     */
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
}
