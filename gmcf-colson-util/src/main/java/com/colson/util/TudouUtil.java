package com.colson.util;

import com.alibaba.fastjson.JSON;
import com.colson.common.bean.TudouJsonRootBean;
import com.colson.common.bean.TudouYSBean;
import com.colson.util.excel.ExlExport;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.IOException;
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
