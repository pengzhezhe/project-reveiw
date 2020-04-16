package com.pzz.review.service;

import com.pzz.review.ao.AnnouncementAO;
import com.pzz.review.ao.AnnouncementAddAO;
import com.pzz.review.domain.Announcement;
import com.pzz.review.dto.PageDTO;

import java.util.List;

public interface AnnouncementService {
    boolean addAnnouncement(AnnouncementAddAO announcementAddAO);

    boolean updateAnnouncement(AnnouncementAO announcementAO);

    boolean deleteAnnouncement(Integer id);

    PageDTO<Announcement> listAnnouncements(int pageNum, int pageSize);

    List<Announcement> listNewAnnouncements();

    Announcement getAnnouncementById(Integer id);
}
