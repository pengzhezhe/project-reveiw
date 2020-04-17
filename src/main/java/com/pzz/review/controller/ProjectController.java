package com.pzz.review.controller;

import com.pzz.review.ao.ProjectAO;
import com.pzz.review.ao.ProjectAddAO;
import com.pzz.review.domain.Announcement;
import com.pzz.review.domain.Attachment;
import com.pzz.review.dto.PageDTO;
import com.pzz.review.dto.ProjectDTO;
import com.pzz.review.dto.ProjectDetailDTO;
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
    private UserService userService;

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private AttachmentService attachmentService;

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
    public String listProjects(@RequestParam(defaultValue = "1", name = "page") int pageNum, @RequestParam(defaultValue = "9", name = "limit") int pageSize, @RequestParam(defaultValue = "0") int type, HttpSession httpSession, Model model) {
        String username = (String) httpSession.getAttribute("username");
        Integer userId = userService.getUserIdByUsername(username);
        PageDTO<ProjectDTO> pageDTO = projectService.listProjectsByUserIdAndType(userId, type, pageNum, pageSize);
        List<Announcement> announcements = announcementService.listNewAnnouncements();
        model.addAttribute("projects", pageDTO.getData());
        model.addAttribute("announcements", announcements);
        model.addAttribute("count", pageDTO.getCount());
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("index", type);
        return "project/index";
    }

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
    public ResponseDTO<PageDTO<ProjectDTO>> listProject(@RequestParam(defaultValue = "-1", name = "type") int type, @RequestParam(defaultValue = "1", name = "page") int pageNum, @RequestParam(defaultValue = "10", name = "limit") int pageSize) {
        PageDTO<ProjectDTO> pageDTO = null;
        if (type == 0 || type == 1 || type == 3)
            pageDTO = projectService.listProjectsByStatus(type, pageNum, pageSize);
        else
            pageDTO = projectService.listProjects(pageNum, pageSize);
        return new ResponseDTO<>(0, "Success", pageDTO);
    }

    @PutMapping("/api/project")
    @ResponseBody
    public ResponseDTO<String> updateProject(@RequestBody ProjectAO projectAO) {
        if (projectService.updateProject(projectAO))
            return new ResponseDTO<>(0, "修改成功", null);
        else
            return new ResponseDTO<>(1, "修改失败", null);
    }

    @DeleteMapping("/api/project/{id}")
    @ResponseBody
    public ResponseDTO<String> deleteProject(@PathVariable("id") int projectId) {
        if (projectService.deleteProject(projectId))
            return new ResponseDTO<>(0, "删除成功", null);
        else
            return new ResponseDTO<>(1, "删除失败", null);
    }
}
