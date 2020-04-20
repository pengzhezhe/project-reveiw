package com.pzz.review.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pzz.review.ao.AnnouncementAO;
import com.pzz.review.ao.AnnouncementAddAO;
import com.pzz.review.domain.Announcement;
import com.pzz.review.dto.PageDTO;
import com.pzz.review.exception.AppException;
import com.pzz.review.mapper.AnnouncementMapper;
import com.pzz.review.service.AnnouncementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public boolean addAnnouncement(AnnouncementAddAO announcementAddAO) {
        Announcement announcement = new Announcement();
        BeanUtils.copyProperties(announcementAddAO, announcement);
        return announcementMapper.insert(announcement) > 0;
    }

    @Override
    public boolean updateAnnouncement(AnnouncementAO announcementAO) {
        Announcement announcement = announcementMapper.getAnnouncement(announcementAO.getId());
        if (announcement == null)
            throw new AppException("公告不存在");
        announcement.setTitle(announcementAO.getTitle());
        announcement.setContent(announcementAO.getContent());
        return announcementMapper.update(announcement) > 0;
    }

    @Override
    public boolean deleteAnnouncement(Integer id) {
        if (announcementMapper.getAnnouncement(id) == null)
            throw new AppException("公告不存在");
        return announcementMapper.delete(id) > 0;
    }

    @Override
    public PageDTO<Announcement> listAnnouncements(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Announcement> announcements = announcementMapper.listAnnouncements();
        PageInfo pageInfo = new PageInfo(announcements);
        return new PageDTO<>(announcements, pageInfo.getTotal());
    }

    @Override
    public List<Announcement> listNewAnnouncements() {
        PageHelper.startPage(1, 8);
        return announcementMapper.listAnnouncements();
    }

    @Override
    public Announcement getAnnouncementById(Integer id) {
        return announcementMapper.getAnnouncement(id);
    }
}
