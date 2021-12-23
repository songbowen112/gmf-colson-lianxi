package com.colson.web.util;

import com.alibaba.fastjson.JSON;
import com.colson.dal.excel.ExlExport;
import com.colson.web.bean.*;
import com.colson.web.emum.VacationTypeEnum;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TudouUtil {
    public static List<TudouYSBean> getBeans() {
		StringBuilder sb = new StringBuilder();

		try {
			String result = Files.asCharSource(new File("/Users/song/Desktop/aaa.txt"), Charsets.UTF_8).read();
			sb.append(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		TudouJsonRootBean result = JSON
				.parseObject(sb.toString(), TudouJsonRootBean.class);

		return result.getList();
	}


	public static void main(String[] args) {
		List<TudouYSBean> beans = TudouUtil.getBeans();
		try {
			HSSFWorkbook workbook = (HSSFWorkbook) ExlExport.exportExcel(beans, "TudouYSBean");
		} catch (Exception e) {
		}
	}
}
