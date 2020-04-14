package com.pzz.review.controller;

import com.pzz.review.domain.Attachment;
import com.pzz.review.dto.ResponseDTO;
import com.pzz.review.exception.AppException;
import com.pzz.review.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class AttachmentController {
    /**
     * 文件上传路径
     */
    @Value("${file.upload-dir}")
    String filePath;

    @Autowired
    private AttachmentService attachmentService;

    /**
     * 文件上传
     *
     * @param file      上传的文件
     * @param projectId 文件所属项目
     * @return response
     * @throws IOException
     */
    @PostMapping("/attachment/upload")
    @ResponseBody
    public ResponseDTO<String> fileUpload(@RequestParam("file") MultipartFile file, @RequestParam("projectId") Integer projectId) throws IOException {
        if (attachmentService.fileUpload(file, projectId))
            return new ResponseDTO<>(0, "上传成功", null);
        else
            throw new AppException("上传文件失败");
    }

    /**
     * 文件下载
     *
     * @param attachmentId 文件Id
     * @return 文件流
     * @throws IOException
     */
    @GetMapping("/attachment/download/{id}")
    public ResponseEntity<InputStreamResource> fileDownload(@PathVariable("id") Integer attachmentId) throws IOException {
        Attachment attachment = attachmentService.getAttachment(attachmentId);
        FileSystemResource file = new FileSystemResource(filePath + attachment.getFilename());
        HttpHeaders headers = new HttpHeaders();
        String fileName = attachment.getOriginalName();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; fileName=" + fileName + ";filename*=utf-8''" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }
}
