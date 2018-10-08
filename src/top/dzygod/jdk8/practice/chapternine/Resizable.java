package top.dzygod.jdk8.practice.chapternine;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/29 12:55
 * @Description: java8接口中的改动
 */
public interface Resizable {

    /**
     * 这样的静态方法可以解放工具辅助类
     */
    static void test(){

    }

    /**
     * 默认方法,向下兼容,实现了接口同时完成了继承
     * 源码兼容
     */
    default boolean isEmpty() {
        return true;
    }

}
