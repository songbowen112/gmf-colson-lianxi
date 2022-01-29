package com.colson.demo.stack;


import com.colson.util.stack.LinkedStack;

/**
 * 使用递归求解n的阶乘(递归分解过程离不开栈)
 * n! = 1*2*3*4*...*n;
 * getF(3)=getF(2)*3=getF(1)*2*3=getF(0)*1*2*3
 * 递归分解3的阶乘:
 * 1.执行getF(2)*3时将getF(2)入栈,栈中数据:[getF(2)]
 * 2.执行getF(1)*2时将getF(1)入栈,栈中数据:[getF(1),getF(2)]
 * 3.执行getF(0)*1时将getF(0)入栈,栈中数据:[getF(0),getF(1),getF(2)]
 * 4.当getF(0)返回1时,getF(0)出栈并赋值为1
 * 5.计算getF(0)*1得1,getF(1)出栈并赋值为1
 * 6.计算getF(1)*2得2,getF(2)出栈并赋值为2
 * 7.计算getF(2)*3得6,getF(3)得到最终结果为6
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
        int size = stack.size();
        for (int i=0;i<size;i++) {
            Integer pop = stack.pop();
            result *= pop;
        }
        return result;
    }
}
