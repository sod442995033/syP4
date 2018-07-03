package cn.dzygod.thread;


/**
 * @Author: dingziyuan
 * @Date: 2018/7/2 15:52
 * @Description: 第一个练习
 */
public class Demo1_Thread {


    @SuppressWarnings("AlibabaRemoveCommentedCode")
    public static void main(String[] args) {
//        创建线程的两种方式
//          testOne();
//          testTwo();
//          anonymous();

//        为指定线程命名,获取线程名
//        setNameForThread();
//        getStaticName();

//        sleepTest();
//        daemonTest();

    }

    /**
     * 守护线程
     * 用户线程全部死亡,守护线程就会死亡
     *
     */
    private static void daemonTest() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    System.out.println(getName() + "...我是一般线程");
                }
                super.run();
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println(getName() + "...我是守护线程");
                }
                super.run();
            }
        };


        //设置为守护线程
        thread2.setDaemon(true);

        thread.start();
        thread2.start();
    }

    /**
     * sleep()定时睡眠
     */
    private static void sleepTest() {
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("定时起床");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("定时睡觉");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private static void getStaticName() {
        new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ".....11111");
                super.run();
            }
        }.start();


        //获取当前线程引用    Thread.currentThread()  runnable的实现
        new Thread(() -> System.out.println(Thread.currentThread().getName() + "...22222")).start();

        Thread.currentThread().setName("我是主线程");
        System.out.println(Thread.currentThread().getName());
    }


    /**
     * 为指定线程命名
     * 两种方式,通过set或构造方法为线程命名
     * 还可以通过生成实例,执行start()方法
     */
    private static void setNameForThread() {
        new Thread("名为张三的线程") {
            @Override
            public void run() {

                //set()方法进行命名
                //this.setName("名为张三的线程");

                //不指定name就会自动生成一个名称
                System.out.println(this.getName() + ".....1111111111111");
                super.run();
            }
        }.start();

        //父类引用指向子类对象
        Thread thread = new Thread() {
            @Override
            public void run() {
//                this.setName("名为李四的线程");
                System.out.println(this.getName() + ".....22222222222222");
                super.run();
            }
        };

        thread.setName("名为李四的线程");
        thread.start();
    }


    /**
     * 使用匿名内部类
     * 开启线程的两种方式
     */
    private static void anonymous() {
        //继承Thread类
        new Thread() {
            @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("Threaddddddddddddddddddddddddddddddddddddddddd");
                }
                super.run();
            }
        }.start();


        //实现runnable接口
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("runnableeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                }
            }
        }).start();
    }

    /**
     * 线程的第二种方式
     */
    private static void testTwo() {
        MyRunnable runnable = new MyRunnable();

        Thread thread = new Thread(runnable);
        //多态,走的是子类的start方法
        thread.start();

        for (int i = 0; i < 100000; i++) {

            System.out.println("thead");
        }
    }

    /**
     * 线程的第一种方式
     *
     * @param args
     */
    private static void testOne() {
        new Test().start();
        for (int i = 0; i < 10000; i++) {
            System.out.println("333333333");
        }
    }
}


class MyRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Runnable");
        }
    }
}

class Test extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println("1000000");
        }
        super.run();
    }
}