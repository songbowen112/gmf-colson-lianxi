package com.colson.demo.staticproxy;

/**
 * @author song
 * @description: 静态代理类
 * @date 2022/1/29 下午7:59
 */
public class ProxyClass implements TargetClass {

    //持有一个目标类的引用
    private TargetClass targetClass;

    public ProxyClass(TargetClass targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public void sayHi() {
        //对目标类进行增强
        System.out.println("执行方法前增强.......");

        //真正干活的还是目标类的sayHi方法
        targetClass.sayHi();

        //对目标类进行增强
        System.out.println("执行方法后增强.......");
    }
}
