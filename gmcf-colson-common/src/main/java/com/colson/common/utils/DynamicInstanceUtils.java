package com.colson.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.BooleanMemberValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * java动态创建实体类工具类
 */
public class DynamicInstanceUtils {
    public static Object getInstance(String className) throws CannotCompileException, IllegalAccessException, InstantiationException, NotFoundException {
        //创建一个类模板
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass(className);

        CtField name = new CtField(pool.get(String.class.getCanonicalName()), "name", ctClass);
        name.setModifiers(Modifier.PRIVATE);
        ctClass.addField(name);
        CtField age = new CtField(pool.get(String.class.getCanonicalName()), "age", ctClass);
        age.setModifiers(Modifier.PRIVATE);
        ctClass.addField(age);

        ClassFile classFile = ctClass.getClassFile();
        ConstPool constPool = classFile.getConstPool();
        AnnotationsAttribute annotationsAttribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        //创建要添加的注解
        Annotation jsonFileAnnotation = new Annotation(JSONField.class.getCanonicalName(), constPool);
        //设置注解中的属性和值
        jsonFileAnnotation.addMemberValue("serialize", new BooleanMemberValue(false, constPool));
        //把这个注解放到一个AnnotationsAttribute对象里面
        annotationsAttribute.addAnnotation(jsonFileAnnotation);
        //把这个对象怼到要打上这个注解的字段/类上面
        age.getFieldInfo().addAttribute(annotationsAttribute);

        //添加类的字段
//        CtField field = new CtField(CtClass.intType, "id", ctClass);
//        ctClass.addField(field);
        //设置字段的访问修饰符
//        field.setModifiers(Modifier.PUBLIC);
        //设置字段的数据类型
//        field.setType(CtClass.intType);
        //设置字段的名称
//        field.setName("id");

        //添加getter setter方法
        ctClass.addMethod(CtNewMethod.setter("setName", name));
        ctClass.addMethod(CtNewMethod.setter("setAge", age));
        ctClass.addMethod(CtNewMethod.getter("getName", name));
        ctClass.addMethod(CtNewMethod.getter("getAge", age));

        //添加toString方法
        StringBuilder builder = new StringBuilder();
        builder.append("return \"Person{\" +\n" +
                "                \"name='\" + name + '\\'' +\n" +
                "                \", age='\" + age + '\\'' +\n" +
                "                '}';");
        CtMethod toStringMethod = new CtMethod(pool.get("java.lang.String"), "toString", null, ctClass);
        toStringMethod.setBody(builder.toString());
        ctClass.addMethod(toStringMethod);
        //创建类模板的实例
        return ctClass.toClass().newInstance();
    }

    public static void main(String[] args) {
        try {
            Object instance = DynamicInstanceUtils.getInstance("DynamicEntity");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("age", "123");
            jsonObject.put("name", "456");
            String jsonString = jsonObject.toJSONString();
            Object o = JSONObject.parseObject(jsonString, instance.getClass());
            System.out.println(o);

            for (Field declaredField : instance.getClass().getDeclaredFields()) {
                System.out.println(declaredField.getName());
                System.out.println(declaredField.getType());
            }

        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
