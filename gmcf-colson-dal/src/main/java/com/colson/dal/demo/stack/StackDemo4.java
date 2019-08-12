package com.colson.dal.demo.stack;

import com.colson.dal.util.stack.LinkedStack;

/**
 * 使用递归求解n的阶乘(递归分解过程离不开栈)
 * n! = 1*2*3*4*...*n;
 */
public class StackDemo4 {

    public static void main(String[] args) {
        System.out.println(getFactorial(3));
        System.out.println(getFactorial(5));

        System.out.println(getFactorial2(3));
        System.out.println(getFactorial2(5));

    }

    public static int getFactorial(int n) {
        if (n == 0) {
            return 1;
        }
        return getFactorial(n-1)*n;
    }

    public static int getFactorial2(int n) {
        LinkedStack<Integer> stack = new LinkedStack();
        while (n != 0) {
            stack.push(n);
            n = n-1;
        }
        Integer result = 1;
        for (int i=0;i<stack.size();i++) {
            Integer pop = stack.pop();
            result += result*pop;
        }
        return result;
    }
}
