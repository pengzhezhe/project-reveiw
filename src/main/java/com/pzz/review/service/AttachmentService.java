package com.pzz.review.service;

import com.pzz.review.domain.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AttachmentService {
    /**
     * 文件上传
     *
     * @param file      上传的文件
     * @param projectId 文件所属项目
     * @return boolean
     * @throws IOException
     */
    boolean fileUpload(MultipartFile file, Integer projectId) throws IOException;

    /**
     * 获取附件信息
     *
     * @param attachmentId 文件Id
     * @return 文件相关信息
     */
    Attachment getAttachment(Integer attachmentId);

    /**
     * @param projectId
     * @return
     */
    List<Attachment> listAttachment(Integer projectId);
}
