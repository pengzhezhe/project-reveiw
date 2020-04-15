package com.pzz.review.controller;

import com.pzz.review.domain.Announcement;
import com.pzz.review.domain.Attachment;
import com.pzz.review.dto.ProjectDetailDTO;
import com.pzz.review.dto.UserDTO;
import com.pzz.review.service.AnnouncementService;
import com.pzz.review.service.AttachmentService;
import com.pzz.review.service.ProjectService;
import com.pzz.review.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserWebController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private AttachmentService attachmentService;

    /**
     * 登录页面
     *
     * @return user/login
     */
    @GetMapping("/login")
    public String loginView() {
        return "user/login";
    }

    /**
     * 登出
     *
     * @param httpSession Session
     * @return user/login
     */
    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/login";
    }

    /**
     * 更新个人信息页面
     *
     * @param httpSession Session
     * @param model       data
     * @return user/update
     */
    @GetMapping("/user/update")
    public String updateUserView(HttpSession httpSession, Model model) {
        String username = (String) httpSession.getAttribute("username");
        UserDTO user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * 添加项目页面
     *
     * @return project/add
     */
    @GetMapping("/project/add")
    public String addProjectView() {
        return "project/add";
    }

    /**
     * 查看项目详情页面
     *
     * @param id    项目Id
     * @param model data
     * @return project/detail
     */
    @GetMapping("/project/{id}")
    public String projectDetailView(@PathVariable("id") Integer id, Model model) {
        ProjectDetailDTO projectDetail = projectService.getProjectDetail(id);
        List<Announcement> announcements = announcementService.listNewAnnouncements();
        List<Attachment> attachments = attachmentService.listAttachment(id);
        model.addAttribute("project", projectDetail);
        model.addAttribute("announcements", announcements);
        model.addAttribute("attachments", attachments);
        return "project/detail";
    }
}
