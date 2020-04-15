package com.pzz.review.controller;

import com.pzz.review.domain.Announcement;
import com.pzz.review.dto.PageDTO;
import com.pzz.review.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    /**
     * 公告页面
     *
     * @param pageNum  页数
     * @param pageSize 每页显示数量
     * @param type     公告类别 0=最新，1=全部
     * @param model    data
     * @return announcement/index
     */
    @GetMapping("/announcement")
    public String announcementView(@RequestParam(defaultValue = "1", name = "page") int pageNum, @RequestParam(defaultValue = "9", name = "limit") int pageSize, @RequestParam(defaultValue = "0") int type, Model model) {
        if (type == 1) {
            PageDTO<Announcement> pageDTO = announcementService.listAnnouncements(pageNum, pageSize);
            model.addAttribute("announcements", pageDTO.getData());
            model.addAttribute("count", pageDTO.getCount());
            model.addAttribute("pageNum", pageNum);
            model.addAttribute("index", type);
        } else {
            List<Announcement> announcements = announcementService.listNewAnnouncements();
            model.addAttribute("announcements", announcements);
            model.addAttribute("count", announcements.size());
            model.addAttribute("pageNum", pageNum);
            model.addAttribute("index", type);
        }
        return "announcement/index";
    }

    /**
     * 公告详情页
     *
     * @param id    公告Id
     * @param model data
     * @return announcement/detail
     */
    @GetMapping("/announcement/{id}")
    public String announcementDetailView(@PathVariable("id") Integer id, Model model) {
        Announcement announcement = announcementService.getAnnouncementById(id);
        List<Announcement> announcements = announcementService.listNewAnnouncements();
        model.addAttribute("announcement", announcement);
        model.addAttribute("announcements", announcements);
        return "announcement/detail";
    }
}
