package com.pzz.review.controller;

import com.pzz.review.ao.ReviewAO;
import com.pzz.review.domain.Announcement;
import com.pzz.review.domain.Attachment;
import com.pzz.review.dto.*;
import com.pzz.review.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/admin")
    public String indexView() {
        return "admin/index";
    }

    @GetMapping("/admin/update")
    public String updateView(HttpSession httpSession, Model model) {
        String username = (String) httpSession.getAttribute("username");
        UserDTO user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "admin/update";
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

    @GetMapping("/admin/review")
    public String projectReviewIndexView() {
        return "admin/project/reviewIndex";
    }

    @GetMapping("/admin/project/update/{id}")
    public String projectUpdateView(@PathVariable("id") int projectId, Model model) {
        ProjectDTO project = projectService.getProject(projectId);
        model.addAttribute("project", project);
        return "admin/project/update";
    }

    @GetMapping("/admin/project/review/{id}")
    public String projectReview(@PathVariable("id") Integer projectId, Model model) {
        ProjectDTO project = projectService.getProject(projectId);
        List<Attachment> attachments = attachmentService.listAttachment(projectId);
        model.addAttribute("attachments", attachments);
        model.addAttribute("project", project);
        return "admin/project/review";
    }

    @PostMapping("/admin/project/review")
    @ResponseBody
    public ResponseDTO<String> addReview(@RequestBody ReviewAO reviewAO) {
        if (projectService.reviewProject(reviewAO)) {
            return new ResponseDTO<>(0, "评审成功", null);
        } else
            return new ResponseDTO<>(1, "评审失败", null);
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
