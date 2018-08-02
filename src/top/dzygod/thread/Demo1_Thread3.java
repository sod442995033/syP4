package top.dzygod.thread;


import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/2 15:52
 * @Description: 第一个练习
 */
public class Demo1_Thread3 {

    private static int INT = 1000;

    private int num = 100;

    public static void main(String[] args) throws InterruptedException {
//                runtimeTest();
//                timerTest();
//                waitAndNotifyTest();
//                waitAndNotifyTest2();
//                reentrantlockTest();
//                threadGroupTest();
//                threadGroupTest2();








    }


    /**
     * 线程组的练习二
     * 将线程指定线程组
     */
    private static void threadGroupTest2() {
        Thread thread = new Thread(() -> {

        });

        Thread thread1 = new Thread(() -> {

        });

        ThreadGroup myThreadGroup = new ThreadGroup("我是自创的线程组");

        Thread first = new Thread(myThreadGroup, thread, "我是第一个线程");
        Thread second = new Thread(myThreadGroup, thread, "我是第二个线程");

        //打印流输出的是"我是自动创的线程组"
        System.out.println(first.getThreadGroup().getName());
        System.out.println(second.getThreadGroup().getName());

        /**
         * 线程组的好处在于,可以对相同功能的线程们进行统一管理
         * 如设置守护线程,设置优先级
         */
        myThreadGroup.setDaemon(true);
        myThreadGroup.setMaxPriority(Thread.MAX_PRIORITY);
    }

    /**
     * 线程组的练习1
     */
    private static void threadGroupTest() {
        Thread thread = new Thread(() -> {

        });

        Thread thread1 = new Thread(() -> {

        });

        Thread first = new Thread(thread, "第一个线程");
        Thread second = new Thread(thread1, "第二个线程");

        /**
         * 输出都为main,没有指定线程组的线程默认都是在主线程组中的
         */
        System.out.println(first.getThreadGroup().getName());
        System.out.println(second.getThreadGroup().getName());
    }


