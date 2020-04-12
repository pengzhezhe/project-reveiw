package com.pzz.review.service;

import com.pzz.review.domain.Attachment;
import com.pzz.review.dto.ResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AttachmentService {
    boolean uploadFile(MultipartFile file, Integer projectId) throws IOException;

    boolean uploadFile(MultipartFile file) throws IOException;

    Attachment downloadFile(Integer attachmentId);

    List<Attachment> listAttachment(Integer projectId);
}
