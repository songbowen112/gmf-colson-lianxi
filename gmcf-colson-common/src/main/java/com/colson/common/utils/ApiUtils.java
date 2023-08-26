package com.colson.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.colson.common.constants.ApiConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by gaozhagen on 2016/10/9.
 */
public class ApiUtils {
    private static final Logger logger = LoggerFactory.getLogger(ApiUtils.class);

    private ApiUtils() {
    }

    /**
     * 格式化json数据异常信息.
     *
     * @param e
     * @param map
     */
    public static void formatJsonException(Exception e, Map map) {
        map.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_FAIL_1);
        map.put(ApiConstants.STATUS_DESC, e.getMessage());
    }

    public static void formatJsonException(Exception e, JSONObject rstJson) {
        rstJson.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_FAIL_1);
        rstJson.put(ApiConstants.STATUS_DESC, e.getMessage());
    }

    /**
     * 格式化有data数据的json异常信息.
     *
     * @param e
     * @param map
     * @param data
     */
    public static void formatJsonExceptionWithData(Exception e, Map map, String data) {
        map.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_FAIL_1);
        map.put(ApiConstants.STATUS_DESC, e.getMessage());
        map.put(ApiConstants.DATA, data == null ? "" : data);
    }

    public static void formatJsonExceptionWithData(Exception e, JSONObject rstJson, String data) {
        rstJson.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_FAIL_1);
        rstJson.put(ApiConstants.STATUS_DESC, e.getMessage());
        rstJson.put(ApiConstants.DATA, data == null ? "" : data);
    }

    /**
     * 格式化参数错误等非数据库异常信息.
     *
     * @param desc
     * @param map
     */
    public static void formatJsonError(String desc, Map map) {
        map.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_FAIL_1);
        map.put(ApiConstants.STATUS_DESC, desc == null ? "" : desc);
    }

    public static void formatJsonError(String desc, JSONObject rstJson) {
        rstJson.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_FAIL_1);
        rstJson.put(ApiConstants.STATUS_DESC, desc == null ? "" : desc);
    }

    /**
     * 格式化有data(如：参数错误)的非数据库异常信息.
     *
     * @param desc
     * @param map
     * @param data
     */
    public static void formatJsonErrorWithData(String desc, Map map, String data) {
        map.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_FAIL_1);
        map.put(ApiConstants.STATUS_DESC, desc == null ? "" : desc);
        map.put(ApiConstants.DATA, data == null ? "" : data);
    }

    public static void formatJsonErrorWithData(String desc, JSONObject rstJson, String data) {
        rstJson.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_FAIL_1);
        rstJson.put(ApiConstants.STATUS_DESC, desc == null ? "" : desc);
        rstJson.put(ApiConstants.DATA, data == null ? "" : data);
    }

    /**
     * 格式化正常的有data数据的信息.
     *
     * @param map
     * @param obj
     */
    public static void formatJsonWithData(Map map, Object obj) {
        map.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_SUCCESS);
        map.put(ApiConstants.STATUS_DESC, ApiConstants.STATUS_DESC_SUCCESS);
        map.put(ApiConstants.DATA, obj == null ? "" : obj);
    }

    public static void formatJsonWithData(JSONObject rstJson, Object obj) {
        rstJson.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_SUCCESS);
        rstJson.put(ApiConstants.STATUS_DESC, ApiConstants.STATUS_DESC_SUCCESS);
        rstJson.put(ApiConstants.DATA, obj == null ? "" : obj);
    }

    /**
     * 格式化正常的无data数据的信息.
     *
     * @param map
     */
    public static void formatJson(Map map) {
        map.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_SUCCESS);
        map.put(ApiConstants.STATUS_DESC, ApiConstants.STATUS_DESC_SUCCESS);
    }

    /**
     * 格式化401seesion失效的json对象.
     *
     * @param map
     */
    public static void formatSessionInvalid(Map map, String data) {
        map.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_FAIL_401);
        map.put(ApiConstants.STATUS_DESC, ApiConstants.STATUS_DESC_FAIL_401);
        map.put(ApiConstants.DATA, data == null ? "" : data);
    }

    public static void formatSessionInvalid(JSONObject rstJson, Object obj) {
        rstJson.put(ApiConstants.STATUS_CODE, ApiConstants.STATUS_CODE_FAIL_401);
        rstJson.put(ApiConstants.STATUS_DESC, ApiConstants.STATUS_DESC_FAIL_401_2);
        rstJson.put(ApiConstants.DATA, obj == null ? "" : obj);
    }

    public static boolean checkString(String str) {
        if (str.equals("") || str.trim().equals("")) {
            return false;
        }
        return true;
    }

    public static String SimpleEncode(String str) {
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++) {
            array[i] = MapChar(array[i]);
        }
        return new String(array);
    }

    private static char MapChar(char c) {
        if (c >= '0' && c <= '4') {
            return (char) (c + 5);
        } else if (c >= '5' && c <= '9') {
            return (char) (c - 5);
        } else if (c >= 'A' && c <= 'M') {
            return (char) ('n' + (c - 'A'));
        } else if (c >= 'n' && c <= 'z') {
            return (char) (c - 'n' + 'A');
        } else if (c >= 'N' && c <= 'Z') {
            return (char) ('m' - (c - 'N'));
        } else if (c >= 'a' && c <= 'm') {
            return (char) ('N' + ('m' - c));
        } else if (c == '@') {
            return '-';
        } else if (c == '-') {
            return '@';
        } else {
            return c;
        }
    }

    public static String SimpleDecode(String str) {
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++) {
            array[i] = MapChar(array[i]);
        }
        return new String(array);
    }

    /**
     * 根据参数或者所有参数获取日志信息.
     *
     * @return
     */
    /*
    public static String getLogString(HttpServletRequest request, String... paramters) {
        StringBuilder sb = new StringBuilder("参数信息：");
        if (request != null) {
            if (paramters == null || paramters.length == 0) {
                Map<String, Object> params = (Map<String, Object>) request.getParameterMap();
                if (params != null) {
                    for (Map.Entry<String, Object> entry : params.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        if (value instanceof String[]) {
                            String[] values = (String[]) value;
                            sb.append(key + "=" + Arrays.toString(values) + ",");
                        } else {
                            sb.append(key + "=" + value + ",");
                        }
                    }
                } else {
                    sb.append("parameterMap is null");
                }
            } else {
                for (String param : paramters) {
                    sb.append(param + "=" + request.getParameter(param) + ",");
                }
            }
        } else {
            sb.append("request is null");
        }

        return sb.toString();
    }
    */
    private static String getLogStringFromMap(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder("参数信息：");
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof String[]) {
                    String[] values = (String[]) value;
                    sb.append(key + "=" + Arrays.toString(values) + ",");
                } else {
                    sb.append(key + "=" + value + ",");
                }
            }
        } else {
            sb.append("parameterMap is null");
        }
        return sb.toString();
    }

    public static void debugLogParam(Map param) {
        logger.debug(getLogStringFromMap(param));
    }

    /**
     * string转List<Integer>
     *
     * @param str
     * @return
     */
    public static List<Integer> convertStringToInteger(String str) {
        if ("".equals(str) || null == str) {
            return null;
        }
        String[] strs = str.split("\\,");
        List<Integer> integers = new ArrayList<Integer>();
        for (String s : strs) {
            integers.add(Integer.parseInt(s));
        }
        return integers;
    }

    public static List<String> convertStringToList(String str) {
        if ("".equals(str) || null == str) {
            return null;
        }
        String[] strs = str.split("\\,");
        List<String> res = new ArrayList<String>();
        for (String s : strs) {
            res.add(s);
        }
        return res;
    }

    public static Integer getLegalIntegerFromStringParam(String param) {
        return (param != null && !"".equals(param)) ? Integer.parseInt(param) : null;
    }


    public static void main(String... args) {
    }
}
