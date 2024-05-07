package com.solson.dal;

/**
 * 将俩个有序唯一数组取交集
 *
 * Mysql查询的访问方法--Intersection索引合并
 * single_table有两个字段: key1 varchar(100), key3 varchar(100)
 * 这两个字段分别建立二级索引: key idx_key1 (key1), key idx_key3 (key3)
 *
 * select * from single_table where key1 = 'a' and key3 = 'b';
 * 使用多个索引来完成一次查询的执行方法称为index merge（索引合并），
 * Intersection索引合并指的就是对从不同索引中扫描到的记录的id（二级索引记录都是按照主键值排序的）取交集，只为这些id值执行回表操作
 * 这句sql的访问方法就是Intersection（交集）索引合并
 * Mysql是怎样运行的--p174
 */
public class ArraysSortIntersectionTest {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 5, 7, 9 ,100 ,101};
//        int[] a = {2, 3, 4, 5, 6, 8, 9, 10 ,11 ,21, 42, 100};
        int[] b = {2, 3, 4, 5, 6, 8, 9, 10 ,11 ,21, 42, 100};

        int minLen = a.length < b.length?a.length:b.length;
        int[] result = new int[minLen];
        int i = 0;//a数组的下标
        int j = 0;//b数组的下标
        int k = 0;//result数组的下标
        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {//交集数字存入result数组
                result[k++] = a[i];
                i++;
                j++;
            } else if (a[i] > b[j]) {//数值小的忽略，取数组的下一个
                j++;
            } else {
                i++;
            }
        }

        for (int index = 0; index < result.length; index++) {
            System.out.print(result[index] + " ");
        }
    }

}
