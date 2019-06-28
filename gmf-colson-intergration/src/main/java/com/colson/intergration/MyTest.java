package com.colson.intergration;

import com.colson.dal.bean.Student;
import com.colson.intergration.util.ArrayUtils;

import java.math.BigDecimal;
import java.util.List;

public class MyTest {

    public static void main(String[] args) {
        BigDecimal i = BigDecimal.ZERO;
        add(i);
        System.out.println(i);
        int[] a1 = {1,3,5,10,11};
        int[] a2 = {6,7,8,9,10};
        System.out.println(ArrayUtils.findMiddleNum2(a1, a2));
//        Student student = new Student();
//        List<Student> students = new ArrayList<>();
//        add(student,students);
//        students.stream().forEach(i -> System.out.println(i.getName()+" "+i.getAge()));
//        System.out.println(student);
    }

    public static void add(Student student, List<Student> students){
        for (int i=0;i<10;i++) {

            Student student1 = new Student();
            student1.setName("zs");
            student1.setAge(i);
            students.add(student1);
            student = student1;
        }
    }

    public static void add(BigDecimal num){
        num.add(BigDecimal.TEN);
    }

}
