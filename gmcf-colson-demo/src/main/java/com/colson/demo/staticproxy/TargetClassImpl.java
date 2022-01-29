package com.colson.demo.staticproxy;

/**
 * @author song
 * @description: 目标类的实现
 * @date 2022/1/29 下午6:17
 */
public class TargetClassImpl implements TargetClass {

    @Override
    public void sayHi() {
        System.out.println("TargetClassImpl sayHi.......");
    }
}
