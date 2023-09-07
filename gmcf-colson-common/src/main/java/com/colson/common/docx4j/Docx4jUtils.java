package com.colson.common.docx4j;

import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 类描述
 *
 * @Author: 吴雨佳
 * @since: 2018/2/24 18:23
 * @update: [变更日期yyyy-MM-dd][变更人姓名][变更描述]
 */
public class Docx4jUtils {

    /**
     * 追加空白行
     * @param wordMLPackage     Word文档对象
     */
    public static void addBlankLine(WordprocessingMLPackage wordMLPackage) throws JAXBException {
        wordMLPackage.getMainDocumentPart().getContent().add(XmlUtils.unmarshalString(xmlBlankLine()));
    }

    /**
     * 插入空白行
     * @param index             插入位置
     * @param wordMLPackage     Word文档对象
     * @throws JAXBException
     * @throws IndexOutOfBoundsException
     */
    public static void insertBlankLine(int index, WordprocessingMLPackage wordMLPackage) throws JAXBException {
        wordMLPackage.getMainDocumentPart().getContent().add(index, XmlUtils.unmarshalString(xmlBlankLine()));
    }

    public static String xmlBlankLine() {
        return "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" ></w:p>";
    }

    /**
     * 获取图片文件的字节数组
     * @param filePath
     * @return
     * @throws IOException
     */
    public byte[] getImageBytes(String filePath) throws IOException {
        // The image to add
        File file = new File(filePath);
        byte[] bytes = null;

        try(InputStream is = new java.io.FileInputStream(file )) {
            // Our utility method wants that as a byte array
            long length = file.length();
            // You cannot create an array using a long type.
            // It needs to be an int type.
            if (length > Integer.MAX_VALUE) {
                System.out.println("File too large!!");
            }
            bytes = new byte[(int)length];
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                    && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }
            // Ensure all the bytes have been read in
            if (offset < bytes.length) {
                System.out.println("Could not completely read file "+file.getName());
            }
        } catch (Exception e) {
            throw e;
        }
        return bytes;
    }

    /**
     * 将html语言的段落处理成一行一行的段落
     * @param htmlParagraph html段落
     * @return  每行集合数据
     */
    public static List<String> htmlParagraphSplit(String htmlParagraph) {
        if (null == htmlParagraph || htmlParagraph.trim().isEmpty()) {
            // 如果传入的是字符串, 则返回空集合
            return new ArrayList<>();
        }
        // 将段落开始符号和段落结束符号替换为空白符, 同时将换行符处理一下, 空格处理一下
        htmlParagraph = htmlParagraph
                .replaceAll("<p>", "")
                .replaceAll("</p>", "\n")
                .replaceAll("<br/>", "\n")
                .replaceAll("<br>", "\n")
                .replaceAll("&nbsp;", " ")
                .replaceAll("<(?!img).+?>", "")
                .replaceAll("</.+?>", "\n")
                .replaceAll("<", "\n<")
                .replaceAll(">", ">\n");

        // 按照换行符切割字符串
        List<String> results = new ArrayList<>();
        String[] split = htmlParagraph.split("\n");
        for (String str : split) {
            if (str.trim().isEmpty()) {
                continue;
            }
            results.add(str);
        }
        return results;
    }

    /**
     * 特殊符号过滤
     * @param chapterDescription
     * @return
     */
    public static String paragraphSpecialFilter(String chapterDescription) {
        return chapterDescription.replaceAll("\u001F", "");
    }
}
