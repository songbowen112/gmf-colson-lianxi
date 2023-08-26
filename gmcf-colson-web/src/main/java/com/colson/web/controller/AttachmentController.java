package com.colson.web.controller;

import com.colson.common.bean.AttendanceRecordBean;
import com.colson.common.bean.TudouYSBean;
import com.colson.service.AttachmentService;
import com.colson.service.UserService;
import com.colson.service.bean.UserBean;
import com.colson.util.DateUtils;
import com.colson.util.OAUtil;
import com.colson.util.SpringUtil;
import com.colson.util.TudouUtil;
import com.colson.util.excel.ExlExport;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/attachment")
public class AttachmentController {

    @Resource
    AttachmentService attachmentService;

    @RequestMapping(value = "/createFile", method = RequestMethod.GET)
    public void createFile() {
        attachmentService.queryAttachmentList(new ArrayList<>());
    }

}
