package com.colson.demo.jvm;


import com.colson.common.bean.Student;

/**
 * @author:songbowen
 * @date:2020/10/8
 *
 * 主题:JVM
 * 1. JVM系统架构图
 *
 * 2. 类加载器
 * System.out.println(Student.class.getClassLoader().getParent().getParent());
 * System.out.println(Student.class.getClassLoader().getParent());
 * System.out.println(Student.class.getClassLoader());
 *  2.1 有哪几种类加载器
 *  2.2 双亲委派
 *  2.3 沙箱安全机制
 *
 * 3. Native
 *  3.1 native是一个关键字
 *  3.2 声明有,实现无,why?
 *
 * 4. PC寄存器
 *  4.1 记录了方法之间的调用和执行情况,类似排班值日表
 *  用来存储指向下一条指令的地址,也即将要执行的指令代码,
 *  它是当前线程所执行的字节码的行号指示器
 *
 * 5. 方法区
 *  5.1 它存储了每一个类的结构信息
 *  5.2 方法区是规范,在不同的虚拟机里实现是不一样的,最典型的就是永久代(PermGen Space)和元空间(Metaspace).
 *
 *  空调 k1 = new 格力();
 *  List list = new ArrayList();
 *  方法区 f = new 永久代();
 *  方法区 f = new 元空间();
 *
 * 6. 栈 stack
 *  6.1 栈管运行,堆管存储
 *  6.2 栈保存哪些东西?
 *      八种基本类型的变量+对象的引用变量+实例方法都是在函数的栈内存中分配.
 *  6.3 Exception in thread "main" java.lang.StackOverflowError
 *      是一个error错误
 */
public class JVMNote {

    public static void m1() {
        m1();
    }

    public void say() {
        System.out.println("------say---------");
    }

    public static void main(String[] args) {

        System.out.println(Student.class.getClassLoader().getParent().getParent());
        System.out.println(Student.class.getClassLoader().getParent());
        System.out.println(Student.class.getClassLoader());

        System.out.println("11111111111111");
        m1();
        System.out.println("22222222222222");
    }
}
