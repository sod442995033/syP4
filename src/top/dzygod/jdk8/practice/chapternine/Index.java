package top.dzygod.jdk8.practice.chapternine;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/29 12:37
 * @Description:
 *  第九章 默认方法
 *       什么是默认方法
 *       如何以一种兼容的方式改进API
 *       默认方法的使用模式
 *       解析规则
 *  java8解决接口新增声明问题:
 *  一，Java 8允许在接口内声明静态方法。
 *  二，Java 8引入了一个新功能，叫默认方法，通过默认方法你可以指定接口方法的默认实现。
 *  
 *
 */
public class Index implements Resizable{

    public static void main(String[] args){
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();

    }

    /**
     * 菱形继承问题
     * 因为类的继承关系图形状像菱形
     */
    private static void test5() {
        new DiamondExtendsProblem().test();
    }

    /**
     * 冲突及如何显式的消除歧义
     */
    private static void test4() {
//        new ExtendsProblem2().say();
    }


    /**
     * 选择提供了最具体实现的默认方法的接口
     */
    private static void test3() {
        new ExtendsProblem().doSomething();
    }

    /**
     * 默认方法调用
     */
    private static void test2() {
        boolean empty = new Index().isEmpty();
        System.out.println(empty);
    }


    /**
     * 排序
     */
    private static void test1() {
        List<Integer> list = Arrays.asList(2, 3, 6, 1, 5);
        //按自然序列对其中的元素进行排序
        list.sort(Comparator.naturalOrder());
        System.out.println(list);
    }

}


