package com.colson.service.impl;

import com.colson.dal.dao.UserEntityMapper;
import com.colson.dal.model.UserEntity;
import com.colson.service.bean.UserBean;
import com.colson.service.UserService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserEntityMapper userEntityMapper;

    @Resource
    MapperFacade mapperFacade;

    @Override
    public UserBean queryUserInfo(Long id) {
        UserEntity userEntity = userEntityMapper.selectByPrimaryKey(id);
        UserBean bean = this.mapperFacade.map(userEntity, UserBean.class);
        return bean;
    }
}
