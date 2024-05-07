package com.solson.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 有一对兔子，从出生后第三个月起每个月都生一对兔子，
 * 小兔子长到第三月后每个月又生一对兔子，假如兔子都不死，问第50个月的兔子总数为多少？
 *
 * 这个问题是一个经典的斐波那契数列问题。
 * 斐波那契数列是这样一个数列：0，1，1，2，3，5，8，13，21，...。
 * 其中，每个数字（从第三个数字开始）都是前两个数字的和。
 *
 * 在这个兔子问题中，我们可以这样理解：
 * 第1个月：1对兔子
 * 第2个月：1对兔子
 * 第3个月：开始生小兔子，所以仍然是1对兔子
 * 第4个月：老兔子生了一对新兔子，所以总共有2对兔子
 * 第5个月：老兔子又生了一对新兔子，同时第4个月生的小兔子还没有生育能力，所以总共有3对兔子
 * ...以此类推
 * 可以看出，每个月的兔子对数都符合斐波那契数列的规律。
 *
 * 为了求解第50个月的兔子总数，我们需要计算斐波那契数列的第50项。
 *
 * 斐波那契数列的递推公式为：
 * F(n) = F(n-1) + F(n-2)，其中 F(1) = 1, F(2) = 1。
 *
 * 我们可以使用循环或递归的方式来计算斐波那契数列的第50项。
 * 但考虑到效率问题，循环通常比递归更优。
 */
public class FibonacciSequenceTest {
    public static void main(String[] args) {
        System.out.println(fibonacci(50));
        System.out.println(fibonacci2(50));
    }

    private static long fibonacci(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            int a = 1,b = 1;
            for (int i = 2;i < n;i++) {
                int t = a;
                a = b;
                b = t + b;
            }
            return b;
        }
    }

    private static long fibonacci2(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        MyObject result = new MyObject(0);
        getSum(1, 1, 2, n, result);
        return result.getValue();
    }

    private static void getSum(int a, int b, int num, int n, MyObject result) {
        if (num == n) {
            result.setValue(b);
            return;
        }
        int t = a;
        a = b;
        b = t + b;
        getSum(a, b, ++num, n, result);
    }

    static class MyObject {
        private int value;

        MyObject(int value) {
            this.value = value;
        }

        void setValue(int value) {
            this.value = value;
        }

        int getValue() {
            return this.value;
        }
    }
}
