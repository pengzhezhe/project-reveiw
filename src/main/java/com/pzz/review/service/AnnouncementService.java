package com.pzz.review.service;

import com.pzz.review.domain.Announcement;
import com.pzz.review.dto.PageDTO;

import java.util.List;

public interface AnnouncementService {
    PageDTO<Announcement> listAnnouncements(int pageNum, int pageSize);

    List<Announcement> listNewAnnouncements();

    Announcement getAnnouncementById(Integer id);
}
