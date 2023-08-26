package com.colson.dal.dao;

import com.colson.dal.model.AttachmentEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttachmentEntityMapper {

    List<AttachmentEntity> selectAttachmentList();
}
