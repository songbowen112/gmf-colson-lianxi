package com.colson.dal.util;

public class ArrayUtils {

    /**
     * 俩个等长升序数组,求合并数组后的中位数。
     * (中位数在奇数长度数组是最中间的数,偶数长度数组中为第length/2个数)
     * 要求时间复杂度、空间复杂度最优。
     */
    public static int getMiddleNum(int[] arr1,int[] arr2) {
        int s1,e1,m1,s2,e2,m2;
        s1 = 0;
        e1 = arr1.length-1;
        s2 = 0;
        e2 = arr2.length-1;

        while (s1!=e1 || s2!=e2) {
            m1 = (s1+e1)/2;
            m2 = (s2+e2)/2;

            if (arr1[m1] == arr2[m2]) {
                return arr1[m1];
            }

            if (arr1[m1]<arr2[m2]) {
                if ((s1+e1)%2==0) {
                    s1 = m1;
                    e2 = m2;
                } else {
                    s1 = m1+1;
                    e2 = m2;
                }
            } else {
                if ((s2+e2)%2==0) {
                    s2 = m2;
                    e1 = m1;
                } else {
                    s2 = m2+1;
                    e1 = m1;
                }
            }
        }
        return arr1[s1]<arr2[s2]?arr1[s1]:arr2[s2];

    }
}
