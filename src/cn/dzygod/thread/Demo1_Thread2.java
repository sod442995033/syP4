package cn.dzygod.thread;


import cn.dzygod.file.FilePra;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/2 15:52
 * @Description: 第一个练习
 */
public class Demo1_Thread2 {

    private static int INT = 1000;

    public static void main(String[] args) {
        //        joinTest();
        //        yieldTest();
        //        priorityTest();
        //        synchronizedTest1();
        FilePra pra = new FilePra();

    }

    /**
     * 非静态同步方法锁
     * 加密参数,传入的是当前对象
     */
    public synchronized void print1() {
        new Thread(() -> {
            for (int i = 0; i < INT; i++) {
                Thread thread = Thread.currentThread();
                System.out.print("1");
                System.out.print("2");
                System.out.print("3");
                System.out.print("4");
                System.out.print("\r\n");
            }
        }).start();


    }

    public void print2() {
        new Thread(() -> {
            synchronized (this) {
                for (int i = 0; i < INT; i++) {
                    Thread thread = Thread.currentThread();
                    System.out.print("a");
                    System.out.print("b");
                    System.out.print("c");
                    System.out.print("d");
                    System.out.print("\r\n");
                }
            }
        }).start();

    }

    /**
     * 同步锁
     * 同步代码块,参数必须是同一个对象,如果传入不同对象,不是同一把锁,同步锁也就失效了
     * 当前线程执行完才能执行其他用户线程
     */
    private static void synchronizedTest1() {
        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                String name = Thread.currentThread().getName();
                for (int i = 0; i < INT; i++) {
                    System.out.print("1");
                    System.out.print("2");
                    System.out.print("3");
                    System.out.print("4");
                    System.out.print("\r\n");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < INT; i++) {
                    System.out.print("a");
                    System.out.print("b");
                    System.out.print("c");
                    System.out.print("d");
                    System.out.print("\r\n");
                }
            }
        }).start();
    }

    /**
     * 设置优先级
     */
    private static void priorityTest() {
        new Thread(() -> {
            for (int i = 0; i < INT; i++) {
                Thread thread = Thread.currentThread();
                //设置线程优先级
                thread.setPriority(Thread.MIN_PRIORITY);
                System.out.println(thread.getName() + "......aaaaaaaaaaa");
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < INT; i++) {
                Thread thread = Thread.currentThread();
                //设置线程优先级
                thread.setPriority(Thread.MAX_PRIORITY);
                System.out.println(thread.getName() + "......bbbbbbbbbb");
            }
        }).start();
    }

    /**
     * 礼让线程
     * 当前线程主动让出cpu
     */
    private static void yieldTest() {
        int anInt = 100;

        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < anInt; i++) {
                    System.out.println(getName() + "....11111111111111111");
                }
            }
        };


        Thread thread1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < anInt; i++) {
                    System.out.println(getName() + "........22222222");
                    if (i % 10 == 0) {
                        Thread.yield();
                    }
                    System.out.println("没有礼让");
                }
            }
        };


        thread.start();
        thread1.start();
    }


    /**
     * join()方法的练习
     */
    private static void joinTest() {
        Thread t1 = new Thread() {
            @Override
            public void run() {

                for (int i = 0; i < 100; i++) {
                    System.out.println(getName() + "......11111111");
                }
                super.run();
            }
        };

        Thread t2 = new Thread() {

            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    if (i == 10) {
                        try {
                            //join插入的线程走完才能继续运行当前线程,参数是指定加入时间一毫秒
                            t1.join(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(getName() + "............222222222222");
                }
                super.run();
            }
        };

        t1.start();
        t2.start();
    }

}