package com.solson.dal;

/**
 * 将俩个有序数组进行排序
 */
public class ArraysSortTest {

    public static void main(String[] args) {
        int[] a = {1,3,9,100};
        int[] b = {2,3,4,5,6,7,8,21,22,44};

        int[] result = new int[a.length+b.length];
        int i = 0;//a数组的下标
        int j = 0;//b数组的下标
        int k = 0;//result数组的下标
        while (i<a.length && j<b.length) {
            if (a[i] < b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }
        while (i==a.length && j<b.length) {
            result[k++] = b[j++];
        }

        while (j==b.length && i<a.length) {
            result[k++] = a[i++];
        }

        for (int index=0;index<result.length;index++) {
            System.out.print(result[index]+" ");
        }
    }
}
