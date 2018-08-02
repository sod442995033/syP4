package top.dzygod.file;


import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author: dingziyuan
 * @Date: 2018/5/15 10:47
 * @Description: *
 */
public class FilePra {


    private static final int INT = 1000;

    public static void main(String[] args) throws IOException, InterruptedException {
//        praOne();
//        filter();
//        suffixJava();
//        test1();
//        delete();
//        copyDir();
//        levelPrinting();
//        fibonacci(8);
//        allZero();
//        lastZero();
//        getLuckNum(8, 3);
    }




    /**
     * @param allNum 环内一共有多少的数,
     * @param var    进行淘汰的循环次数
     * @return
     * @title 约瑟夫环
     * @description 两个局部变量, 一个代表当前操作数, 一个代表下标
     */
    private static Integer getLuckNum(Integer allNum, Integer var) {


        ArrayList<Integer> Ins = new ArrayList<>();

        for (Integer i = 1; i <= allNum; i++) {
            Ins.add(i);
        }

        //用来记录当前数
        int count = 1;
        for (int i = 0; Ins.size() != 1; i++) {
            if (i == Ins.size()) {
                i = 0;
            }

            if (count % var == 0) {
                Ins.remove(i);
                i--;
            }
            count++;
        }


        return Ins.get(0);

    }

    /**
     * 1000的阶乘,尾部零的个数
     */
    private static void lastZero() {
        BigInteger integer = new BigInteger("1");
        for (int i = 1; i <= 1000; i++) {
            integer = integer.multiply(new BigInteger("" + i));
        }

        StringBuilder reverse = new StringBuilder(integer.toString()).reverse();


        int count = 0;
        for (int i = 0; i < reverse.length(); i++) {
            if ('0' == reverse.charAt(i)) {
                count++;
            } else {
                break;
            }
        }
        System.out.println(count);
    }

