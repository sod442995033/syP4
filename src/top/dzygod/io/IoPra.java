package top.dzygod.io;

import java.io.*;
import java.util.Scanner;

/**
 * @Author: dingziyuan
 * @Date: 2018/5/30 16:29
 * @Description: io的练习
 */
public class IoPra {

    public static void main(String[] args) {
//        encryption();
//        copyPath();
        /**
         *  将键盘录入的字符
         *  输入到指定的txt文件内
         *
         */
        File file = new File("text.txt");
        Scanner scanner = new Scanner(System.in);

        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file);
        ) {
            System.out.println("请输入字符:");

            while (true) {
                String s = scanner.nextLine();
                if ("quit".equals(s)) {
                    break;
                } else {
                    fileOutputStream.write(s.getBytes());
                    fileOutputStream.write("\r\n".getBytes());
                }
            }
            System.out.println("已退出!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将路径外的文件拷贝到项目中
     */
    private static void copyPath() throws IOException {


        File file = getFile();
        try (
                FileInputStream fileInputStream = new FileInputStream(file);
                FileOutputStream fileOutputStream = new FileOutputStream(file.getName());
        ) {
            int len;
            while ((len = fileInputStream.read()) != -1) {
                fileOutputStream.write(len);
            }
            System.out.println("复制完成!");
        }
    }

    /**
     * file文件判断
     */
    private static File getFile() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入路径地址：");
        String filePath = scanner.nextLine();

        File file = new File(filePath);

        while (true) {
            if (!file.exists()) {
                System.out.printf("文件不存在，重新输入!");
                throw new RuntimeException();
            } else if (file.isDirectory()) {
                System.out.println("目录是一个文件夹");
                throw new RuntimeException();
            }
            return file;
        }
    }

    /**
     * 加密图片
     * 对写入的字节进行异或,一个字节异或2次就是它本身
     */
    private static void encryption() {
        try (
                FileInputStream inputStream = new FileInputStream("图片加密.png");
                FileOutputStream outputStream = new FileOutputStream("图片解密.png");
        ) {
            int len;
            while ((len = inputStream.read()) != -1) {
                outputStream.write(len ^ 123);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
