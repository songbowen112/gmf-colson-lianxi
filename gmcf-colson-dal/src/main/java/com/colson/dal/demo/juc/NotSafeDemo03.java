package com.colson.dal.demo.juc;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 1.故障现象
 * java.util.ConcurrentModificationException
 * <p>
 * 2.导致原因
 * 多线程并发争抢同一个资源类,没有加锁
 * <p>
 * 3.解决方法
 * 1)new Vector():线程安全的list
 * 2)Collections.synchronizedList(List<T> list) JDK1.2
 * 3)new CopyOnWriteArrayList():JDK1.8,JUC里面的List实现类,底层使用lock
 * <p>
 * 4.优化建议(同样的错误不犯第二次)
 * <p>
 * 写时复制
 * CopyOnWrite容器即写时复制的容器.往一个容器添加元素的时候,不直接往当前容器的Object[]添加,
 * 而是先将当前容器的Object[]进行Copy,复制出一个新的容器Object[] newElements,然后新的容器Object[] newElements
 * 里添加元素,添加完元素之后,再将原容器的引用指向新的容器setArray(newElements).这样做的好处是可以对CopyOnWrite容器
 * 进行并发的读,而不需要加锁,因为当前容器不会添加任何元素.所以CopyOnWrite容器也是一种读写分离的思想,读和写不同的容器.
 */
public class NotSafeDemo03 {

    public static void main(String[] args) {

//        Map map = new HashMap();
        Map map = new ConcurrentHashMap();

        for (int i = 0; i < 30; i++) {

            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    public static void setNotSafe() {
        //        Set<String> set = new HashSet<>();
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {

            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    public static void listNotSafe() {
//        List<String> list = new ArrayList();
//        List<String> list = new Vector<>();// 1
//        List<String> list = Collections.synchronizedList(new ArrayList<>());// 2
        List<String> list = new CopyOnWriteArrayList<>();// 3

        for (int i = 0; i < 30; i++) {

            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
