package com.pzz.review.mapper;

import com.pzz.review.domain.Attachment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AttachmentMapperTest {
    @Autowired
    private AttachmentMapper attachmentMapper;

    @Test
    public void listAttachments() {
        List<Attachment> attachments = attachmentMapper.listAttachments();
        System.out.println(attachments);
    }

    @Test
    public void listAttachmentsByProjectId() {
        List<Attachment> attachments = attachmentMapper.listAttachmentsByProjectId(1);
        System.out.println(attachments);
    }
}