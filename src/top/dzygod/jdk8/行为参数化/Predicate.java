package top.dzygod.jdk8.行为参数化;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/16 13:36
 * @Description: 过滤的普适接口
 */
@FunctionalInterface
public  interface  Predicate<T> {

    boolean test (T t);

}
