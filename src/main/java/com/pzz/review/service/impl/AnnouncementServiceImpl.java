package com.pzz.review.service.impl;

import com.pzz.review.domain.Announcement;
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
    public List<Announcement> listAnnouncements() {
        return announcementMapper.listAnnouncements();
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
