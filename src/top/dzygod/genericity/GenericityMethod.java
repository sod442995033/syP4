package top.dzygod.genericity;


import jdk.nashorn.internal.ir.Block;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/19 16:09
 * @Description: 泛型方法, 泛型方法在泛型类与普通类都可以定义
 */
public class GenericityMethod<T> {


    public static void main(String[] args) {

//        GenericityMethod.<Integer>test1(1, 2, 3, 4);
//        Integer integer = GenericityMethod.test1(12, 33);
//        String s = GenericityMethod.test1("1", "2");
//        Number number = GenericityMethod.test1(12.1,0, 32.11,23.11);
//        System.out.println(integer);

//        Integer[] integers = {1,2,3};
//        Integer min = GenericityMethod.min(1,2,3);
//        System.out.println(min);

       /* Dictionary<Integer, Component> dictionary = new Hashtable<>();
        dictionary.put(1, new Label());
        test3(dictionary);*/

//        GenericityMethod<Object> method = new GenericityMethod<>();
//        method.test4();
//        test5();

/*
        Demo<String> demo = test7(String.class);
        demo.setT1("1");
        demo.setT2("2");
        System.out.println(demo);
*/
//        test9();

    }


    /**
     * 永远可以把一个参数化类型,
     * 转化成一个原始类型
     */
    private static void test9() {
        IOException exception = new IOException();
        Demo<IOException> demo = getDemo(exception);
        Demo demo1 = demo;
        demo1.setT1(new Exception());
        System.out.println(demo1.getT1());
    }


    /**
     * 泛型类型有继承关系,
     * 但是使用泛型的类之间没有任何关系
     *
     * @param num
     * @return
     * @example GenericityMethod<Object> method = new GenericityMethod<>();
     * @example Demo<Collection> demo1 = method.getDemo(new ArrayList());
     */
    public static <T> Demo<T> getDemo(T num) {
        return new Demo(num, num);
    }


    /**
     * 泛型类的静态上下文中,类型变量无效
     * private static T t;
     * private static T test8() {
     * }
     */
    private static <T extends Throwable> void test8(T t) throws T {

        try {
            Integer[] integers = new Integer[10];
            Integer integer = integers[11];
        } catch (Throwable e) {
            Throwable throwable = e.initCause(t);
            throw e;
        }


    }


    /**
     * 不能实例化类型变量
     * String.class实际上时Class<String>.是唯一实例
     */
    private static <T> Demo<T> test7(Class<T> t) {

        try {
            return new Demo(t.newInstance(), t.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SafeVarargs
    private static <T> Collection<T> test6(Collection<T> con, T... t) {
        //可以使用@SafeVarargs注解来消除创建泛型数组的有有关限制
        for (T t1 : t) {
            con.add(t1);
        }
        return con;
    }

    /**
     * 不能创建参数化类型的数组(泛型类型的数组)
     * JDK8这个限制已经被取消了,创建参数化类型的数组是可以的
     */

    private static void test5() {

        ArrayList<String>[] strings = new ArrayList[10];
        Object[] arrO = strings;
        /*
            数组会记住它的元素类型,如果试图存储其他类型的元素,
            就会抛出一个java.lang.ArrayStoreException异常
         */
        arrO[0] = "啊啊啊";

        /*
            可以通过数组类型检查,但还是会导致一个类型错误.出于这个原因,不允许创建参数化类型的数组
            JDK8已经修复
         */
        arrO[0] = new ArrayList<File>();

    }

    /**
     * jvm中的对象总有一个特定的非泛型类型.因此,所有的类型查询只产生原始类型
     */
    private void test4() {

        ArrayList<Object> arrayList = new ArrayList<>();
        GenericityMethod<String> string = new GenericityMethod<>();
        GenericityMethod<String> integer = new GenericityMethod<>();

        //Conllection<>不能指定泛型类型
        if (arrayList instanceof Collection) {
            //两个class进行比较,返回的时true.因为两次调用的getClass()返回的都是GenericityMethod.class
            if (string.getClass() == integer.getClass()) {
                System.out.println("正确");
            }
        }
    }

    /**
     * 不能用类型参数代替基本类型,<double>错,<Double>对
     * 因为JVM类型擦除之后,含有Object类型的域,而Object不能存储基本数据类型的值
     *
     * @param s
     */
    private static void test3(Dictionary s) {

    }

    private static <T> T test1(T... t) {
        return t[t.length / 2];
    }


    /**
     * <T extends BoundingType>表示
     * <p>
     * T应该是绑定类型的子类型
     * T和绑定类型可以是类,也可以是接口
     * 一个类型变量或通配符可以有多个限定,例如:<T extends Comparable & Comparator>
     * 限定中,如果用一个类来做限定,它必须是限定列表的第一个
     * 限定类型使用&进行分割,逗号用来分割类型变量
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T, O extends String & Comparator> T min(T... t) {

        return t[1];
    }
}


class Demo<T> {

    private T t1;
    private T t2;

    public Demo(T t1, T t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public T getT1() {
        return t1;
    }

    public void setT1(T t1) {
        this.t1 = t1;
    }

    public T getT2() {
        return t2;
    }

    public void setT2(T t2) {
        this.t2 = t2;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "t1=" + t1 +
                ", t2=" + t2 +
                '}';
    }
}