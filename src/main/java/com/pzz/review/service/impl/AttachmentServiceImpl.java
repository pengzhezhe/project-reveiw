package com.pzz.review.service.impl;

import com.pzz.review.domain.Attachment;
import com.pzz.review.exception.AppException;
import com.pzz.review.mapper.AttachmentMapper;
import com.pzz.review.service.AttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    private AttachmentMapper attachmentMapper;

    @Value("${file.upload-dir}")
    String filePath;

    @Override
    public boolean uploadFile(MultipartFile file, Integer projectId) throws IOException {
        if (file.isEmpty())
            throw new AppException("文件为空");
        Attachment attachment = new Attachment();
        String originalFilename = file.getOriginalFilename();
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = UUID.randomUUID().toString() + suffixName;
        attachment.setProjectId(projectId);
        attachment.setFilename(filename);
        attachment.setOriginalName(originalFilename);
        File dest = new File(filePath + filename);
        log.info(dest.getPath());
        if (!dest.getParentFile().exists())
            dest.getParentFile().mkdirs();
        file.transferTo(dest);
        return attachmentMapper.insert(attachment) > 0;
    }

    @Override
    public boolean uploadFile(MultipartFile file) throws IOException {
        return uploadFile(file, null);
    }

    @Override
    public Attachment downloadFile(Integer attachmentId) {
        Attachment attachment = attachmentMapper.getAttachment(attachmentId);
        String filename = attachment.getFilename();
        log.info(attachment.toString());
        if (filename == null)
            return null;
        return attachment;
    }

    @Override
    public List<Attachment> listAttachment(Integer projectId) {
        return attachmentMapper.listAttachmentsByProjectId(projectId);
    }
}
