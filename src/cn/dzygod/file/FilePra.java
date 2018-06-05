package cn.dzygod.file;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @Author: dingziyuan
 * @Date: 2018/5/15 10:47
 * @Description: *
 */
public class FilePra {
    public static void main(String[] args) throws IOException {
//        praOne();
//        filter();
//        suffixJava();



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
        private static void filter (){

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
        private static void praOne () {


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

