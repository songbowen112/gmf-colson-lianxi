package com.colson.dal.excel;

import com.colson.dal.excel.bean.Column;
import com.colson.dal.excel.bean.NxmlParser;
import com.colson.dal.excel.constant.ExcelConstant;
import com.colson.dal.exception.MyBusinessException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ExlExport {
	public static final short START_ROW = 0;
	
	//样式表
    private static Map<String, CellStyle> createStyles(Workbook wb){
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		DataFormat fmt = wb.createDataFormat();
		CellStyle style1 = wb.createCellStyle();
		Font headerFont1 = wb.createFont();
		// 粗体
		headerFont1.setBoldweight(Font.BOLDWEIGHT_BOLD);
		// 设置单元格背景色
		style1.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style1.setFont(headerFont1);
		styles.put("header", style1);

		CellStyle style2 = wb.createCellStyle();
		style2.setAlignment(CellStyle.ALIGN_CENTER);
		styles.put("body", style2);

		CellStyle style3 = wb.createCellStyle();
		style3.setAlignment(CellStyle.ALIGN_RIGHT);
		style3.setDataFormat(fmt.getFormat("¥#,##0.00"));
		styles.put("currency", style3);

		CellStyle style4 = wb.createCellStyle();
		style4.setAlignment(CellStyle.ALIGN_CENTER);
		style4.setDataFormat(fmt.getFormat("yyyy-m-d HH:mm:ss"));
		styles.put("date", style4);

		CellStyle style5 = wb.createCellStyle();
		style5.setAlignment(CellStyle.ALIGN_RIGHT);
		style5.setDataFormat(fmt.getFormat("0.00%"));
		styles.put("percent", style5); 
		
		CellStyle style6 = wb.createCellStyle();
		style6.setAlignment(CellStyle.ALIGN_RIGHT);
		style6.setDataFormat(fmt.getFormat("0.00"));
		styles.put("snum", style6);//两位小数
        return styles;  
    } 
    
    
    public static void export(List<String[]> header,List<Object[]> datas){
		FileOutputStream out = null;
    	try {
			out = new FileOutputStream("workbook.xls");
			getWorkbook(header,datas).write(out);
		} catch (FileNotFoundException e) {
			//logger.error("发生异常 msg={}","原因",e);
		}catch (IOException e) {
			//logger.error("发生异常 msg={}","原因",e);
		}finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					//logger.error("发生异常 msg={}","原因",e);
				}
			}
		}
    }
    
    
    public static Workbook getWorkbook(List<String[]> header, List<Object[]> datas){
			// create a new workbook
			Workbook wb = new HSSFWorkbook();
			// create a new sheet
			Sheet s = wb.createSheet();
			
			Map<String, CellStyle> styles = createStyles(wb);
			
			//生成表头
			Row hr = s.createRow(START_ROW);
			int cNo = header.size();
			if(cNo>0){
				CellStyle cs = styles.get("header");
				for(int i=0;i<cNo;i++){
					Cell cell = hr.createCell(i);
					cell.setCellStyle(cs);
					cell.setCellValue(header.get(i)[0]);
				}
			}
			int rNo = datas.size();
			//生成数据体
			if(rNo>0){
				CellStyle cs = styles.get("body");
				for(int i=0;i<datas.size();i++){
					Row hbr = s.createRow(i+1);
					Object[] os = datas.get(i);
					for(int j=0;j<cNo;j++){
						Cell cell = hbr.createCell(j);
						cell.setCellValue(strFormat(os[j], header.get(j)[1]));
						cell.setCellStyle(cs);
					}
				}
			}
		return wb;
    }
    
    /**
     * 将字符串格式化
     */
    public static String strFormat(Object resource,String signFormat){
    	String result = "";
    	if("money".equals(signFormat)){
    		result = String.format("%,.2f", Double.parseDouble(resource.toString()));
    	}else if("date".equals(signFormat)){
    		result = String.format("%tF", (Date)resource);
    	}else if("time".equals(signFormat)){
    		result = String.format("%tT", (Date)resource);
    	}else if("percent".equals(signFormat)){
    		result = String.format("%.2f%%", Double.parseDouble(resource.toString()));
    	}else{
    		result = resource.toString();
    	}
    	return result;
    	
    }
    
    public static <T,E> Workbook exportExcel(List<T> list, NxmlParser nxp, E E, NxmlParser nxpTotal) throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, InterruptedException {
    	if(null==nxp){
    		throw new MyBusinessException("excel解析器不存在","0000");
    	}

    	// create a new workbook
		Workbook wb = new HSSFWorkbook();

		Map<String, CellStyle> styles = createStyles(wb);

		List<Column> cols = nxp.getColumns();
    	
		if(null==cols){
			throw new MyBusinessException("excel没有配置column","0000");
		}
    	
		int cno = cols.size();
		
		if(cno==0){
			throw new MyBusinessException("excel没有配置column","0000");
		}

		//一个sheet显示的行数
		int totalRows= ExcelConstant.TOTAL_ROWS;
		Class<?> clazz = Class.forName(nxp.getClassname());
		if(null!=list){
			int rNo = list.size();
			//生成数据体
			if(rNo>0) {
				//表示sheet工作表的张数;
				int sheetPageTotal = rNo%totalRows == 0 ? rNo/totalRows : (rNo/totalRows) + 1;
				ExecutorService executorService = Executors.newSingleThreadExecutor();
				CountDownLatch countDownLatch=new CountDownLatch(sheetPageTotal);
				for (int t = 1; t <= sheetPageTotal; t++) {
					int sheetCurrentNumber = t;
					executorService.submit(new Runnable() {
						@Override
						public void run() {
							Sheet s = wb.createSheet("Sheet" + sheetCurrentNumber);
							//生成表头
							Row hr = s.createRow(START_ROW);
							//设置表头样式
							CellStyle hcs = styles.get("header");
							//设置表头
							for (Column col : cols) {
								Cell cell = hr.createCell(col.getCidx());
								cell.setCellStyle(hcs);
								cell.setCellValue(col.getCname());
							}

							int rows=sheetCurrentNumber*totalRows<=rNo?totalRows:rNo-((sheetCurrentNumber-1)*totalRows);
							for (int i = 0; i < rows; i++) {
								int num=((sheetCurrentNumber-1)*totalRows)+i;

								Row hbr = s.createRow(i + 1);
								T td = list.get(num);
								for (Column c : cols) {
									try {
										createCell(clazz, td, styles, c, hbr);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
							if (nxpTotal != null) {
								Class<?> clazzTotal = null;
								try {
									clazzTotal = Class.forName(nxpTotal.getClassname());
								} catch (ClassNotFoundException e) {
									e.printStackTrace();
								}
								List<Column> colsTotal = nxpTotal.getColumns();
								if (null == colsTotal) {
									throw new MyBusinessException("excel没有配置汇总column", "0000");
								}
								int cnoTotal = colsTotal.size();

								if (cnoTotal == 0) {
									throw new MyBusinessException("excel没有配置汇总column", "0000");
								}
								Row hbr = s.createRow(rNo + 1);
								for (Column c : colsTotal) {
									try {
										createCell(clazzTotal, E, styles, c, hbr);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
							countDownLatch.countDown();
						}
					});
				}
				countDownLatch.await();
				executorService.shutdown();
			}
		}
    	return wb;
    }

    public  static <T> void createCell(Class<?> clazz, T td, Map<String, CellStyle> styles, Column column, Row hbr)throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException
    {

    	String pname = column.getPname();
		PropertyDescriptor pd = new PropertyDescriptor(pname,clazz);
		Method getMethod = pd.getReadMethod();//获得get方法
		Object o = getMethod.invoke(td);//执行get方法返回一个Object
		if(null==o){
			return ;
		}
		String type = column.getType();
		Class<?> pc = pd.getPropertyType();//获取类型
		if(StringUtils.isBlank(type)){//如果没有设置type，就直接返回对象的中的类型
			type = pc.getSimpleName();
		}
		
		if("obj".equals(type)){
			if(StringUtils.isBlank(column.getHandler())){
				String[] strs = column.getHandler().split("#");
				if(strs[0].startsWith("insastead")){
					PropertyDescriptor cpd = new PropertyDescriptor(strs[1],pc);
					Method getcMethod = cpd.getReadMethod();//获得get方法
					o = getcMethod.invoke(o);
					type = "String";
				}else if(strs[0].startsWith("int")){
					PropertyDescriptor cpd = new PropertyDescriptor(strs[1],pc);
					Method getcMethod = cpd.getReadMethod();//获得get方法
					o = getcMethod.invoke(o);
					type = "Integer";
				}
				
			}
		}
    	if("String".equals(type)){
			Cell cell = hbr.createCell(column.getCidx());
			cell.setCellValue((String)o);
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}else if("Integer".equals(type)||"int".equals(type)){
			Cell cell = hbr.createCell(column.getCidx());
			cell.setCellValue((Integer)o);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		}else if("Float".equals(type)||"float".equals(type)){
			Cell cell = hbr.createCell(column.getCidx());
			cell.setCellValue((Float)o);
			cell.setCellStyle(styles.get("snum"));
		}else if("Double".equals(type)||"bouble".equals(type)){
			Cell cell = hbr.createCell(column.getCidx());
			cell.setCellValue((Double)o);
			cell.setCellStyle(styles.get("snum"));
		}else if("Byte".equals(type)||"byte".equals(type)){
			Cell cell = hbr.createCell(column.getCidx());
			cell.setCellValue((Byte)o);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		}else if("Long".equals(type)||"long".equals(type)){
			Cell cell = hbr.createCell(column.getCidx());
			cell.setCellValue((Long)o);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		}else if("Boolean".equals(type)||"boolean".equals(type)){
			Cell cell = hbr.createCell(column.getCidx());
			cell.setCellValue((Long)o);
			cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
		}else if("Date".equals(type)){
			Cell cell = hbr.createCell(column.getCidx());
			cell.setCellValue((Date)o);
			cell.setCellStyle(styles.get("date"));
		}else if("currency".equals(type)){
			Cell cell = hbr.createCell(column.getCidx());
			cell.setCellValue(((BigDecimal)o).doubleValue());
			cell.setCellStyle(styles.get("snum"));
		}else if("BigDecimal".equals(type)){
			Cell cell = hbr.createCell(column.getCidx());
			cell.setCellValue(((BigDecimal)o).doubleValue());
			cell.setCellStyle(styles.get("snum"));
		}else if("percent".equals(type)){
			Cell cell = hbr.createCell(column.getCidx());
			cell.setCellValue((Date)o);
			cell.setCellStyle(styles.get("percent"));
		}    	
    }
    
    /**
     * 根据传入的list中对象获取解析器，进行生成excel
     * @param <T>
     * @param list
     * @return
     * @throws IntrospectionException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException 
     */
	@SuppressWarnings("hiding")
	public static <T> Workbook exportExcel(List<T> list) throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, InterruptedException {
    	if(null==list||list.size()==0){
    		throw new MyBusinessException("数据为空","0000");
    	}
    	T t = list.get(0);
    	Class<?> clazz = t.getClass();
    	String csm = clazz.getSimpleName();
    	//获取解析器
    	NxmlParser nxp = ParseUtils.nanalysis().get(csm);
    	return exportExcel(list,nxp,null,null);
    }
    
	/**
	 * 导出excel汇总
	 * @param <T>
	 * @param <E>
	 * @param list
	 * @param E
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 */
	public static <T,E> Workbook exportExcelTotal(List<T> list, E E) throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, InterruptedException {
    	if(null==list||list.size()==0){
    		throw new MyBusinessException("数据为空","0000");
    	}
    	T t = list.get(0);
    	Class<?> clazz = t.getClass();
    	String csm = clazz.getSimpleName();
    	//获取解析器
    	NxmlParser nxp = ParseUtils.nanalysis().get(csm);
    	
    	NxmlParser nxpTotal = ParseUtils.nanalysis().get(E.getClass().getSimpleName());
    	return exportExcel(list,nxp,E,nxpTotal);		
	}
		
	/**
	 * 
	 * @param <T> 传入类
	 * @param list 结果集
	 * @param parserId 解析器ID
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException 
	 */
	public static <T> Workbook exportExcel(List<T> list, String parserId) throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, InterruptedException {
		if(StringUtils.isBlank(parserId)){
			return exportExcel(list);
		}else{
			NxmlParser nxp = ParseUtils.nanalysis().get(parserId);
			return exportExcel(list,nxp,null,null);
		}
    }
	public static void printExcel(HSSFWorkbook workbook, HttpServletResponse response, String filename) throws IOException {
		OutputStream out = response.getOutputStream();
		response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
		response.setContentType("application/msexcel;charset=UTF-8");
		if (workbook!=null) {
			workbook.write(out);
		}
		out.close();
	}
}
