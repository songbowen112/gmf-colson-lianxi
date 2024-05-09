package com.solson.dal;

import com.colson.common.emum.Week;
import com.colson.util.ArraysUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReverseStrTest {
    /**
     * 力扣541. 反转字符串 II
     * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     *
     * 示例 1：
     * 输入：s = "abcdefg", k = 2
     * 输出："bacdfeg"
     * 示例 2：
     * 输入：s = "abcd", k = 2
     * 输出："bacd"
     *
     * 提示：
     * 1 <= s.length <= 104
     * s 仅由小写英文组成
     * 1 <= k <= 104
     */
    @Test
    public void reverseStr() {
        String s = "abcdefg";
        int k = 8;
        //三指针法--自己推导
        char[] c = s.toCharArray();
        int fast = 0;//快指针
        int middle = 0;//中指针
        int slow = 0;//慢指针
        while(fast < 2*k-1) {
            if(middle < k-1) {
                middle++;
            }
            fast++;
        }
        //fast指针没有超出字符数组执行反转
        while(fast < c.length) {
            for(int i=0;i<k/2;i++) {
                char t = c[slow+i];
                c[slow+i] = c[middle-i];
                c[middle-i] = t;
            }
            slow = fast+1;
            middle = slow+k-1;
            fast = middle+k;
        }
        //处理长度小于2k个字符的情况
        if(fast >= c.length && slow < c.length-1) {
            //如果剩余字符少于 k 个，则将剩余字符全部反转
            if(middle > c.length || k > c.length) {
                middle = c.length-1;
                for(int i=0;i<(c.length-slow)/2;i++) {
                    char t = c[slow+i];
                    c[slow+i] = c[middle-i];
                    c[middle-i] = t;
                }
                //如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样
            } else if(middle <= c.length) {
                for(int i=0;i<k/2;i++) {
                    char t = c[slow+i];
                    c[slow+i] = c[middle-i];
                    c[middle-i] = t;
                }
            }
        }
        System.out.println(String.valueOf(c));
    }
}
