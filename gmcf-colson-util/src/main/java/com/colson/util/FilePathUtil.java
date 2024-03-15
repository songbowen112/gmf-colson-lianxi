package com.colson.util;

import com.alibaba.fastjson.JSON;
import com.colson.common.bean.FilePathBean;
import com.colson.common.bean.TudouJsonRootBean;
import com.colson.common.bean.TudouYSBean;
import com.colson.util.excel.ExlExport;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilePathUtil {
    public static List<FilePathBean> getBeans(String dirName) {
		List<FilePathBean> filePathBeans = new ArrayList<>();
		File file = new File("/Users/songbowen/Desktop/资料/" + dirName);
		if (file.exists()) {
			File[] files = file.listFiles();
			for (File file1 : files) {
				String[] fileNameList = file1.list();
				if (null == fileNameList || fileNameList.length == 0) {
					continue;
				}
				for (String fileName : fileNameList) {
					FilePathBean bean = new FilePathBean();
					bean.setFirstDir(file.getName());
					bean.setSecondDir(file1.getName());
					bean.setFullFileName(fileName);
					filePathBeans.add(bean);
				}
			}
		}
		return filePathBeans;
	}


	public static void main(String[] args) {
		String dirName = "官方笔记";
		List<FilePathBean> beans = FilePathUtil.getBeans(dirName);
		try {
			HSSFWorkbook workbook = (HSSFWorkbook) ExlExport.exportExcel(beans, "FilePathBean");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
