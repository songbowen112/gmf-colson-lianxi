package com.colson.web.test;

import com.colson.web.util.SpringUtil;

import java.util.List;

public class SpringTest {

    public static void main(String[] args) {

        List beans = SpringUtil.getBeans();
        beans.forEach(i -> {
            System.out.println(i);
        });

        Object userController = SpringUtil.getBean("UserController");
        System.out.println(userController);
    }
}
