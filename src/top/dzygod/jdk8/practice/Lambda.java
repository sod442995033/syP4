package top.dzygod.jdk8.practice;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/16 18:07
 * @Description: *
 */
public class Lambda {
    public static void main(String[] args){
        Runnable runnable = () -> {
            System.out.println("我是先实例化之后再执行的!");
            return;
        };

        process(runnable);
        process(() -> System.out.println("我是lambda传递的!"));

    }

    private static void process(Runnable r) {
        r.run();
    }

}
