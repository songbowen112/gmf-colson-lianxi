package com.colson.intergration;


import com.colson.intergration.util.ArrayUtils;

/**
 * Created by Administrator on 2019/6/15.
 */
public class Test1 {

    public static void main(String[] args) {
        int[] array = {1,2,3,4,2,5,7,1,3,1,6,8,2,3,66};
        int[] array2 = new int[array.length+1];
        ArrayUtils.addElem(array,array2,3,999);
        ArrayUtils.printArr(array2);

        ArrayUtils.addElem(array,array2,3,999);
        ArrayUtils.sort(array2);
        ArrayUtils.printArr(array2);
        ArrayUtils.reverse(array);
        ArrayUtils.printArr(array);
        int[] array3 = {1,13,2,13,4,5,7};
        int[] array4 = {10,12,6,8,11};
        System.out.println(ArrayUtils.findMiddleNum(array3,array4));

        //找中位数第二种方法
        int[] array5 = {1,3,4,7,8,10,12};
        int[] array6 = {8,10,12,16,17,19,22};
        System.out.println(ArrayUtils.findMiddleNum2(array5,array6));

        ArrayUtils.printArr(array3);
        int[] array7 = ArrayUtils.cycleLeft2(array3,1);
        ArrayUtils.printArr(array7);
    }




}
