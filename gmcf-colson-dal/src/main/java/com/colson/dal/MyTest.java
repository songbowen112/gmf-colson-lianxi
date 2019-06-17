package com.colson.dal;

import com.colson.dal.bean.Student;

import java.math.BigDecimal;
import java.util.List;

public class MyTest {

    public static void main(String[] args) {
        BigDecimal i = BigDecimal.ZERO;
        add(i);
        System.out.println(i);
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
