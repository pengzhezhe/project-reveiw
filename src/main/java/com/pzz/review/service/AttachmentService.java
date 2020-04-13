package com.pzz.review.service;

import com.pzz.review.domain.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AttachmentService {
    boolean fileUpload(MultipartFile file, Integer projectId) throws IOException;

    Attachment downloadFile(Integer attachmentId);

    List<Attachment> listAttachment(Integer projectId);
}
