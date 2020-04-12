package com.pzz.review.mapper;

import com.pzz.review.domain.Attachment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AttachmentMapper {
    int delete(Integer id);

    int insert(Attachment attachment);

    Attachment getAttachment(Integer id);

    List<Attachment> listAttachments();

    List<Attachment> listAttachmentsByProjectId(Integer projectId);

    int update(Attachment attachment);
}