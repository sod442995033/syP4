package cn.dzygod.io;

import java.io.*;
import java.util.Scanner;

/**
 * @Author: dingziyuan
 * @Date: 2018/6/8 19:56
 * @Description: 标准输入输出流
 */
public class SystemInOut {


    public static void main(String[] args) throws IOException {

//        standardTest1();
//        standardTest2();
//        keyboard();


    }


    /**
     *  两种方式实现键盘录入
     */
    private static void keyboard() {

        //1.使用bufferReader装饰类
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String s = reader.readLine();
//        System.out.println(s);

        //2.使用Scanner装饰类
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        System.out.println(s1);
    }


    /**
     * 改变输入输出流指向
     * @throws IOException
     */
    private static void standardTest2() throws IOException {
        //改变标准输入流
        System.setIn(new FileInputStream("O.txt"));
        //改变标准输出流
        System.setOut(new PrintStream(new FileOutputStream("D.txt")));

        //获取默认的标准输入流,默认指向键盘,改变后指向文件
        InputStream in = System.in;
        //获取默认的标准输出流,默认指向console,改变后指向文件
        PrintStream out = System.out;

        int len;
        while ((len = in.read()) != -1) {
            out.write(len);
        }

        in.close();
        out.close();
    }

    /**
     * 什么是标准输入流和输出流?
     * System.in是InputStream 标准输入流,默认可以从键盘输入读取字节数据
     * System.out是PrintStream,标注输出流,默认可以向console里输出字符和字节数据
     */
    private static void standardTest1() throws IOException {
        InputStream in = System.in;
        int read = in.read();
        //读取的是第一个字节在码表中的数值,输入4999,输出的是52也就是4在码表中的位置
        System.out.println(read);

        //标准输入流的in对象是静态的,关闭一次就彻底关闭(不与硬盘进行交互,所以最好不要用close()关闭)
        in.close();
    }


    /**
     * 打印的字符流与字节流
     * 只操作数据目的
     *
     * @throws FileNotFoundException
     */
    private static void print() throws FileNotFoundException {
        /**
         *  1.什么是打印流
         *      该流可以很方便的将对象的toString()结果输出,并且加上自动换行,而且可以使用自动刷出的模式
         *      System.out就是一个PrintStream(字节流),其默认向控制台输出信息
         *
         *
         */
/*
        PrintStream out = System.out;

        //底层通过Integer.toString()将97转换为字符串并打印
        out.println(97);
        //查找码表,找到对应的a并打印
        out.write(97);

        //默认调用对象的toString方法
        out.println(new StudentPra("张三"));

        //打印引用数据类型,如果是null就返回null,如果不是null就调用对象的toString()方法
        StudentPra s = null;


        out.println(s);
        out.close();*/


        /**
         * 2.打印方式:
         *  打印:print(),println()
         *  自动刷出(字符流):PrintWriter(OutputStream out,boolean autoFlush)
         *
         */

//        PrintWriter writer = new PrintWriter("g.txt");
        PrintWriter writer = new PrintWriter(new FileOutputStream("g.txt"), true);

        //自动刷出只针对println()方法
        writer.println(97);
        writer.print(97);
        writer.write(97);


//        writer.close();
    }

}
