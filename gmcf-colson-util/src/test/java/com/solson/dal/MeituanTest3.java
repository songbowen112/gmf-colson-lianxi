package com.solson.dal;


import com.colson.util.Md5Utils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 3
 * 请根据下面定义的商品Class，设计程序能够合并名称与属性完全相同的商品。
         *示例：
         * 【合并前】的商品：
         *红富士， [红色,纯天然,很甜]，10个
         *红富士， [很甜,红色,纯天然]，2个
         *红富士， [纯天然,红色]，5个
         *小金苹果， [纯天然,红色]，20个

         * 【合并后】的商品：
         *红富士，[红色,纯天然,很甜]，12个
         *红富士，[纯天然,红色]，5个
         *小金苹果，[纯天然,红色]，20个
         *提示：可以修改Goods类，可以增加类和方法
 */
public class MeituanTest3 {

    static List<Goods> mergeGoods(List<Goods> goodsList) {
        Map<String, Goods> goodsMap = new HashMap<>();
        goodsList.forEach(i -> {
            String md5 = i.getMd5();

            if (goodsMap.containsKey(md5)) {
                Goods goods = goodsMap.get(md5);
                goods.setCount(goods.getCount() + i.getCount());
                goodsMap.put(md5, goods);
            } else {
                goodsMap.put(md5,i);
            }
        });
        return goodsMap.values().stream().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Goods> list = new ArrayList<>();
        Goods g1 = new Goods("红苹果", Arrays.asList("脆","delicious","甜"),6);
        Goods g2 = new Goods("蓝苹果", Arrays.asList("脆","甜"),4);
        Goods g3 = new Goods("红苹果", Arrays.asList("甜","脆","delicious"),3);
        Goods g4 = new Goods("红苹果", Arrays.asList("甜","脆","delicious"),5);
        Goods g5 = new Goods("红苹果", Arrays.asList("甜","脆","delicious","脆"),8);
        Goods g6 = new Goods("红苹果", Arrays.asList("甜","脆","delicious","脆"),8);
        Goods g7 = new Goods("蓝苹果", Arrays.asList("甜","脆","delicious","脆"),19);
        Goods g8 = new Goods("蓝苹果", Arrays.asList("甜","脆","delicious","脆"),19);
        list.add(g1);
        list.add(g2);
        list.add(g3);
        list.add(g4);
        list.add(g5);
        list.add(g6);
        list.add(g7);
        list.add(g8);

        List<Goods> mergeGoods = mergeGoods(list);
        mergeGoods.forEach(i -> {
            System.out.println(i.getName() + "------" + i.getAttrs() + "------" + i.getCount());
        });
    }

}

class Goods {

    /** 商品名称 */
    private String name;

    /** 商品属性 */
    private String attrs;

    /** 商品属性 */
    private List<String> attrList;

    /** 商品数量 */
    private Integer count;

    /** md5 */
    private String md5;

    public Goods() {
    }

    public Goods(String name, List<String> attrList, Integer count) {
        this.name = name;
        this.attrList = attrList;
        this.count = count;
        this.attrs = convertAttrList();
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<String> getAttrList() { return attrList; }

    public void setAttrList(List<String> attrList) { this.attrList = attrList; }

    public Integer getCount() { return count; }

    public void setCount(Integer count) { this.count = count; }

    public String getAttrs() { return attrs; }

    public void setAttrs(String attrs) { this.attrs = attrs; }

    public String getMd5() { return md5; }

    public void setMd5(String md5) { this.md5 = md5; }

    public String convertAttrList() {
        String s = attrList.stream().sorted().collect(Collectors.toList()).toString();
        setMd5(Md5Utils.MD5(getName() + s));
        return s;
    }
}
