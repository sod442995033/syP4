package cn.dzygod.io;

import jdk.internal.util.xml.impl.Input;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author dzyGod
 * @description 字符流的操作练习
 */
public class IoPra2 {
    public static void main(String[] args) {

//        fileReaderTest();
//        fileWriterTest();
//        bufferTest();


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
