package com.colson.dal.dao;

import com.colson.dal.model.AttachmentEntity;
import com.colson.dal.model.DatumEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DatumEntityMapper {

    List<DatumEntity> selectDatumBundleList();

    List<DatumEntity> selectDatumBundleListByFileName();

    List<DatumEntity> selectDatumBundleListByCreateTime(@Param("createTime") String createTime);
}
