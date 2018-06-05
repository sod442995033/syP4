package cn.dzygod.io;

import java.io.*;
import java.time.temporal.ValueRange;
import java.util.Enumeration;
import java.util.Vector;

/**
 * @author dingziyuan
 * @description 序列流
 */
public class SequenceStream {

    public static void main(String[] args) throws IOException {
//        demo1();
//        integrate();


    }

    /**
     * 将三个MP3组合成一个mp3
     * 2个以上的序列流
     */
    private static void integrate() throws IOException {

        FileInputStream fileInputStream1 = new FileInputStream("C:\\Users\\Administrator\\Music\\G.E.M. 邓紫棋 - 光年之外.mp3");
        FileInputStream fileInputStream2 = new FileInputStream("C:\\Users\\Administrator\\Music\\Lovestoned - Bye Bye Bye.mp3");
        FileInputStream fileInputStream3 = new FileInputStream("C:\\Users\\Administrator\\Music\\Russ - Psycho (Pt. 2).mp3");

        Vector<InputStream> vector = new Vector();
        vector.add(fileInputStream1);
        vector.add(fileInputStream2);
        vector.add(fileInputStream3);
        Enumeration<InputStream> elements = vector.elements();

        SequenceInputStream sequenceInputStream = new SequenceInputStream(elements);
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Administrator\\Music\\all.mp3");

        byte[] bytes = new byte[1024 *  256];

        int len;
        while ((len = sequenceInputStream.read(bytes)) != -1){
            fileOutputStream.write(bytes);
        }

        sequenceInputStream.close();
        fileOutputStream.close();
    }

    /**
     * 两个文件的内容
     * 用序列流复制到一个文件中
     */
    private static void demo1() throws IOException {
        FileInputStream fileReader = new FileInputStream("utf-8.txt");
        FileInputStream fileReader2 = new FileInputStream("gbk.txt");
        SequenceInputStream sequenceStream = new SequenceInputStream(fileReader, fileReader2);

        FileOutputStream fileOutputStream = new FileOutputStream("sequence.txt");
        int byte_;
        while ((byte_ = sequenceStream.read()) != -1){
            fileOutputStream.write(byte_);
        }

        /**
         *  序列流close()方法会将构造传入的那两个流都关闭
         */
        sequenceStream.close();
        fileOutputStream.close();
    }
}
