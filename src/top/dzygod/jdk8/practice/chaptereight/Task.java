package top.dzygod.jdk8.practice.chaptereight;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/7 14:37
 * @Description: *
 */
@FunctionalInterface
public interface Task {
    public void execute();
    public static void doSomething(Runnable r){ r.run(); }
    public static void doSomething(Task a){ a.execute(); }
}


