package com.colson.web.test;

import com.colson.service.AttachmentService;
import com.colson.service.PaperUploadService;
import com.colson.service.ValuableBookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

/**
 * 通关宝典下载
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml"})
public class ValuableBookTest {

    @Autowired
    PaperUploadService paperUploadService;

    @Test
    public void downloadValuableBookTask() {
        String subjectIdStr = "";
//        String subjectIdStr = "2,24,25,17";
//        String subjectIdStr = "2";
        String provinceIdStr = "";
        paperUploadService.downloadValuableBookTask(subjectIdStr, provinceIdStr);
    }
}
