package com.colson.service;

import com.colson.service.bean.UserBean;

public interface UserService {

    UserBean queryUserInfo(Long id);
}