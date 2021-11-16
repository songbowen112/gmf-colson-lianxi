package com.colson.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@Getter
@ToString
@AllArgsConstructor
class User {
    private String name;
    private int age;
}

/**
 * @author song
 * @description: AtomicReference类可以对自定义的类进行原子包装，实现原子引用
 * @date 2021/11/15 下午8:14
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User zs = new User("zs", 22);
        User ls = new User("ls", 28);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(zs);

        System.out.println(atomicReference.compareAndSet(zs, ls)+"\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(zs, ls)+"\t" + atomicReference.get().toString());
    }
}
