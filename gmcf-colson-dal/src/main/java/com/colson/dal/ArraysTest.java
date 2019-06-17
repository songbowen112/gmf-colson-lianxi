package com.colson.dal;

import com.colson.dal.emum.Week;

import java.util.*;

public class ArraysTest {

    public static void main(String[] args) {
        //基本类型转换为list
        int[] data = {1,2,3,4,5};
		
        List list = Arrays.asList(data);
        String a = "123";

        System.out.println("列表中的元素数量是：" + list.size());
        //列表中的元素数量是：1
        System.out.println("元素类型：" + a.getClass());
        //元素类型：class java.lang.String
        System.out.println("元素类型：" + list.get(0).getClass());
        //元素类型：class [I(int数组)

        System.out.println("前后是否相等："+data.equals(list.get(0)));
        //前后是否相等：true

        //包装类型转换为list
        Integer[] data2 = {1,2,3,4,5};

        List list3 = Arrays.asList(data2);
        String a1 = "123";

        System.out.println("列表中的元素数量是：" + list3.size());
        //列表中的元素数量是：5

        System.out.println("元素类型：" + a1.getClass());
        //元素类型：class java.lang.String
        System.out.println("元素类型：" + list3.get(0).getClass());
        //元素类型：class [I
        System.out.println("前后是否相等："+data2.equals(list.get(0)));
        //前后是否相等：false

        //对象数组转换为list
        Week[] workDays = {Week.Mon, Week.Tue, Week.Wed,Week.Thu,Week.Fri};
        List list2 = Arrays.asList(workDays);
//        list2.add(Week.Sun);
        //[Mon, Tue, Wed, Thu, Fri]
//        list2.add(com.colson.dal.Week.Sun);
//        使用Arrays.asList得到的list不能使用add/remove/clear方法，
//        asList的返回对象是一个Arrays内部类,并没有实现集合的修改方法。Arrays.asList体现的是适配器模式，只是转换接口，后台的数据仍是数组。
//
// 首先，该方法是将数组转化为list。有以下几点需要注意：
//
//　　（1）该方法不适用于基本数据类型（byte,short,int,long,float,double,boolean）
//
//　　（2）该方法将数组与列表链接起来，当更新其中之一时，另一个自动更新
//
//　　（3）不支持add和remove方法

        //如果想要可变，那就使用ArrayList再包装一下
        List<String> numbers = new ArrayList<String>(Arrays.asList("1","2","3"));
        numbers.add("4");
        System.out.println(numbers);

		System.out.println(singleNumber(new int[]{1,1,2,1,2,4,5,1,4}));
		System.out.println(singleNumber2(new int[]{1,1,2,1,2,4,5,1,4}));

		System.out.println(singleNumber(new int[]{1,4,5,1,4}));
		System.out.println(singleNumber2(new int[]{1,4,5,4,3,3,5,6,1,4}));
    }

	/**
	 * 找到不重复的数
	 * @param nums
	 * @return
	 */
	public static int singleNumber(int[] nums) {
		Map<Integer,Integer> map = new HashMap();
		for(int i=0;i<nums.length;i++) {
			if(null != map.get(nums[i])){
				map.remove(nums[i]);
			}else{
				map.put(nums[i],1);
			}
		}
		return map.keySet().iterator().next();
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
		return 0;
	}

}
