package com.pzz.review.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzz.review.domain.Announcement;
import com.pzz.review.domain.Attachment;
import com.pzz.review.domain.Project;
import com.pzz.review.domain.User;
import com.pzz.review.dto.ProjectDetailDTO;
import com.pzz.review.dto.ResponseDTO;
import com.pzz.review.dto.UserDTO;
import com.pzz.review.exception.AppException;
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
     * 项目列表
     *
     * @param pageNum     页数
     * @param pageSize    每页显示数量
     * @param type        项目类别，0=全部，1=审核中，2=已审核
     * @param httpSession Session
     * @param model       data
     * @return project/index
     */
    @GetMapping("/project")
    public String project(@RequestParam(defaultValue = "1", name = "page") int pageNum, @RequestParam(defaultValue = "9", name = "limit") int pageSize, @RequestParam(defaultValue = "0") int type, HttpSession httpSession, Model model) {
        if (type > 2)
            throw new AppException("type invalid");
        String username = (String) httpSession.getAttribute("username");
        Integer userId = userService.getUserIdByUsername(username);
        PageHelper.startPage(pageNum, pageSize);
        List<Project> projects = projectService.listProjectsByUserIdAndStatus(userId, type);
        PageInfo pageInfo = new PageInfo(projects);
        List<Announcement> announcements = announcementService.listNewAnnouncements();
        model.addAttribute("projects", projects);
        model.addAttribute("announcements", announcements);
        model.addAttribute("page", pageInfo);
        model.addAttribute("index", type);
        return "project/index";
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
        UserDTO user = userService.getUserByUsername(username);
        Project project = new Project();
        project.setName(name);
        project.setIntroduction(introduction);
        project.setUserId(user.getId());
        project.setUserName(user.getName());
        project.setStatus(0);
        projectService.addProject(project);
        return new ResponseDTO<>(0, "添加成功", project.getId());
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
