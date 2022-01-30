package com.solson.dal;

import com.colson.common.emum.Week;
import java.util.*;
import com.colson.util.ArraysUtils;
import org.junit.Test;

public class ArraysTest {
    /**
     * Arrays.asList(array[])
     * 首先，该方法是将数组转化为list。有以下几点需要注意：
     * 1）该方法不适用于基本数据类型（byte,short,int,long,float,double,boolean）
     * 2）该方法将数组与列表链接起来，当更新其中之一时，另一个自动更新
     * 3）得到的list不支持add/remove/clear方法
     * asList的返回对象是一个Arrays内部类,并没有实现集合的修改方法。
     * Arrays.asList体现的是适配器模式，只是转换接口，后台的数据仍是数组。
     */
    @Test
    public void testAsList() {
        //基本类型转换为list
        int[] data = {1,2,3,4,5};
        List list = Arrays.asList(data);
        String a = "123";
        System.out.println("列表中的元素数量是：" + list.size());//列表中的元素数量是：1
        System.out.println("元素类型：" + a.getClass());//元素类型：class java.lang.String
        System.out.println("元素类型：" + list.get(0).getClass());//元素类型：class [I(int数组)
        System.out.println("前后是否相等："+data.equals(list.get(0)));//前后是否相等：true

        //包装类型转换为list
        Integer[] data2 = {1,2,3,4,5};
        List list3 = Arrays.asList(data2);
        System.out.println("列表中的元素数量是：" + list3.size());//列表中的元素数量是：5
        System.out.println("元素类型：" + list3.get(0).getClass());//元素类型：class [I
        System.out.println("前后是否相等："+data2.equals(list.get(0)));//前后是否相等：false

        //对象数组转换为list
        Week[] workDays = {Week.Mon, Week.Tue, Week.Wed,Week.Thu,Week.Fri};
        List list2 = Arrays.asList(workDays);
        System.out.println(list2);//[Mon, Tue, Wed, Thu, Fri]
//        list2.add(Week.Sun);//java.lang.UnsupportedOperationException

        //如果想要可变，那就使用ArrayList再包装一下
        List<String> numbers = new ArrayList<String>(Arrays.asList("1","2","3"));
        numbers.add("4");
        System.out.println(numbers);
    }

    /**
     * 找到不重复的数
     */
    @Test
    public void testSingleNumber() {
        System.out.println(ArraysUtils.singleNumber(new int[]{1,6,1,4,5,1,4}));
        System.out.println(ArraysUtils.singleNumber2(new int[]{1,4,9,5,4,3,3,5,6,1,4}));
    }

    /**
     * 数组Api
     */
    public void testArrayApi() {
        int[] array = {1,2,3,4,2,5,7,1,3,1,6,8,2,3,66};
        ArraysUtils.printArr(array);

        int[] array2 = new int[array.length+1];
        int[] result1 = ArraysUtils.addElem1(array,3,999);
        System.out.print("插入元素结果:");
        ArraysUtils.printArr(result1);

        int[] result2 = ArraysUtils.addElem2(array2,3,999);
        System.out.print("插入元素结果:");
        ArraysUtils.printArr(result2);
        ArraysUtils.sort(array);
        ArraysUtils.printArr(array);
        ArraysUtils.reverse(array);
        ArraysUtils.printArr(array);
        int[] array3 = {1,13,2,13,4,5,7};
        int[] array4 = {10,12,6,8,11};
        System.out.println("找中位数:"+ArraysUtils.findMiddleNum(array3,array4));

        //找中位数第二种方法
        int[] array5 = {1,3,4,7,8,10,12};
        int[] array6 = {8,10,12,16,17,19,22};
        System.out.println("找中位数:"+ArraysUtils.findMiddleNum2(array5,array6));

        ArraysUtils.printArr(array3);
        int[] array7 = ArraysUtils.cycleLeft2(array3,1);
        ArraysUtils.printArr(array7);

        System.out.println(1 << 5);
    }
}
