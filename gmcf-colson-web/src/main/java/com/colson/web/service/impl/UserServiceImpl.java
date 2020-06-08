package com.colson.web.service.impl;

import com.colson.web.bean.UserBean;
import com.colson.web.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserBean queryUserInfo(Integer id) {
        return new UserBean("茨木",19);
    }
}
