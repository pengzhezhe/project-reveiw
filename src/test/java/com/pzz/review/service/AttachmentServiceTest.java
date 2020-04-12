package com.pzz.review.service;

import com.pzz.review.domain.Attachment;
import com.pzz.review.dto.ResponseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AttachmentServiceTest {
    @Autowired
    private AttachmentService attachmentService;

    @Test
    public void listAttachment() {
        List<Attachment> attachments = attachmentService.listAttachment(1);
        System.out.println(attachments);
    }
}