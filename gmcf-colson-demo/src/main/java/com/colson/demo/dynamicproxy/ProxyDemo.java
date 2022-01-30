package com.colson.demo.dynamicproxy;

import com.colson.demo.staticproxy.ProxyClass;

import java.lang.reflect.Proxy;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author song
 * @description: 测试动态代理
 * @date 2022/1/29 下午8:02
 */
public class ProxyDemo {

    public static void main(String[] args) {
//        String saveGeneratedFiles = "sun.misc.ProxyGenerator.saveGeneratedFiles";
//        //设置系统变量
//        System.setProperty(saveGeneratedFiles, "true");
//
//        //拿系统变量
//        String property = System.getProperty(saveGeneratedFiles);
//        System.out.println(property);

        //把目标类传入代理处理器
        ProxyInvocationHandler handler = new ProxyInvocationHandler(new TargetClassImpl());

        //代理类
        TargetClass targetClass = (TargetClass) Proxy.newProxyInstance(
                new TargetClassImpl().getClass().getClassLoader(),
                new Class<?>[] {TargetClass.class},
                handler);
        targetClass.sayHi();

    }
}
