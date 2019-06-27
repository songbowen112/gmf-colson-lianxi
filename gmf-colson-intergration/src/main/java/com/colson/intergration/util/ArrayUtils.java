package com.colson.intergration.util;

/**
 * Created by Administrator on 2019/6/15.
 */
public class ArrayUtils {

    /**
     * 在指定位置插入一个整数
     * @param array1
     * @param array2
     * @param index
     * @param num
     * @return
     */
    public static boolean addElem(int[] array1,int[] array2,int index,int num) {
        if (index<1 || index>array1.length+1) {
            return false;
        }
        for (int i=0;i<array1.length;i++) {
            array2[i] = array1[i];
        }
        for (int i=array2.length-1;i>=index;i--) {
            array2[i] = array2[i-1];
        }
        array2[index-1] = num;
        return true;
    }

    /**
     * 打印数组
     * @param array
     */
    public static void printArr(int[] array) {
        for (int i=0;i<array.length;i++) {
            if (i!=array.length-1) {
                System.out.print(array[i]+",");
            } else {
                System.out.print(array[i]);
            }
        }
        System.out.println();
    }

    /**
     * 数组升序排序
     * @param array
     */
    public static void sort(int[] array) {
        int temp = 0;
        for (int i=0;i<array.length;i++) {
            for (int j=1;j<array.length-i;j++) {
                if (array[j] < array[j-1]) {
                    temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    /**
     * 反转数组
     * @param array
     */
    public static void reverse(int[] array) {
        int temp;
        for (int i=0;i<array.length/2;i++) {
            temp = array[i];
            array[i] = array[array.length-i-1];
            array[array.length-i-1] = temp;
        }
    }

    /**
     * 反转数组
     * @param array
     */
    public static void reverse2(int[] array,int begin,int end) {
        int temp;
        for (int i=0;i<(end-begin+1)/2;i++) {
            temp = array[begin+i];
            array[begin+i] = array[end-i];
            array[end-i] = temp;
        }
    }

    /**
     * 将数组循环左移（时间复杂度O(n),O(1)）
     * @param array
     * @param step
     */
    public static int[] cycleLeft(int[] array,int step) {
        int[] result = new int[array.length];
        for (int i=array.length-1;i>=0;i--) {
            if (i-step>=0) {
                result[i-step] = array[i];
            } else {
                result[array.length+i-step] = array[i];
            }
        }
        return result;
    }

    /**
     * 将数组循环左移（时间复杂度O(n),O(1)）
     * @param array
     * @param step
     */
    public static int[] cycleLeft2(int[] array,int step) {
        reverse2(array,0,step-1);
        reverse2(array,step,array.length-1);
        reverse2(array,0,array.length-1);
        return array;
    }

    public static int findMiddleNum(int[] arr1,int[] arr2) {
        int[] arr = new int[arr1.length+arr2.length];
        int index = 0;
        for (int i=0;i<arr.length;i++) {
            if (i<arr1.length) {
                arr[i] = arr1[i];
            } else {
                arr[i] = arr2[index];
                index++;
            }
        }
        printArr(arr);
        sort(arr);
        printArr(arr);
        return arr.length%2==0?arr[arr.length/2-1]:arr[arr.length/2];
    }


}
