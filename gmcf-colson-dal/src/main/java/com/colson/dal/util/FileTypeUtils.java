package com.colson.dal.util;

import org.slf4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileTypeUtils {
    public static final Map<String, String> FILE_TYPE_MAP = new HashMap<>();

    static {
        FILE_TYPE_MAP.put("FFD8FF", "jpg");
        FILE_TYPE_MAP.put("89504E47", "png");
        FILE_TYPE_MAP.put("47494638", "gif");
        FILE_TYPE_MAP.put("49492A00", "tif");
        FILE_TYPE_MAP.put("424D", "bmp");
        FILE_TYPE_MAP.put("41433130", "dwg"); // CAD
        FILE_TYPE_MAP.put("38425053", "psd");
        FILE_TYPE_MAP.put("7B5C727466", "rtf"); // 日记本
        FILE_TYPE_MAP.put("3C3F786D6C", "xml");
        FILE_TYPE_MAP.put("68746D6C3E", "html");
        FILE_TYPE_MAP.put("44656C69766572792D646174653A", "eml"); // 邮件
        FILE_TYPE_MAP.put("D0CF11E0", "doc/xls"); // doc 或 xls
        FILE_TYPE_MAP.put("5374616E64617264204A", "mdb");
        FILE_TYPE_MAP.put("252150532D41646F6265", "ps");
        FILE_TYPE_MAP.put("255044462D312E", "pdf");
        FILE_TYPE_MAP.put("504B0304", "docx/xlsx"); //docx 或 xlsx
        FILE_TYPE_MAP.put("52617221", "rar");
        FILE_TYPE_MAP.put("57415645", "wav");
        FILE_TYPE_MAP.put("41564920", "avi");
        FILE_TYPE_MAP.put("2E524D46", "rm");
        FILE_TYPE_MAP.put("000001BA", "mpg");
        FILE_TYPE_MAP.put("000001B3", "mpg");
        FILE_TYPE_MAP.put("6D6F6F76", "mov");
        FILE_TYPE_MAP.put("3026B2758E66CF11", "asf");
        FILE_TYPE_MAP.put("4D546864", "mid");
        FILE_TYPE_MAP.put("1F8B08", "gz");
        FILE_TYPE_MAP.put("4D5A9000", "exe/dll");
        FILE_TYPE_MAP.put("75736167", "txt");
    }
    /**
     * 根据文件路径获取文件头信息
     *
     * @param filePath 文件路径
     * @return 文件名称信息
     */
    public static String getFileType(String filePath) {
        return FILE_TYPE_MAP.get(getFileHeader(filePath));
    }

    /**
     * 根据文件路径获取文件头信息
     *
     * @param filePath 文件路径
     * @return 文件头信息
     */
    public static String getFileHeader(String filePath) {
        FileInputStream is = null;
        String value = null;
        try {
            is = new FileInputStream(filePath);
            byte[] b = new byte[4];
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return value;
    }

    /**
     * 将要读取文件头信息的文件的byte数组转换成string类型表示
     *
     * @param bytes 要读取文件头信息的文件的byte数组
     * @return 文件头信息
     */
    private static String bytesToHexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        if (bytes == null || bytes.length <= 0) {
            throw new RuntimeException("文件不能为空");
        }
        String hv;
        for (int i = 0; i < bytes.length; i++) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toHexString(bytes[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }

    /**
     * 判断文件是否为xls doc
     * @return boolean
     * */
    public static boolean xlsType(String filePath){
        String fileHeader = getFileHeader(filePath);
        if("D0CF11E0".equals(fileHeader)){
            return true;
        }
        return false;
    }

    /**
     * 判断文件是否为xlsx docx,是返回true
     * @return boolean
     * */
    public static boolean xlsxType(String filePath){
        String fileHeader = getFileHeader(filePath);
        if("504B0304".equals(fileHeader)){
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        System.out.println(getFileType("/Users/zhaokun/Downloads/2b64a61af7d11dbc.xls"));
        System.out.println(xlsxType("/Users/zhaokun/Documents/ceshi.xlsx"));
    }
}
