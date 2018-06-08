package cn.dzygod.io;


import cn.dzygod.bean.StudentPra;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.*;
import java.util.*;

/**
 * @author dzyGod
 * @description 字符流的操作练习
 */
public class IoPra2 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /**
         * 序列化版本号,流生成时会自动生成.
         *  流写入会一起写入文件,在读取时会对比版本号,
         *  版本号不符则会抛出java.io.InvalidClassException异常
         *  不加也是可以的,这个常量的作用仅仅是为了异常有更高的可读性
         *
         *         private static final long serialVersionUID = 2L;
         */

//        fileReaderTest();
//        fileWriterTest();
//        bufferTest();
//        specialMethod();
//        docInversion();
//        lineNumberTest();
//        transformCharset();
//        statistics();
//        numberOfTips();
//        byteStream();
//        byteStreamGarbled();
//        randomAccessFile();
//        objectOutTest();
//        objectInputTest();
//        optimization();
//        dataIO();
//        print();



    }




    /**
     * 数据输入输出流
     * DataInputStream(),DataOutputStream()
     */
    private static void dataIO() throws IOException {
        /**
         * 为什么要使用数据输入输出流?
         *  输出流的会将数据放进4个8位的int数组中,在写出的时候截取掉前三个八位
         *  997在int中应该是                         00000000 00000000 00000011 11100101
         *  在写入文件时会截取最后一个八位 11100101,变成 00000000 00000000 00000000 11100101 造成精度缺失
         */

    /*    FileOutputStream outputStream = new FileOutputStream("D.txt");
        outputStream.write(997);
        outputStream.write(998);
        outputStream.write(999);

        outputStream.close();

        FileInputStream inputStream = new FileInputStream("D.txt");

        int read = inputStream.read();
        int read1 = inputStream.read();
        int read2 = inputStream.read();

        inputStream.close();
        *//**
         * 输出
         * 229  00000000 00000000 00000000 11100101
         * 230  00000000 00000000 00000000 11100110
         * 231  00000000 00000000 00000000 11100111
         *//*
        System.out.println(read);
        System.out.println(read1);
        System.out.println(read2);
*/

        /**
         * 解决方案:
         *   使用DataInputStream与DataOutputStream
         *   不会遗失精度
         * 注意:
         *   需要在输入与输出时使用一致的方法,比如writeInt()对应的就是readInt();
         */

        DataOutputStream stream = new DataOutputStream(new FileOutputStream("D.txt"));
        stream.writeInt(997);
        stream.writeInt(998);
        stream.writeInt(999);

        stream.close();

        DataInputStream inputStream1 = new DataInputStream(new FileInputStream("D.txt"));

        int read3 = inputStream1.readInt();
        int read4 = inputStream1.readInt();
        int read5 = inputStream1.readInt();

        System.out.println(read3);
        System.out.println(read4);
        System.out.println(read5);

        inputStream1.close();

    }

    /**
     * 对象操作流优化
     * 存在集合中,
     * 一次写入,一次读取
     */
    private static void optimization() throws IOException, ClassNotFoundException {

        ArrayList<StudentPra> pras = new ArrayList<>();
        pras.add(new StudentPra("张三", 10, 2, 3));
        pras.add(new StudentPra("李四", 10, 2, 3));
        pras.add(new StudentPra("王五", 10, 2, 3));
        pras.add(new StudentPra("赵六", 10, 2, 3));
        pras.add(new StudentPra("马奇", 10, 2, 3));
        pras.add(new StudentPra("孙八", 10, 2, 3));

        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("O.txt"));
        stream.writeObject(pras);

        stream.close();

        ObjectInputStream stream1 = new ObjectInputStream(new FileInputStream("O.txt"));
        ArrayList<StudentPra> pras1 = (ArrayList<StudentPra>) stream1.readObject();
        for (StudentPra pra : pras1) {
            System.out.println(pra);
        }

        stream1.close();
    }

    /**
     * 对象对象输入流
     */
    private static void objectInputTest() throws IOException, ClassNotFoundException {
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream("O.txt"));
        StudentPra pra = (StudentPra) stream.readObject();
        StudentPra pra1 = (StudentPra) stream.readObject();
//        当文件读取到了末尾,继续读取会出现EOFException异常(end of file)
//        StudentPra pra2 = (StudentPra) stream.readObject();

        System.out.println(pra);
        System.out.println(pra1);
