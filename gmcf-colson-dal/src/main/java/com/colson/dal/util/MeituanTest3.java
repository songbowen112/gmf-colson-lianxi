package com.colson.dal.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 1
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
        return null;
    }

}

class Goods {

    /** 商品名称 */
    String name;

    /** 商品属性 */
    List<String> attrList;

    /** 商品数量 */
    Integer count;

    public List<String> convertAttrList() {
        for (String s : attrList) {

        }

        return new ArrayList<>();
    }
}
