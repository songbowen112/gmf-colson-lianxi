package com.colson.dal.dao;

import com.colson.dal.model.PaymentEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PaymentEntity record);

    int insertSelective(PaymentEntity record);

    PaymentEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PaymentEntity record);

    int updateByPrimaryKey(PaymentEntity record);
}