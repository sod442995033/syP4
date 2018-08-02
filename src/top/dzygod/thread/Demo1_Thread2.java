
package top.dzygod.thread;

import java.io.IOException;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/2 15:52
 * @Description: 第一个练习
 */
public class Demo1_Thread2 {

    private static int INT = 1000;

    private int num = 100;

    public static void main(String[] args) throws IOException {
//        joinTest();
//        yieldTest();
//        priorityTest();
//        synchronizedTest1();
//        synchronizedTest2();
//        synchronizedTest3();
//        ticketSalesTest();
//        ticketSalesTest1();
//        deadLockTest();
//        singletonTest();


    }


    /**
     * 单例设计模式
     */
    private static void singletonTest() {

        Singleton1 s = Singleton1.s;
        Singleton1 s1 = Singleton1.s;

        System.out.println(s == s1);
    }


    /**
     * 简单的单例
     * 加了final实例的内存地址不会变了(所以必须是单实例)
     */
    static class Singleton2 {
        private Singleton2() {

        }

        private static final Singleton2 s = new Singleton2();
    }

    /**
     * 懒汉式(单例的延迟加载模式)
     * 类加载不创建实例,仅仅做一个声明.(所以说比较懒)
     */
    static class Singleton1 {
        private Singleton1() {
        }

        private static Singleton1 s;

        public static Singleton1 getInstance() {

            if (s != null) {
                s = new Singleton1();
            }
            return s;
        }
    }


    /**
     * 饿汉式
     * 类加载直接创建实例,所以称为饿汉式.(所以比较急躁,把内存当成饭就好了)
     */
    static class Singleton {
        private Singleton() {

        }

        private static Singleton s = new Singleton();

        public static Singleton getInstance() {
            return s;
        }
    }


    /**
     * 死锁问题练习
     * cpu在执行到第一个线程的第一个同步锁时,第二个线程抢到了资源,o锁没有释放,o1又加了锁.
     * 就会造成两个锁都无法释放的后果,于是就形成了死锁
     * 多线程同步时,如果同步代码块嵌套,使用相同锁,就有可能出现死锁
     * 尽量不要嵌套使用
     */
    private static void deadLockTest() {
        Object o = new Object();
        Object o1 = new Object();

        new Thread(() -> {
            while (true) {
                synchronized (o) {
                    System.out.println(Thread.currentThread().getName() + "我是o");
                    synchronized (o1) {
                        System.out.println(Thread.currentThread().getName() + "我是o1");
                    }
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + "我是o1");
                    synchronized (o) {
                        System.out.println(Thread.currentThread().getName() + "我是o");
                    }
                }
            }
        }).start();
    }


    /**
     * Runnable的购票问题解决方式
     */
    private static void ticketSalesTest1() {
        ticketSales1 sales1 = new ticketSales1();
        new Thread(sales1).start();
        new Thread(sales1).start();
        new Thread(sales1).start();
        new Thread(sales1).start();

/*
        //多次启动一个线程是非法的
        Thread thread = new Thread();
        thread.start();
        thread.start();
        thread.start();
        thread.start();
*/

    }


    static class ticketSales1 implements Runnable {
        private int num = 100;


        @Override
        public void run() {
            //使用当前对象做锁也是可以的
            while (true) {
                synchronized (this) {

                    if (num <= 0) {
                        break;
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() +
                            "当前还有" + num-- + "个票");
                }
            }
        }
    }


    /**
     * 卖票练习
     * 100张票,4个同时窗口卖
     * 每个窗口分一个线程
     */
    private static void ticketSalesTest() {
        new ticketSales().start();
        new ticketSales().start();
        new ticketSales().start();
        new ticketSales().start();
    }


    static class ticketSales extends Thread {
        private static int num = 100;

        @Override
        public void run() {
            while (true) {
                //当前类字节码做锁,因为单个类字节码在jvm中只有一个
                synchronized (ticketSales.class) {
                    if (num <= 0) {
                        break;
                    }
                    try {
                        //防止cpu运行过快,前几个线程把票卖完
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getName() + "还有" + num-- + "张票");
                }
            }

        }
    }


    /**
     * 同步类锁
     * 静态方法锁,加的锁就是当前类的字节码对象
     */
    private static void synchronizedTest3() {
        new Thread(() -> {
            while (true) {
                Demo1_Thread2.print3();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Demo1_Thread2.print4();
            }
        }).start();
    }


    private static synchronized void print3() {
        System.out.print("1");
        System.out.print("2");
        System.out.print("3");
        System.out.print("4");
        System.out.print("\r\n");

    }

    //通过类字节码加锁
    private static void print4() {
        synchronized (Demo1_Thread2.class) {
            System.out.print("a");
            System.out.print("b");
            System.out.print("c");
            System.out.print("d");
            System.out.print("\r\n");
        }
    }

    /**
     * 同步对象锁
     * 非静态方法的同步锁,加的锁就是当前对象
     */
    private static void synchronizedTest2() {
        Demo1_Thread2 thread2 = new Demo1_Thread2();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    thread2.print1();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    thread2.print2();
                }
            }
        }.start();
    }


    /**
     * 非静态同步方法锁
     * 加密参数,传入的是当前对象,以当前对象为锁
     */
    public synchronized void print1() {
        System.out.print("1");
        System.out.print("2");
        System.out.print("3");
        System.out.print("4");
        System.out.print("\r\n");
    }

    public void print2() {
        synchronized (this) {
            System.out.print("a");
            System.out.print("b");
            System.out.print("c");
            System.out.print("d");
            System.out.print("\r\n");
        }
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

