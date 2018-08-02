package top.dzygod.genericity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/21 12:07
 * @Description: 通配符类型限定
 */
public class WildcardType {


    public static void main(String[] args) {
/*
        boolean b = demoIsNull(new Demo<>(null,null));
        System.out.println(b);
*/
/*
       Demo<String> demo = new Demo<String>("啊啊","啊啊");
        test3(demo);
 */
    }

    /**
     * class类的通配符使用样例
     * @param cla
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private static <T> Demo<T> getDemo(Class<T> cla)
            throws IllegalAccessException, InstantiationException {

        return new Demo<>(cla.newInstance(), cla.newInstance());
    }


    /**
     * 通配符捕获
     * 使用T类型捕获?无类型通配符
     * 通配符捕获必须能够确信,通配符表达的是单个,确定的类型.如:Demo<T>捕获Demo<?>
     * 例如:ArrayList<Demo<T>>中的T永远不能捕获ArrayList<Demo<?>>的通配符
     */
    private static void test3(Demo<? super String> demo) {
        process(demo);
    }

    private static <T> void process(Demo<T> demo) {
        System.out.println(demo.getT1().equals(demo.getT2()));
    }


    /**
     * 无限制通配符 <?>
     * set方法只能set一个null,无法set其他类型
     * get方法的返回值只能是一个Object
     *
     * @param a
     * @return
     */
    private static boolean demoIsNull(Demo<?> a) {
//        a.setT1(null);
//        Object t1 = a.getT1();

        return a.getT1() == null || a.getT2() == null;
    }

    /**
     * 当处理一个GregorianCalendar对象的数组时,会出现一些问题
     * Gregorian Calendar是Calendar的子类,并且Calendar实现了Comparable<Calendar>.
     * 因此Gregorian Calendar实现的是Comparable<Calendar>,而不是Comparable<GregorianCalendar>
     * 可以使用超类进行补助
     *
     * @param <T>
     * @return
     * @signature <T extends Comparable<? super T>> T demo()
     */
    private <T extends Comparable<? super T>> T demo() {

        return null;

    }


    /**
     * 子类限定
     */
    private static void test2() {
        Demo<FileNotFoundException> demo = GenericityMethod.getDemo(new FileNotFoundException());
        Demo<? super FileNotFoundException> demo1 = demo;

    }

    /**
     * 使用通配符类型,转化之后设置字段值,编译出错,编译器无法识别具体类型
     * 父类限定
     */
    private static void test1() {
        Demo<IOException> demo = GenericityMethod.getDemo(new IOException());
        Demo<? extends Exception> demo1 = demo;
//        demo1.setT1( new Exception());
    }
}
