package com.colson.demo.sort;

import com.colson.util.ArraysUtils;

/**
 * 冒泡排序
 * 将相邻的两个数两两比较大小，左面比右面大就交换，最终冒出去的就是最大的数
 * 第一次外循环确定length-1下标的数
 * {6 3 1 4 5}
 * {3 6} 1 4 5
 * 3 {1 6} 4 5
 * 3 1 {4 6} 5
 * 3 1 4 {5 6}
 * 第二次外循环确定length-2下标的数
 * {3 1 4 5 6}
 * {1 3} 4 5 6
 * 1 {3 4} 5 6
 * 1 3 {4 5} 6
 * ...
 *
 * 看：N + N-1 + N-2 + N-3 +...
 * 比：N + N-1 + N-2 + N-3 +...
 * swap：N
 */
public class BubbleSort {

    private static int[] array = {13,7,1,5,6,9,11,15,3,16,8,4,2};

    public static void main(String[] args) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length-i; j++) {
                if (array[j-1] > array[j]) {
                    //通过三次异或交换两个数
                    array[j-1] = array[j-1] ^ array[j];
                    array[j] = array[j-1] ^ array[j];
                    array[j-1] = array[j-1] ^ array[j];
                }
            }

        }
        ArraysUtils.printArr(array);
    }
}
