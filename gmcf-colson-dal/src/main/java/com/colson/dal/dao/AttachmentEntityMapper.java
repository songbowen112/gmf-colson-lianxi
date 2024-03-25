package com.colson.dal.dao;

import com.colson.dal.model.AttachmentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AttachmentEntityMapper {

    List<AttachmentEntity> selectAttachmentList();

    List<AttachmentEntity> selectAttachmentListByFileName();

    List<AttachmentEntity> selectAttachmentListByCreateTime(@Param("createTime") String createTime);
}
