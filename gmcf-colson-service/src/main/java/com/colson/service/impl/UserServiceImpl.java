package com.colson.service.impl;

import com.colson.service.bean.UserBean;
import com.colson.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserBean queryUserInfo(Integer id) {
        return new UserBean();
    }
}
