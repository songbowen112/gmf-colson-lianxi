package com.solson.dal;

/**
 * 给定一个金额
 * 有面值 1元 5元 10元 20元 50元 100元 的纸币
 * 求解最少钞票数的方案，并给出每张钞票的张数
 */
public class MoneyTest {

    public static int[] getScheme(int money) {
        int index = 5;
        int[] ints = new int[6];

        ints[index--] = money / 100;
        money = money % 100;

        ints[index--] = money / 50;
        money = money % 50;

        ints[index--] = money / 20;
        money = money % 20;

        ints[index--] = money / 10;
        money = money % 10;

        ints[index--] = money / 5;
        money = money % 5;

        ints[index] = money / 1;
        return ints;
    }

    public static void main(String[] args) {
        int money = 1241;
        int[] scheme = getScheme(money);
        for (int i : scheme) {
            System.out.print(i);
            System.out.print("  ");
        }

    }
}
