package top.dzygod.designpatterns.java8.templateMethod;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/8 11:15
 * @Description: lambda的极限操作
 */
@FunctionalInterface
public interface Consumer<T> {
    public void accept(T s);
}
