package com.colson.dal.util;

import java.util.*;

/**
 * 2
 * 编写程序实现：
 * 输入两个字符串，从第一字符串中删除第二个字符串中所有的字符
 * 示例：
 *   输入They are students. 和 aeiou 输出 Thy r stdnts.
 */
public class MeituanTest2 {

    public static String deleteDuplicateChars(String str1, String str2) {

        char[] chars = str2.toCharArray();
        char[] result = str1.toCharArray();
        List<Character> list1 = new ArrayList();
        List<Character> list2 = new ArrayList();
        for (char c : result) {
            list1.add(c);
        }
        for (char c : chars) {
            list2.add(c);
        }
        Set<Character> characters = new HashSet<>();
        for (Character aChar : list2) {
            if (str1.contains(aChar.toString())) {
                characters.add(aChar);
            }
        }
        Iterator<Character> iterator = list1.iterator();
        List<Character> copy = new ArrayList<>();
        while (iterator.hasNext()) {
            boolean flag = false;
            Character next = iterator.next();
            for (Character character : characters) {
                if (next == character) {
                    flag = true;
                    iterator.remove();
                    break;
                }
            }
            if (!flag)
                copy.add(next);
        }
        return copy.toString();
    }

    public static <T> List<T> copyIterator(Iterator<T> iter) {
        List<T> copy = new ArrayList<T>();
        while (iter.hasNext())
            copy.add(iter.next());
        return copy;
    }

    public static void main(String[] args) {
        String aeiou = deleteDuplicateChars("They are students. aaeeio111uuu", "aeiou");
        System.out.println(aeiou);
    }
}
