package com.colson.dal.excel;

import com.colson.dal.excel.bean.Column;
import com.colson.dal.excel.bean.NxmlParser;
import com.colson.dal.excel.bean.Prop;
import com.colson.dal.excel.bean.XmlParser;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.*;

public class ParseUtils {
//	private static Logger logger = GmfLogger.getLogger(ParseUtils.class);
	public static final String fileName = "/excelRead.xml";
	public static final String nfileName = "/excelWrite.xml";
	
	/**
	 * 
	 * @param xmlStr xml字符串
	 * @return
	 */
	public static Document getDocByString(String xmlStr){
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
//			logger.error("发生异常 msg={}","原因",e);
		}
		return doc;
	}
	
	/**
	 * 
	 * @param xmlFilePath
	 * @return
	 */
	public static Document getDocByPath(String xmlFilePath){
		 SAXReader reader = new SAXReader();
	        Document document = null;
	        try {
	            InputStream in = ParseUtils.class.getResourceAsStream(xmlFilePath);
	            document = reader.read(in);
	        } catch (DocumentException e) {
	            System.out.println("读取classpath下xmlFileName文件发生异常，请检查CLASSPATH和文件名是否存在！");
//				logger.error("发生异常 msg={}","原因",e);
	        }
	        return document; 
	}
	

	/**
	 * 解析
	 */
	public static Map<String, XmlParser> analysis() {
		Document document = getDocByPath(fileName);
		Element root = document.getRootElement();
		Map<String, XmlParser> map = new HashMap<String, XmlParser>();
		for (Iterator iter = root.elementIterator("parse"); iter.hasNext();) {
			XmlParser xp = new XmlParser();
			Element e1 = (Element) iter.next();
			// 获取parse节点的id属性的值
			Attribute aId = e1.attribute("id");
			xp.setId(aId.getValue());
			// 获取parse节点的name属性的值
			Attribute name = e1.attribute("name");
			xp.setName(name.getValue());
			// 获取parse节点的start属性的值
			Attribute start = e1.attribute("start");
			xp.setStart(Integer.parseInt(start.getValue()));
			//获取parse节点的split属性的值
			Attribute split = e1.attribute("split");
			xp.setSplit(split.getValue());
			// 获取parse节点的mode属性的值
			Attribute mode = e1.attribute("mode");
			xp.setMode(mode.getValue());
			xp.setClassName(e1.element("class").getText());
			// 获取parse节点的cardNum属性的值
			Attribute cardNum = e1.attribute("cardNum");
			xp.setCardNum(cardNum.getValue());
			
			Element e2 = e1.element("props");
			List<Prop> list = new ArrayList<Prop>();
			for (Iterator iter2 = e2.elementIterator(); iter2.hasNext();) {
				Element e3 = (Element) iter2.next();
				Attribute idx = e3.attribute("idx");
				Attribute pname = e3.attribute("pname");
				Attribute handers = e3.attribute("handers");
				Prop p = new Prop(Integer.parseInt(idx.getValue()),pname.getValue(),handers.getValue());
				list.add(p);
			}
			
			xp.setProps(list);
			map.put(aId.getValue(), xp);
		}
		return map;
	} 
	
	/**
	 * 新的解析
	 * @return
	 */
	public static Map<String, NxmlParser> nanalysis(){
		Document document = getDocByPath(nfileName);
		Element root = document.getRootElement();
		Map<String, NxmlParser> map = new HashMap<String, NxmlParser>();
		for (Iterator iter = root.elementIterator("wobj"); iter.hasNext();) {
			NxmlParser nxp = new NxmlParser();
			Element e1 = (Element) iter.next();
			Attribute aId = e1.attribute("id");
			if(null!=aId){
//				if ("ExpScmCardInfor".equals(aId.getValue())) {//同一个类不同导出
//					nxp.setId("ScmCardInfor");
//				}else {
					nxp.setId(aId.getValue());
//				}
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
			for (Iterator iter2 = e2.elementIterator(); iter2.hasNext();) {
				Element e3 = (Element) iter2.next();
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
//			if ("ExpScmCardInfor".equals(aId.getValue())) {//同一个类不同导出
//				map.remove("ScmCardInfor");
//				map.put("ScmCardInfor", nxp);
//			} else {
				map.put(aId.getValue(), nxp);
//			}
		}
		
		
		return map;
	}
	
	public static void main(String[] args) {
		NxmlParser nxp = nanalysis().get("ZipScmCardInfor");
		System.out.println(nxp.getColumns().get(1).getType());
	}
}
