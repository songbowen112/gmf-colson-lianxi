package com.colson.web.test;


import com.colson.util.SpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml"})
public class SpringTest {

    @Test
    public void testSpringBean() {
        List beans = SpringUtil.getBeans();
        beans.forEach(i -> {
            System.out.println(i);
        });

        Object userController = SpringUtil.getBean("userController");
        System.out.println(userController);
    }

}
