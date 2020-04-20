package com.pzz.review.mapper;

import com.pzz.review.domain.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnnouncementMapper {
    int delete(Integer id);

    int insert(Announcement announcement);

    int update(Announcement announcement);

    Announcement getAnnouncement(Integer id);

    List<Announcement> listAnnouncements();

}