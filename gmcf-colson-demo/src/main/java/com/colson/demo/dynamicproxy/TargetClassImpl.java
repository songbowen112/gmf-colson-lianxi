package com.colson.demo.dynamicproxy;

/**
 * @author song
 * @description: 目标类的实现
 * @date 2022/1/29 下午6:17
 */
public class TargetClassImpl implements TargetClass, TargetClass2 {

    @Override
    public void sayHi() {
        System.out.println("TargetClassImpl sayHi.......");
    }

    @Override
    public void sayHi2() {
        System.out.println("TargetClassImpl2 sayHi2.......");
    }
}
