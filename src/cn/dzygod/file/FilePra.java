package cn.dzygod.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * @Author: dingziyuan
 * @Date: 2018/5/15 10:47
 * @Description: *
 */
public class FilePra {
    public static void main(String[] args) throws IOException {
//        praOne();
//        filter();


    }




    /**
     *  public String[] list(FilenameFilter filter)
     *  File对象过滤器
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
     *
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
            if(file1.isFile()&&file1.getName().endsWith(".jpg")){
                System.out.println(file1);
            }
        }
    }


}