    /**
     * 互斥锁练习
     */
    private static void reentrantlockTest() {
        WaitAndNotifyForLock lockClass = new WaitAndNotifyForLock();

        ReentrantLock lock = new ReentrantLock();

        new Thread(() -> {
            while (true) {
                try {
                    lockClass.print1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    lockClass.print2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(() -> {
            while (true) {
                try {
                    lockClass.print3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 1.5新特性互斥锁
     * 使用Reentrantlock类的lock和unlock方法进行同步
     * <p>
     * 重写3个线程通信
     * 1.在同步代码块中用哪个对象锁,就用哪个对象调用wait()方法.
     * 2.为什么wait()和notify()这些方法需要定义在Object这个类中
     * 锁对象可以使任意对象,任意对应的类都是Object的子类,所以将方法定义在Object这个类中,
     * 就会让任意对象对其进行调用
     * 3.sleep()方法和wait()方法的区别
     * a.
     * sleep()方法在同步代码块中,或同步函数中不释放锁(CPU并没有执行其他的线程)
     * wait()在同步代码块和同步函数中释放锁(CPU会去执行其他线程)
     * b.
     * sleep()方法必须传入参数,时间到了就会自动醒来
     * wait()方法可以传入参数,如果传入时间参数,用法和sleep()相似,时间到就停止等待
     * 通常用的都是没有参数的wait()方法
     */
    static class WaitAndNotifyForLock {
        private int state = 1;
        private ReentrantLock lock = new ReentrantLock();
        private Condition c1 = lock.newCondition();
        private Condition c2 = lock.newCondition();
        private Condition c3 = lock.newCondition();


        public void print1() throws InterruptedException {

            lock.lock();

            if (state != 1) {
                c1.await();
            }
            System.out.print("1");
            System.out.print("2");
            System.out.print("3");
            System.out.print("4");
            System.out.print("5");
            System.out.print("\r\n");
            this.state = 2;

            c2.signal();

            lock.unlock();
        }

        public void print2() throws InterruptedException {

            lock.lock();
            if (state != 2) {
                c2.await();
            }
            System.out.print("a");
            System.out.print("b");
            System.out.print("c");
            System.out.print("d");
            System.out.print("e");
            System.out.print("\r\n");
            this.state = 3;

            c3.signal();

            lock.unlock();
        }

        public void print3() throws InterruptedException {

            lock.lock();
            if (this.state != 3) {
                c3.await();
            }

            System.out.print("张");
            System.out.print("张");
            System.out.print("张");
            System.out.print("张");
            System.out.print("\r\n");
            this.state = 1;

            c1.signal();

            lock.unlock();
        }
    }


    /**
     * 三个线程间的通信
     * 依旧是等待唤醒机制
     * <p>
     * 在同步代码块中,用哪个对象锁,就用哪个调用wait()方法
     * wait()方法也可以传入参数
     */
    private static void waitAndNotifyTest2() {
        WaitAndNotify3Test test = new WaitAndNotify3Test();

        new Thread(() -> {
            try {
                while (true) {
                    test.print1();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    test.print2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    test.print3();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 三个线程的唤醒机制
     */
    static class WaitAndNotify3Test {
        private int state = 1;

        public void print1() throws InterruptedException {

            synchronized (this) {
                while (state != 1) {
                    this.wait();
                }
                System.out.print("1");
                System.out.print("2");
                System.out.print("3");
                System.out.print("4");
                System.out.print("5");
                System.out.print("\r\n");
                this.state = 2;
                this.notifyAll();
            }
        }

        public void print2() throws InterruptedException {
            synchronized (this) {
                while (state != 2) {
                    this.wait();
                }
                System.out.print("a");
                System.out.print("b");
                System.out.print("c");
                System.out.print("d");
                System.out.print("e");
                System.out.print("\r\n");
                this.state = 3;
                this.notifyAll();
            }
        }

        public void print3() throws InterruptedException {
            synchronized (this) {
                if (this.state != 3) {
                    this.wait();
                }

                System.out.print("张");
                System.out.print("张");
                System.out.print("张");
                System.out.print("张");
                System.out.print("\r\n");
                this.state = 1;
                this.notifyAll();
            }
        }
    }


    /**
     * 两个线程之间的通信
     * 等待唤醒机制
     */
    private static void waitAndNotifyTest() {
        waitAndNotify notify = new waitAndNotify();

        new Thread(() -> {
            try {
                while (true) {
                    notify.print1();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    notify.print2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    static class waitAndNotify {

        int state = 1;

        void print1() throws InterruptedException {
            synchronized (this) {
                if (this.state != 1) {
                    this.wait();
                }

                System.out.print("1");
                System.out.print("2");
                System.out.print("3");
                System.out.print("4");
                System.out.print("\r\n");
                this.state = 2;
                this.notify();
            }

        }

        void print2() throws InterruptedException {
            synchronized (this) {
                if (this.state == 1) {
                    this.wait();
                }
                System.out.print("a");
                System.out.print("b");
                System.out.print("c");
                System.out.print("d");
                System.out.print("\r\n");

                this.state = 1;

                this.notify();
            }
        }
    }


    /**
     * 定时器类
     */
    private static void timerTest() throws InterruptedException {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("该起床了!");
            }
        };

//        MyTask task = new MyTask();
        Timer timer = new Timer();
        Date date = new Date(118, 6, 6, 14, 9, 50);

        //在指定时间执行tast线程,最后一个参数是间隔多少秒再次执行
        timer.schedule(task, date, 2000);

        while (true) {
            Thread.sleep(1000);
            System.out.println(new Date());
        }
    }

    static class MyTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("该起床了!");
        }
    }


    /**
     * Runtime类
     */
    private static void runtimeTest() {
        //runtime类可以使用dos命令直接对操作系统进行控制
        Runtime runtime = Runtime.getRuntime();
//        runtime.exec("shutdown -s -t 600");
        try {
            runtime.exec("shutdown -a");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}