package top.dzygod.jdk8.practice.chapternine;

/**
 * @Author: dingziyuan
 * @Date: 2018/10/3 9:48
 * @Description: 冲突及如何显式的消除歧义
 * 实现两个有相同函数签名的方法,编译不能通过,必须在当前类中实现该方法,
 * 覆盖父类的方法,在它的方法体内显式地调用你希望调用的方法.
 * Java 8中引入了一种新的语法 X.super.m(…) ，其中 X 是你希望调用的 m方法所在的父接口。
 */
public class ExtendsProblem2 implements GoodFather, Daddy {

    public static void main(String[] args) {
//        new ExtendsProblem2().say();

    }

    /**
     * Java 8中引入了一种新的语法 X.super.m(…) ，其中 X 是你希望调用的 m方法所在的父接口。
     */
    @Override
    public void say() {
        Daddy.super.say();
    }
}

interface GoodFather {
    default void say() {
        System.out.println("我是Father");
    }
}

interface Daddy {
    default void say() {
        System.out.println("我是Daddy");
    }
}
