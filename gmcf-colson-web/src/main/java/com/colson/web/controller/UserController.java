package com.colson.web.controller;

import com.colson.dal.excel.ExlExport;
import com.colson.web.bean.AttendanceRecordBean;
import com.colson.web.bean.UserBean;
import com.colson.web.service.UserService;
import com.colson.web.util.DateUtils;
import com.colson.web.util.OAUtil;
import com.colson.web.util.SpringUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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


	/**
	 * @brief OA记录-导出
	 * @api GET /api/user/get-oa-info
	 * @occurs songbowen
	 */
	@RequestMapping(value = "/get-oa-info", method = RequestMethod.GET)
	@ResponseBody
	public void queryOAExport(HttpServletResponse response) {

		List<AttendanceRecordBean> beans = OAUtil.getBeans();

		HSSFWorkbook workbook;
		try {
			workbook = (HSSFWorkbook) ExlExport.exportExcel(beans, "AttendanceRecordBean");
			ExlExport.printExcel(workbook, response, "国美_谷本考勤模版_员工姓名宋博文-" + DateUtils.getNowDateShort() + ".xls");
		} catch (Exception e) {
		}
	}
}
