package top.dzygod.jdk8.practice.chapterthirteen;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: dingziyuan
 * @Date: 2018/10/6 14:50
 * @Description:
 * 第十三章 函数式的思考
 *  本章内容
 *       为什么要进行函数式编程
 *       什么是函数式编程
 *       声明式编程以及引用透明性
 *       编写函数式Java的准则
 *       迭代和递归
 */
public class Index{


    public static void main(String[] args){
        /**
         * 如果一个方法既不修改它内嵌类的状态，也不修改其他对象的状态，使用 return 返回所有的计算结果，
         * 那么我们称其为纯粹的或者无副作用的。
         *
         * 声明式编程
         * （“你只需要使用不相互影响的表达式，描述想要做什么，由系统来选择如何实现”）和无副作用计算。
         * 这两个思想能帮助你更容易地构建和维护系统。
         *
         * 在函数式编程的上下文中，一个“函数”对应于一个数学函数：它接受零个或多个参数，
         * 生成一个或多个结果，并且不会有任何副作用。你可以把它看成一个黑盒，
         * 它接收输入并产生一些输出,它所返回的结果一定是相同的
         * 要被称为函数式，函数或者方法不应该抛出任何异常
         *
         * （不改变对调用者可见的变量、不进行I/O、不抛出异常）的这些限制都隐含着引用透明性。
         */
        List<Integer> x = Arrays.asList(1, 2, 3).subList(0, 2);
        x.forEach(System.out::println);

    }
}
