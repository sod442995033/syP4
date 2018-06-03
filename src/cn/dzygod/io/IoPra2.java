package cn.dzygod.io;


import java.io.*;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * @author dzyGod
 * @description 字符流的操作练习
 */
public class IoPra2 {
    public static void main(String[] args) throws IOException {
//        fileReaderTest();
//        fileWriterTest();
//        bufferTest();
//        specialMethod();
//        docInversion();
//        lineNumberTest();
//        transformCharset();


    }

    /**
     * 用指定编码表进行高效读写
     *
     * unicode编码表一个中文3个字节，gbk一个中文2个字节。
     * 所以不指定编码就会出现乱码
     *
     * 用utf-8编码字符集进行读取
     * 再将读取数据用gbk编码表进行写出到文本文件中
     */
    private static void transformCharset() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("utf-8.txt"),"utf-8"));
        BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("gbk.txt"), "gbk"));

        int len;
        while ((len = bufferedReader.read()) != -1){
            bufferedWriter.write(len);
        }

        bufferedReader.close();
        bufferedWriter.close();
    }

    /**
     * LineNumberReader
     * LineNumberReader是BufferedReader的子类，具有相同的功能，并且可以统计行号
     * 调用getLineNumber（）方法可以获取当前行号
     * 调用setLineNumber（）方法可以设置当前行号
     */
    private static void lineNumberTest() throws IOException {

        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader("bufferTest.txt"));

        String line;
        //lineNumberReader中有一个成员变量lineNumber，它有set与get方法
        lineNumberReader.setLineNumber(100);
        while ((line = lineNumberReader.readLine()) != null) {
            System.out.println(lineNumberReader.getLineNumber() + ":" + line);
        }
        lineNumberReader.close();
    }

    /**
     * 文档反转
     * 将一个文档上的文本反转，第一行和倒数一行交换，第二行和倒数第二行交换
     * <p>
     * 分析：
     * 1.创建输入输出流
     * 2.创建集合对象
     * 3.将读到的数据存储在集合中
     * 4.倒着便利集合将数据写到文件中
     * 5.关流
     * <p>
     * 注意事项：
     * 晚开早关，什么时候用到再开，什么时候用完立即关
     */
    private static void docInversion() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader("bufferTest.txt"));

        ArrayList<String> strings = new ArrayList();

        String len;
        while ((len = bufferedReader.readLine()) != null) {
            strings.add(len);
        }
        //关流
        bufferedReader.close();

        BufferedWriter bufferWriter = new BufferedWriter(new FileWriter("bufferWriter"));
        for (int i = strings.size() - 1; i >= 0; i--) {
            bufferWriter.write(strings.get(i));
            bufferWriter.newLine();
        }
        bufferWriter.close();
    }

    /**
     * 带缓冲字符流的两个独特殊的方法
     * newLine();
     * readLine();
     * 与\r\n的区别：
     * 这两个方法都是跨平台的
     */
    private static void specialMethod() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader("bufferTest.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("fileWriter.txt"));

        String len;
        while ((len = bufferedReader.readLine()) != null) {
            bufferedWriter.write(len);
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
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
