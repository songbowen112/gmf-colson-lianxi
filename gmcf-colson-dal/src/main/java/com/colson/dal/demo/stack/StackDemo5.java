package com.colson.dal.demo.stack;

import com.colson.dal.bean.StackDataBean;
import com.colson.dal.util.stack.LinkedStack;

import java.util.ArrayList;
import java.util.List;

/**
 * 利用一个栈实现以下递归函数的非递归计算:
 *       {1,                        n=0
 * Pn(x)={2x,                       n=1
 *       {2xPn-1(x)-2(n-1)Pn-2(x),  n>1
 */
public class StackDemo5 {
    public static void main(String[] args) {
        System.out.println(getNum(2,1));
        System.out.println(getNum(2,2));
        System.out.println(getNum(3,1));
        System.out.println(getNum(3,2));
        System.out.println(getNum(4,1));

        System.out.println("------------");
        System.out.println(getResultNum(2,1));
        System.out.println(getResultNum(2,2));
        System.out.println(getResultNum(3,1));
        System.out.println(getResultNum(3,2));
        System.out.println(getResultNum(4,1));


    }

    public static int getResultNum(int n,int x) {
        LinkedStack<StackDataBean> stack = new LinkedStack<>();
        int fv1 = 1;
        int fv2 = 2*x;

        for (int i=n;i>=2;i--) {
            StackDataBean bean = new StackDataBean(i,null);
            System.out.println("进栈时的n:"+i);
            stack.push(bean);
        }
        while (stack.size()>0) {
            StackDataBean pop = stack.pop();
            System.out.println("出栈时的n:"+pop.key);
            pop.value = 2*x*fv2-2*(pop.key-1)*fv1;
            fv1 = fv2;
            fv2 = pop.value;
        }
        if (n==0) {
            return fv1;
        }
        return fv2;
    }

    public static Integer getNum(int n,int x) {
        if (n==0) {
            return 1;
        }
        if (n==1) {
            return 2*x;
        }
        if (n>1) {
            int num1 = getNum(n-1, x);//2
            int num2 = getNum(n-2, x);//2
            return 2*x*num1-2*(n-1)*num2;
        }
        return null;
    }
}
