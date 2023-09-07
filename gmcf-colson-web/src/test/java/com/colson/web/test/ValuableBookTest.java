package com.colson.web.test;

import com.colson.service.AttachmentService;
import com.colson.util.SpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml"})
public class AttachmentTest {

    @Autowired
    private AttachmentService attachmentService;

    @Test
    public void queryAttachmentList() {
        attachmentService.queryAttachmentList(new ArrayList<>());
    }
}
