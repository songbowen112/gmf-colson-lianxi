package com.colson.web.controller;

import com.colson.web.bean.UserBean;
import com.colson.web.service.UserService;
import com.colson.web.util.SpringUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("api/user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping(value = "/get-user", method = RequestMethod.GET)
    @ResponseBody
    public UserBean getUser() {
        UserBean bean = userService.queryUserInfo(1);
//        UserBean bean = new UserBean();
//        bean.setName("zs");
//        bean.setGender(1);
        return bean;
    }


    @RequestMapping(value = "/get-bean", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getActiveBean() {
        List beans = SpringUtil.getBeans();
        beans.forEach(i -> {
            System.out.println(i);
        });
        return beans;
    }
}
