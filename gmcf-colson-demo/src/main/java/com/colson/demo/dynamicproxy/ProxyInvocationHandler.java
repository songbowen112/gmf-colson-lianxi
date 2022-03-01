package com.colson.demo.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author song
 * @description: 动态代理处理器(不是真正的代理类)
 * @date 2022/1/29 下午7:59
 */
public class ProxyInvocationHandler implements InvocationHandler {

    //持有一个目标类的引用
    private Object targetClass;

    /**
     * 构造器
     * @param targetClass
     */
    public ProxyInvocationHandler(Object targetClass) {
        this.targetClass = targetClass;
    }

    /**
     * 对目标方法的增强
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //对目标类进行增强
        System.out.println("执行方法前增强.......");

        //真正干活的还是目标类的sayHi方法
        Object object = method.invoke(targetClass, args);

        //对目标类进行增强
        System.out.println("执行方法后增强.......");
        return object;
    }


}
