package com.colson.demo.sort;

import com.colson.util.ArraysUtils;

/**
 * 选择排序
 * {6 3 1 4 5}
 * 1 {3 6 4 5}  第一次外循环将最小值跟下标为0的数交换
 * 1 3 {6 4 5}  第二次外循环将最小值跟下标为1的数交换
 * 1 3 4 {6 5}  第三次外循环将最小值跟下标为2的数交换
 * 1 3 4 5 {6}  第四次外循环将最小值跟下标为3的数交换
 *
 * 看：N + N-1 + N-2 + N-3 +...
 * 比：N + N-1 + N-2 + N-3 +...
 * swap：N
 *
 * 时间复杂度计算：aN² + bN + c
 * 忽略常数项和低阶项，高阶项忽略系数
 * 最终时间复杂度：O(N²)
 */
public class SelectionSort {

    private static int[] array = {13,7,1,5,6,9,11,15,3,16,8,4,2};

    public static void main(String[] args) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        ArraysUtils.printArr(array);
    }
}
