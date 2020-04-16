package com.pzz.review.controller;

import com.pzz.review.domain.Announcement;
import com.pzz.review.dto.ProjectDTO;
import com.pzz.review.dto.UserDTO;
import com.pzz.review.service.AnnouncementService;
import com.pzz.review.service.ProjectService;
import com.pzz.review.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/admin")
    public String indexView() {
        return "admin/index";
    }

    @GetMapping("/admin/user")
    public String userView() {
        return "admin/user/index";
    }

    @GetMapping("/admin/user/add")
    public String userAddView() {
        return "admin/user/add";
    }

    @GetMapping("/admin/user/update/{id}")
    public String userUpdateView(@PathVariable("id") int userId, Model model) {
        UserDTO user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "admin/user/update";
    }

    @GetMapping("/admin/project")
    public String projectView() {
        return "admin/project/index";
    }

    @GetMapping("/admin/project/update/{id}")
    public String projectUpdateView(@PathVariable("id") int projectId, Model model) {
        ProjectDTO project = projectService.getProject(projectId);
        model.addAttribute("project", project);
        return "admin/project/update";
    }

    @GetMapping("/admin/announcement")
    public String announcementView() {
        return "admin/announcement/index";
    }

    @GetMapping("/admin/announcement/add")
    public String announcementAddView() {
        return "admin/announcement/add";
    }

    @GetMapping("/admin/announcement/update/{id}")
    public String announcementUpdateView(@PathVariable("id") int announcementId, Model model) {
        Announcement announcement = announcementService.getAnnouncementById(announcementId);
        model.addAttribute("announcement", announcement);
        return "admin/announcement/update";
    }
}
