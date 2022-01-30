package com.colson.demo.staticproxy;

/**
 * @author song
 * @description: 测试静态代理
 * @date 2022/1/29 下午8:02
 */
public class ProxyDemo {

    public static void main(String[] args) {
        ProxyClass proxyClass = new ProxyClass(new TargetClassImpl());
        proxyClass.sayHi();
    }
}
