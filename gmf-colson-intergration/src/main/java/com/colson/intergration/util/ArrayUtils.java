package com.colson.intergration.util;

/**
 * Created by Administrator on 2019/6/15.
 */
public class ArrayUtils {

    /**
     * 在指定位置插入一个整数
     * @param array
     * @param index 数组第index的位置插入
     * @param num
     * @return
     */
    public static boolean addElem(int[] array,int index,int num) {
        if (index<1 || index>array.length+1) {
            return false;
        }
        int[] result = new int[array.length+1];
        for (int i=0;i<array.length;i++) {
            result[i] = array[i];
        }
        for (int i=result.length-1;i>=index;i--) {
            result[i] = result[i-1];
        }
        result[index-1] = num;
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

    /**
     * 俩个等长升序数组,求合并数组后的中位数。
     * (中位数在奇数长度数组是最中间的数,偶数长度数组中为第length/2个数)
     * 要求时间复杂度、空间复杂度最优。
     */
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

    /**
     * 时间复杂度、空间复杂度最优。
     */
    public static int findMiddleNum2(int[] arr1,int[] arr2) {
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