    /**
     * 1000的阶乘,所有零和尾部零的个数
     * 求出1000的阶乘所有零的个数,不用递归做
     */
    private static void allZero() {
        BigInteger integer = new BigInteger("1");

        for (int i = 1; i <= 1000; i++) {
            integer = integer.multiply(new BigInteger("" + i));
        }

        String s = integer.toString();

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if ('0' == s.charAt(i)) {
                count++;
            }
        }
        System.out.println(count);
    }

    /**
     * 斐波那契数列
     * 即从第三个数起,每一个为前两个的和
     */
    private static Integer fibonacci(Integer num) {
        int two = 2;
        int one = 1;

        if (num == one || num == two) {
            return 1;
        } else {
            return fibonacci(num - 1) + fibonacci(num - 2);
        }
    }


    /**
     * 从键盘中接收一个文件夹路径,把文件夹中的所有文件以及文件夹的名字按层级打印,
     * 例如:把文件夹中的所有文件及文件夹的名字按层级打印
     */
    private static void levelPrinting() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("输入文件夹路径:");
        String dir = scanner.nextLine();

        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            System.out.println("不存在");
        } else if (dirFile.isFile()) {
            System.out.println("是文件");
        } else if (dirFile.isDirectory()) {
            printFileName(dirFile, 0);
            System.out.println("================结束================");
        }
    }


    private static void printFileName(File dir, Integer status) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                String name = file.getName();
                for (int i = 0; i < status; i++) {
                    System.out.print("\t");
                }
                System.out.println(dir.getName().concat("  ").concat(name));
            } else {
                printFileName(file, status + 1);
            }

        }

    }

    /**
     * 键盘录入将指定文件夹
     * 拷贝到指定目录中
     * <p>
     * D:\新建文件夹
     * D:\新建文件夹 (2)-test
     */
    private static void copyDir() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("需要复制的文件夹路径:");
        String s = scanner.nextLine();
        System.out.println("复制到哪里:");
        String s1 = scanner.nextLine();

        File dirIn = new File(s);
        File dirOut = new File(s1);

        if (!dirIn.exists() && dirOut.exists()) {
            System.out.println("不存在");
        } else if (dirIn.isFile() && dirOut.isFile()) {
            System.out.println("是文件");
        } else if (dirIn.isDirectory() && dirOut.isDirectory()) {
            //加判断,防止文件夹为空出现无限递归创建
            if (!dirIn.equals(dirOut)) {
                copyDir(dirIn, dirOut);
            }
            System.out.println("拷贝完毕");
        }
    }


    private static void copyDir(File enter, File export) throws IOException {
        File file = new File(export, enter.getName());
        file.mkdir();

        File[] subFiles = enter.listFiles();

        for (File subfile : subFiles) {
            if (subfile.isFile()) {
                BufferedInputStream enterStream = new BufferedInputStream(new FileInputStream(subfile));
                BufferedOutputStream exportStream = new BufferedOutputStream(new FileOutputStream(new File(file, subfile.getName())));

                int len;
                while ((len = enterStream.read()) != -1) {
                    exportStream.write(len);
                }
                enterStream.close();
                exportStream.close();
            } else {
                copyDir(subfile, file);
            }
        }


    }


    /**
     * 获取一个文件夹路径,删除文件夹
     * D:\新建文件夹
     */
    private static void delete() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("输入路径:");
        while (true) {
            String dir = scanner.nextLine();
            File file = new File(dir);

            if (!file.exists()) {
                System.out.println("不存在,再次输入:");
            } else if (file.isFile()) {
                System.out.println("文件,再次输入:");
            } else if (file.isDirectory()) {
                deleteAllFiles(file);
                System.out.println("已删除");
                return;
            }
        }
    }

    private static void deleteAllFiles(File file) {

        //虚拟机停止时进行删除
        file.deleteOnExit();
        File[] files = file.listFiles();

        for (File file1 : files) {
            if (file1.isDirectory()) {
                deleteAllFiles(file1);
            } else {
                file1.delete();
            }
        }
    }


    /*
     * 键盘录入一个文件夹路径,读取该文件夹的字节大小
     * 13,557
     */
    private static void test1() {


        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入文件夹地址:");
        while (true) {
            String dir = scanner.nextLine();
            File file = new File(dir);
            if (!file.exists()) {
                System.out.println("不存在,再次输入:");
            } else if (file.isFile()) {
                System.out.println("文件,再次输入:");
            } else if (file.isDirectory()) {
                Long bytes = getBytes(file);
                System.out.println(bytes);
                return;
            }


        }
    }

    private static Long getBytes(File file) {

        File[] files = file.listFiles();
        long len = 0;
        for (File file1 : files) {
            if (file1.isDirectory()) {
                //递归字节大小
                len = getBytes(file1) + len;
            } else {
                len = file1.length() + len;
            }
        }
        return len;
    }


    /**
     * 从键盘录入一个文件夹路径,打印出该文件夹下所有的.java文件名
     * <p>
     * 分析:
     * 1.如果录入的文件夹不存在,给予提示
     * 2.如果录入的是文件路径,给予提示
     * 3.如果是文件夹路径,直接返回
     * <p>
     * 1.获取指定文件夹路径下的所有文件,存储再File[]数组中
     * 2.便利file数组,将.java后缀的打印出来
     * 3.如果数组中有文件夹,就使用递归讲文件夹下的java后缀文件也一起打印出来
     */
    private static void usePrint() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入文件夹地址:");
        String path = scanner.nextLine();

        File file = new File(path);
        isDirectory(file);
    }

    private static void isDirectory(File dir) {
        if (!dir.exists()) {
            System.out.println("文件夹不存在");
        } else if (dir.isFile()) {
            System.out.println("输入的是文件路径");
        } else if (dir.isDirectory()) {
            suffixJava(dir);
        }
    }

    private static void suffixJava(File dirPath) {
        File[] files = dirPath.listFiles();

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".java")) {
                System.out.println(file.getName());
            } else if (file.isDirectory()) {
                isDirectory(file);
            }
        }
    }

    /**
     * public String[] list(FilenameFilter filter)
     * File对象过滤器
     */
    private static void filter() {

        File file = new File("E://");
        String[] list = file.list(new FilenameFilter() {


            @Override
            public boolean accept(File dir, String name) {
//                System.out.println(dir);
//                System.out.println(name);
                File file1 = new File(dir, name);
                return file1.getName().endsWith(".jpg");
            }
        });

        for (String s : list) {
            System.out.println(s);
        }
    }


    /**
     * 需求：判断E盘目录下是否有后缀名为.jpg 的文件，如果有，就输出该文件名称
     */
    private static void praOne() {


        File file = new File("E://");
       /* String[] list = file.list();
        for (String s : list) {
            if(s.endsWith(".jpg")){
                System.out.println(s);
            }
        }*/


        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.isFile() && file1.getName().endsWith(".jpg")) {
                System.out.println(file1);
            }
        }
    }


}

