package top.dzygod.jdk8.行为参数化;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/16 12:26
 * @Description: *
 */
public interface ApplePredicate {

    boolean test(Apple apple);

    default void test1() {
        System.out.println("你好");
    }


}