//        System.out.println(pra2);
    }

    /**
     * 对象输出流
     */
    private static void objectOutTest() throws IOException {
        StudentPra student = new StudentPra("李四", 10, 20, 30);
        StudentPra student1 = new StudentPra("张三", 10, 20, 30);

        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("O.txt"));
        stream.writeObject(student);
        stream.writeObject(student1);

        stream.close();
    }

    /**
     * 随机访问流和读写数据
     * RandomAccessFile概述
     * RandomAccessFile类不属于流,是Object的子类,但是它融合了InputStream与OutoutStream的功能
     * 支持随机访问文件的读取和写入
     */
    private static void randomAccessFile() throws IOException {

        //第二个参数
        RandomAccessFile file = new RandomAccessFile("g.txt", "rw");
        //在指定位置设置指针
        file.seek(10);

//        file.write(97);
//        int read = file.read();
//        System.out.println((char)read);


        file.close();
    }

    /**
     * 定义一个文件输入流,调用read(byte[])方法.将指定txt文件的内容打印出来(byte数组大小限制为五)
     * 对在内存中的内存输出流,进行全部一起解码,这样就不会出现只解码半个,也就不会出先乱码了
     */
    private static void byteStreamGarbled() throws IOException {

        FileInputStream inputStream = new FileInputStream("text.txt");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        int len;
        byte[] bytes = new byte[5];
        while ((len = inputStream.read(bytes)) != -1) {
            stream.write(bytes, 0, len);
        }

        inputStream.close();
        System.out.print(stream);

        stream.close();
    }

    /**
     * 内存输出流
     * 可以解决读取中文字符乱码的问题
     * 将数据全部读取到内存中一个可以自动增长的byte数组中(这样一来就不存在自定义byte数组,切分中文字符出现乱码的问题了)
     * 再使用输出流写出到指定文件
     */
    private static void byteStream() throws IOException {

        FileInputStream inputStream = new FileInputStream("text.txt");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        int len;
        while ((len = inputStream.read()) != -1) {
            stream.write(len);
        }

        inputStream.close();

        //根据操作系统默认码表进行转换
        byte[] bytes = stream.toByteArray();


        FileOutputStream outputStream = new FileOutputStream("test.txt");
        outputStream.write(bytes);

        stream.close();
        outputStream.close();
    }


    /**
     * 软件使用次数提示
     * 开始是五次,每次打开减一次.到零提示您的使用次数已用完
     */
    private static void numberOfTips() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("text.txt"));

        String line = reader.readLine();

        //对获取到的数值进行判断,如果大于0就将次数进行提示,否则提示试用次数已用尽
        Integer frequency;
        if ((frequency = Integer.parseInt(line)) > 0) {
            System.out.println("您还有" + frequency-- + "次试用次数!");
            //新建String参数实例,会初始化文本文件,为null
            FileWriter writer = new FileWriter("text.txt");
            writer.write(frequency.toString());
            writer.close();
        } else {
            System.out.println("您的次数已用尽,请充值!");
        }
    }

    /**
     * 获取一个文本文件中每一个字符出现的个数 ,将其结果写在times.txt中
     * <p>
     * 分析:
     * 1.新建字符包装读写流对象
     * 2.新建双链集合对象,以键来存储字符,值来存储出现的次数(每出现一次加一)
     * 3.读取指定文本文档,读取一个字符就循环查询整个文本文件,记录出现次数.add()到双链集合中
     * 4.关闭流
     */
    private static void statistics() throws IOException {

        Map<Character, Integer> map = new TreeMap();
        int len;

        BufferedReader iopro2 = new BufferedReader(
                new InputStreamReader(new FileInputStream("iopro2.txt"), "utf-8"));
        while ((len = iopro2.read()) != -1) {
            char c = (char) len;
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
        }
        iopro2.close();

        Set<Character> characters = map.keySet();
        for (Character character : characters) {
            Integer integer = map.get(character);
            System.out.println(character + ":" + integer);
        }

    }

    /**
     * 用指定编码表进行高效读写
     * <p>
     * unicode编码表一个中文3个字节，gbk一个中文2个字节。
     * 所以不指定编码就会出现乱码
     * <p>
     * 用utf-8编码字符集进行读取
     * 再将读取数据用gbk编码表进行写出到文本文件中
     */
    private static void transformCharset() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("utf-8.txt"), "utf-8"));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("gbk.txt"), "gbk"));

        int len;
        while ((len = bufferedReader.read()) != -1) {
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
