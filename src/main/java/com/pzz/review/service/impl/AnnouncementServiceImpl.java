package com.pzz.review.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzz.review.domain.Announcement;
import com.pzz.review.dto.PageDTO;
import com.pzz.review.mapper.AnnouncementMapper;
import com.pzz.review.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public PageDTO<Announcement> listAnnouncements(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Announcement> announcements = announcementMapper.listAnnouncements();
        PageInfo pageInfo = new PageInfo(announcements);
        return new PageDTO<>(announcements, pageInfo.getTotal());
    }

    @Override
    public List<Announcement> listNewAnnouncements() {
        return announcementMapper.listNewAnnouncements();
    }

    @Override
    public Announcement getAnnouncementById(Integer id) {
        return announcementMapper.getAnnouncement(id);
    }
}
