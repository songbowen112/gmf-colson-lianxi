package com.colson.dal.util;

public class ArrayUtils {

    /**
     * 俩个等长升序数组,求合并数组后的中位数(中位数在奇数长度数组是最中间的数,偶数长度数组中为第length/2个数)
     */
    public static int getMiddleNum(int[] arr1,int[] arr2) {
        int s1,e1,m1,s2,e2,m2;
        int[] arr = new int[arr1.length+arr2.length];

        s1 = 0;
        e1 = arr1.length-1;
        m1 = arr1.length%2==0?arr1[arr1.length/2-1]:arr1[arr1.length/2];
        s2 = 0;
        e2 = arr2.length-1;
        m2 = arr2.length%2==0?arr2[arr2.length/2-1]:arr2[arr2.length/2];
        if (m1==m2) {
            return m1;
        }
        while (s1!=e1 && s2!=e2) {
            if (m1<m2) {
                s1 = (s1+e1)/2-1;
                e2 = (s2+e2)/2;
            } else {
                s2 = (s2+e2)/2-1;
                e1 = (s1+e1)/2;
            }
        }
        return m1;

    }
}
