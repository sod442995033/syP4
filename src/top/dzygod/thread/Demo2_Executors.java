package top.dzygod.thread;

import java.util.concurrent.*;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/12 11:08
 * @Description: 线程池的使用
 */
public class Demo2_Executors {

    public static void main(String[] args) {
//        test();



    }


    /**
     * A.线程池概述
     * 程序启动一个新线程的成本是比较高的,因为它涉及到要与操作系统进行交互,
     * 而使用线程池可以很好的提高性能,尤其是程序中需要创建大量生命周期短的线程时,更应该考虑使用线程池.
     * 线程池里的每一个线程代码结束后,并不会死亡,而是再次回到线程池中成为空闲状态,等待下一个对象来使用,
     * 在JDK5之前,我们必须手动实现我们的线程池,从JDK5开始,JDK内置支持线程池
     * <p>
     * B.内置线程池的使用概述
     * JDK5新增了一个Executors工厂类来生产线程池,有如下几个方法
     * public static ExecutorService newFixedThreadPool(int nThreads)
     * public static ExecutorService newSingleThreadExecutor()
     * 这些方法的返回值是ExecutorService对象,该对象的表示一个线程池,
     * 可以执行Runnable对象或者Callback对象代表的线程,它提供了如下方法
     * Future<?> submit(Runnable task)
     * <T> Future<T> submit(Callable<T> task)
     * <p>
     * 使用步骤
     * 创建线程池对象
     * 创建Runnable实例
     * 提交Runnable实例
     * 关闭线程池
     */
    private static void test() {

        //newFixedThreadPool()这个方法是创建指定大小的线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);

        //newSingleThreadExecutor()这个方法是创建存储单个线程的线程池
        //ExecutorService service = Executors.newSingleThreadExecutor();

        //将线程放进池子里并执行
        pool.submit(new MyRunnable());
        pool.submit(new MyRunnable());


        //关闭线程池
//        pool.shutdown();


    }


}
