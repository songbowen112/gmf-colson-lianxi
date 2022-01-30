package com.colson.util;

import java.util.*;
import java.util.stream.Collectors;

public class ArraysUtils {

    public static String printArr(Object[] array) {
        int index;
        //打印一维数组
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (null != array) {
            Arrays.stream(array).forEach(i -> {
                sb.append(i + " ");
            });
            index = sb.lastIndexOf(" ");
            sb.deleteCharAt(index);
        }

        sb.append("]\n");
        return sb.toString();
    }

    public static String printArr2(Object[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if(i==array.length-1) {
                    sb.append(array[i]);
                } else {
                    sb.append(array[i] + " ");
                }
            }
        }
        sb.append("]\n");
        return sb.toString();
    }

    /**
     * 在指定位置插入一个整数
     * @param array
     * @param index 数组第index的位置插入
     * @param num
     * @return
     */
    public static int[] addElem1(int[] array,int index,int num) {
        if (index<1 || index>array.length+1) {
            return null;
        }
        int[] result = new int[array.length+1];
        for (int i=0;i<array.length;i++) {
            result[i] = array[i];
        }
        for (int i=result.length-1;i>=index;i--) {
            result[i] = result[i-1];
        }
        result[index-1] = num;
        return result;
    }

    /**
     * 在指定位置插入一个整数
     * @param array
     * @param index 数组第index的位置插入
     * @param num
     * @return
     */
    public static int[] addElem2(int[] array,int index,int num) {
        if (index<1 || index>array.length+1) {
            return null;
        }
        int[] result = new int[array.length+1];
        for (int i=0;i<index-1;i++) {
            result[i] = array[i];
        }
        for (int i=index;i<result.length;i++) {
            result[i] = array[i-1];
        }
        result[index-1] = num;
        return result;
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
     * 数组升序排序（冒泡排序）
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

    /**
     * 找到不重复的数
     * @param nums
     * @return
     */
    public static List<Integer> singleNumber(int[] nums) {
        Map<Integer,Integer> map = new HashMap();
        for(int i=0;i<nums.length;i++) {
            if(null != map.get(nums[i])){
                map.put(nums[i],map.get(nums[i])+1);
            }else{
                map.put(nums[i],1);
            }
        }
        return map.entrySet().stream().filter(i -> i.getValue() == 1).map(i -> i.getKey()).collect(Collectors.toList());
    }

    /**
     * 找到不重复的数
     * @param nums
     * @return
     */
    public static int singleNumber2(int[] nums) {
        List<Integer> list = new ArrayList();
        for (int i=0;i<nums.length;i++) {
            list.add(nums[i]);
        }
        for (int j=0;j<list.size();j++) {
            if (list.indexOf(list.get(j))==list.lastIndexOf(list.get(j))){
                return list.get(j);
            }
        }
        return -1;
    }

}
