package cn.dzygod.io;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.util.HashMap;
import java.util.Set;

/**
 * @author dzyGod
 * @description 字符流的操作练习
 */
public class IoPra2 {
    public static void main(String[] args) throws IOException {

//        fileReaderTest();
//        fileWriterTest();
//        bufferTest();

        /**
         * 获取一个文本文件中每一个字符出现的个数 ,将其结果写在times.txt中
         *
         * 分析:
         * 1.新建字符包装读写流对象
         * 2.新建双链集合对象,以键来存储字符,值来存储出现的次数(每出现一次加一)
         * 3.读取指定文本文档,读取一个字符就循环查询整个文本文件,记录出现次数.add()到双链集合中
         * 4.关闭流
         */

        BufferedReader iopro2 = new BufferedReader(new InputStreamReader(new FileInputStream("iopro2.txt"), "utf-8"));
        HashMap<Character, Integer> map = new HashMap(50);
        int len;
        while ((len = iopro2.read()) != -1) {
            char c = (char) len;
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
        }

        Set<Character> characters = map.keySet();
        for (Character character : characters) {
            Integer integer = map.get(character);
            System.out.println(character + ":" + integer);
        }


    }

    /**
     * 字符写出
     * 会有2048字节的缓冲流,所以必须关流,让字节flush到指定文件中
     */
    private static void bufferTest() {

        try {
            FileReader fileReader = new FileReader("fileWriter.txt");
            FileWriter fileWriter = new FileWriter("bufferTest.txt");
            int len;

            while ((len = fileReader.read()) != -1) {
                fileWriter.write(len);
            }
            fileReader.close();
            //不关闭输出流，数据会在内存中无法刷出到文件中，因为关闭流时会进行刷新
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * FileWriter的测试
     */
    private static void fileWriterTest() {
        try (
                /**
                 * 构造给布尔值，是否清空文件
                 */
                FileWriter fileWriter = new FileWriter("fileWriter.txt", true);
        ) {
            fileWriter.write("张三李四王二麻子\r\n");
            fileWriter.write(97);
            fileWriter.write("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * FileReader的测试
     */
    private static void fileReaderTest() {
        try (
                FileReader fileReader = new FileReader("fileReader.txt");
        ) {
            int len;
            while ((len = fileReader.read()) != -1) {
                System.out.print((char) len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
