package com.colson.service;

import com.colson.service.bean.UserBean;

public interface UserService {

    public UserBean queryUserInfo(Long id);
}