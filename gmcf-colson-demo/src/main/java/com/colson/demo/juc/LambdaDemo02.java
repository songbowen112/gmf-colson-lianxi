package com.colson.demo.juc;

@FunctionalInterface    //标记函数式接口
interface Foo {

    //普通方法
    //        public void sayHello();
    public int add(int i, int j);

    //default修饰方法
    default int mul(int i, int j) {
        System.out.println("come in mul method");
        return i * j;
    }

    //静态方法
    public static int div(int i, int j) {
        System.out.println("come in div method");
        return i / j;
    }
}

/**
 * 1.函数式编程(lambda表达式)
 * <p>
 * 1) 拷贝小括号   写死右箭头   落地大括号
 * 2) FunctionalInterface:标记为此接口为函数式接口,只有一个普通方法
 * 3) default:接口中声明有完整的定义加实现的方法,可以声明多个
 * 4) static:静态方法,可以声明多个
 */
public class LambdaDemo02 {

    public static void main(String[] args) {

        Foo foo = (i, j) -> {
            System.out.println("come in add method");
            return i + j;
        };
        System.out.println(foo.add(3, 5));

        System.out.println(foo.mul(3, 5));

        System.out.println(Foo.div(10, 2));

        //lambda表达式
//        Foo foo = () -> {System.out.println("hello......lambda表达式");};
//        foo.sayHello();

        //匿名内部类
//        Foo foo = new Foo() {
//            @Override
//            public void sayHello() {
//                System.out.println("hello......匿名内部类");
//            }
//        };
//        foo.sayHello();

    }
}
