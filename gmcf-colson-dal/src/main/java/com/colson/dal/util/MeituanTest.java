package com.colson.dal.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 1
 * 编写一个函数来查找字符串数组中的最长公共后缀。
 * 如果不存在公共后缀，返回空字符串 ""。
 * 示例 1:
 * 输入: ["flight","light","right"]
 * 输出: "ight"
 * 示例 2:
 * 输入: ["dog","frog","cat"]
 * 输出: ""
 * 解释: 输入不存在公共后缀。
 *
 * 说明:
 * 所有输入只包含小写字母 a-z
 */
public class MeituanTest {

    public static String findCommonSuffix(String str1, String str2) {
        if (StringUtils.isBlank(str1) || StringUtils.isBlank(str2)) {
            return "";
        }
        StringBuilder s1 = new StringBuilder(str1).reverse();
        char[] chars = s1.toString().toCharArray();
        StringBuilder s2 = new StringBuilder(str2).reverse();
        StringBuilder commonStr = new StringBuilder(s1.toString().charAt(0));
        for (int i = 0; i < chars.length - 1; i++) {
            if (s2.toString().startsWith(commonStr.toString())){
                commonStr.append(chars[i]);
            } else {
                break;
            }
        }
        if (commonStr.toString().charAt(0) == s2.toString().charAt(0)) {
            return commonStr.reverse().toString();
        }
        return "";
    }

    public static void main(String[] args) {
        String commonSuffix = findCommonSuffix("dog", "cat");
        System.out.println(commonSuffix);
    }
}
