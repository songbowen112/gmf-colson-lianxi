package com.colson.util.excel;

import com.alibaba.fastjson.JSON;
import com.colson.common.exception.MyBusinessException;
import com.colson.util.excel.bean.Column;
import com.colson.util.excel.bean.NxmlParser;
import com.colson.util.FileTypeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.util.ReflectionUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;


public class ExlImport {
	public static final String fileName = "/excelRead.xml";
	public static <T> List<T> exlList(String xlsPath, String parserId) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
		//获取解析器
		NxmlParser nxp = nanalysis().get(parserId);
		return excelImport(xlsPath, nxp);
    }

    public static  <T> List<T> excelImport(String xlsPath, NxmlParser nxp) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
		List<T> list = new ArrayList<T>();
		FileInputStream fileIn = new FileInputStream(xlsPath);

		Workbook workbook = new HSSFWorkbook();
		//判断文件是否为xls doc
		if(FileTypeUtils.xlsType(xlsPath)){
			workbook = new HSSFWorkbook(fileIn);
		}
		//判断文件是否为xlsx docx
		if(FileTypeUtils.xlsxType(xlsPath)) {
			workbook = new XSSFWorkbook(fileIn);
		}
		int numberOfSheets = workbook.getNumberOfSheets();
		if (numberOfSheets <= 0) {
			fileIn.close();
			throw new MyBusinessException("上传文件内容为空,无法解析","10000");
		}

		//获取Excel文档中的第一个表单
		Sheet sheet = workbook.getSheetAt(0);

		List<Column> columns = nxp.getColumns();
		Map<String,Integer> columnsMap=new HashMap<>();
		for (Column column : columns) {
			columnsMap.put(column.getPname(),column.getCidx());
		}

		//对Sheet中的每一行进行迭代
		for (Row r : sheet) {
			//如果当前行的行号（从0开始）未达到2（第三行）则从新循环
			if (r.getRowNum() < 1) {  //首行
				for (Column column : columns) {
					String stringCellValue = r.getCell(column.getCidx()).getStringCellValue();
					if(!stringCellValue.equals(column.getCname())){
						fileIn.close();
						throw new MyBusinessException("模板不匹配","10000");
					}
				}
				continue;
			}

            Class<?> clazz = Class.forName(nxp.getClassname());
            Object object = clazz.newInstance();
            Method[] methodArray = ReflectionUtils.getAllDeclaredMethods(clazz);

			for(Method method : methodArray){
				if(method.getName().startsWith("set")){
					Type[] argTypes = method.getGenericParameterTypes();
					String fieldName = toLowerCaseFirstOne(method.getName().substring(3));
					if(!columnsMap.containsKey(fieldName)){
						continue;
					}
					if(argTypes.length == 1){
						Object value = null;
						Integer index = columnsMap.get(fieldName);
						if (null == r.getCell(index)) continue;
						if(!argTypes[0].getTypeName().equals("java.util.Date")){
                            r.getCell(index).setCellType(1);
                        }
						switch (argTypes[0].getTypeName()){
							case "byte": value = r.getCell(index).getErrorCellValue();break;
							case "java.lang.Byte": value = r.getCell(index).getErrorCellValue();break;
							case "int": value = Integer.valueOf(r.getCell(index).getStringCellValue());break;
							case "java.lang.Integer": {
									String num = StringUtils.isEmpty(r.getCell(index).getStringCellValue())?"0":r.getCell(index).getStringCellValue();
									value = Integer.valueOf(num);
								}
								break;
							case "long": value = Long.valueOf(r.getCell(index).getStringCellValue());break;
							case "java.lang.Long": value = Long.valueOf(r.getCell(index).getStringCellValue());break;
							case "boolean": value = r.getCell(index).getBooleanCellValue();break;
							case "java.lang.Boolean": value = r.getCell(index).getBooleanCellValue();break;
							case "java.lang.String": value =r.getCell(index).getStringCellValue();break;
							case "java.math.BigDecimal": {
								String num = StringUtils.isEmpty(r.getCell(index).getStringCellValue())?"0":r.getCell(index).getStringCellValue();
								value =new BigDecimal(num);
							}
							break;
							case "java.util.Date": {
                                if (HSSFDateUtil.isCellDateFormatted(r.getCell(index))) {
                                    value = r.getCell(index).getDateCellValue();
                                }else{
									throw new MyBusinessException("日期格式解析错误","10000");
                                }
                                break;
                            }
							default:value=null; break;
						}
						method.invoke(object,value);
					}
				}
			}
			list.add((T) object);
		}
		fileIn.close();
		return list;
	}
	public static Map<String, NxmlParser> nanalysis(){
		Document document = ParseUtils.getDocByPath(fileName);
		Element root = document.getRootElement();
		Map<String, NxmlParser> map = new HashMap<>();
		Iterator<Element> iter = root.elementIterator("wobj");
		while(iter.hasNext()) {
			NxmlParser nxp = new NxmlParser();
			Element e1 = iter.next();
			Attribute aId = e1.attribute("id");
			if(null!=aId){
				nxp.setId(aId.getValue());
			}

			Attribute name = e1.attribute("name");
			if(null!=name){
				nxp.setName(name.getValue());
			}

			Attribute filename = e1.attribute("filename");
			if(null!=filename){
				nxp.setFilename(filename.getValue());
			}

			Attribute filetype = e1.attribute("filetype");
			if(null!=filetype){
				nxp.setFiletype(filetype.getValue());
			}

			Attribute classname = e1.attribute("class");
			if(null!=classname){
				nxp.setClassname(classname.getValue());
			}


			Element e2 = e1.element("columns");
			List<Column> cols = new ArrayList<Column>();
			Iterator<Element> iter2 = e2.elementIterator();
			while (iter2.hasNext()) {
				Element e3 = iter2.next();
				Column col = new Column();

				Attribute cidx = e3.attribute("cidx");
				if(null!=cidx){
					col.setCidx(Integer.parseInt(cidx.getValue()));
				}

				Attribute cname = e3.attribute("cname");
				if(null!=cname){
					col.setCname(cname.getValue());
				}

				Attribute pname = e3.attribute("pname");
				if(null!=pname){
					col.setPname(pname.getValue());
				}

				Attribute type = e3.attribute("type");
				if(null!=type){
					col.setType(type.getValue());
				}

				Attribute cformat = e3.attribute("cformat");
				if(null!=cformat){
					col.setCformat(cformat.getValue());
				}

				Attribute handler = e3.attribute("handler");
				if(null!=handler){
					col.setHandler(handler.getValue());
				}
				cols.add(col);
			}
			nxp.setColumns(cols);
			map.put(aId.getValue(), nxp);
		}

		return map;
	}
	/**
	 * 首字母转小写
	 * @param s
	 * @return
	 */
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0))) {
			return s;
		} else {
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}

	public static void main(String[] args) throws IOException {
		try {
//			exlList("C:\\Users\\hp\\Desktop\\网银流水需求\\融通例子.xlsx","110929312110701");
            exlList("C:\\Users\\hp\\Desktop\\网银流水需求\\（4）0701-2笔.xlsx", "110929312110701");

		} catch (IOException | ClassNotFoundException | InstantiationException | InvocationTargetException | IllegalAccessException | IllegalStateException e) {
			e.printStackTrace();
		}
	}

}
