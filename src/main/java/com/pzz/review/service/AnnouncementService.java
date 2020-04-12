package com.pzz.review.service;

import com.pzz.review.domain.Announcement;

import java.util.List;

public interface AnnouncementService {
    List<Announcement> listAnnouncements();

    List<Announcement> listNewAnnouncements();

    Announcement getAnnouncementById(Integer id);
}
