package com.pzz.review.service;

import com.pzz.review.domain.Announcement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AnnouncementServiceTest {
    @Autowired
    private AnnouncementService service;

    @Test
    public void listAnnouncements() {
        List<Announcement> announcements = service.listAnnouncements();
        System.out.println(announcements);
    }

    @Test
    public void getAnnouncementById() {
    }
}