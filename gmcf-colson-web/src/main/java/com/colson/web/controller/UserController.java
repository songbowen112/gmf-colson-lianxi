package com.colson.web.controller;

import com.colson.common.bean.FilePathBean;
import com.colson.util.*;
import com.colson.util.excel.ExlExport;
import com.colson.service.UserService;
import com.colson.service.bean.UserBean;
import com.colson.common.bean.AttendanceRecordBean;
import com.colson.common.bean.TudouYSBean;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public UserBean getUser(Long id) {
        UserBean bean = userService.queryUserInfo(id);
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
		    e.printStackTrace();
		}
	}

    /**
     * @brief 土豆雅思-导出
     * @api GET localhost:10087/api/user/get-td-info
     * @occurs songbowen
     */
    @RequestMapping(value = "/get-td-info", method = RequestMethod.GET)
    @ResponseBody
    public void queryTdExport(HttpServletResponse response) {
        List<TudouYSBean> beans = TudouUtil.getBeans();

        HSSFWorkbook workbook;
        try {
            workbook = (HSSFWorkbook) ExlExport.exportExcel(beans, "TudouYSBean");
            ExlExport.printExcel(workbook, response, "土豆雅思-" + DateUtils.getStringDate() + ".xls");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief 文件信息-导出
     * @api GET localhost:10087/api/user/get-filePath
     * @occurs songbowen
     */
    @RequestMapping(value = "/get-filePath", method = RequestMethod.GET)
    @ResponseBody
    public void queryFilePathExport(HttpServletResponse response, @RequestParam("dirName") String dirName) {
//        String dirName = "押题急救密卷";
//        String dirName = "官方笔记";
        // 补漏百题斩 考学一点通 考前黄金卷 决胜3小时 考前60分-主观题带背
//        String dirName = "补漏百题斩";
        List<FilePathBean> beans = FilePathUtil.getBeans(dirName);

        HSSFWorkbook workbook;
        try {
            workbook = (HSSFWorkbook) ExlExport.exportExcel(beans, "FilePathBean");
            ExlExport.printExcel(workbook, response, dirName + "-" + DateUtils.getStringDate() + ".xls");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
