package top.dzygod.io;


import java.io.*;

/**
 * @Author: dingziyuan
 * @Date: 2018/5/15 16:09
 * @Description: IO流
 */
public class IoTest {


    public static void main(String[] args) throws IOException {
//        test();
//        fileOutConstru();
//        copyOne();
//        copyTwo();
//         copyThree();
//        copyFour();
//        copyFinal();
//        bufferedCopy();
//        flushAndClose();
//        garbledInSream();
//        garbledOutSteam();
//        standardProcessing();
//        newStandardProcessing();
    }

    /**
     * 1.7版本的标准异常处理代码
     *
     * 在try的小括号范围中的类,都实现了AutoCloseable接口,都重写了close()方法
     * try的大括号范围中执行完会执行小括号close()方法,关闭流
     */
    private static void newStandardProcessing() throws IOException {
        String path = "输出.txt";
        try (
                FileInputStream inputStream = new FileInputStream(path);
                FileOutputStream outputStream = new FileOutputStream(path);
        ) {
            int len;
            byte[] bytes = new byte[1024 * 2];
            while ((len = inputStream.read(bytes)) == -1) {
                outputStream.write(bytes, 0, len);
            }
        }
    }


    /**
     * 流的标准处理代码,1.6以前
     */
    private static void standardProcessing() throws IOException {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        String path = "输出.txt";
        try {
            inputStream = new FileInputStream(path);
            outputStream = new FileOutputStream(path);

            int len;
            byte[] arr = new byte[1024 * 8];
            while ((len = inputStream.read(arr)) != -1) {
                outputStream.write(arr, 0, len);
            }
        } finally {

            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        }
    }

    /**
     * 字节流写中文出现的问题
     * 字节流直接操作字节,所以写中文必须将字符串转换为字节数组
     * 写出回车换行write("\r\n".getBytes());
     */
    private static void garbledOutSteam() throws IOException {
        File file = new File("文件夹.txt/nihao.txt");
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write("我很好".getBytes());
        outputStream.write("\r\n".getBytes());

        outputStream.close();
    }

    /**
     * 字节流读中文出现的问题
     * 字节流读中文的时候,有可能会读到半个中文,造成乱码
     *
     * @throws IOException
     */
    private static void garbledInSream() throws IOException {
        //        File file = new File("文件夹.txt/nihao.txt");
//        FileInputStream inputStream = new FileInputStream(file);

        byte[] arrs = new byte[2];
        FileInputStream inputStream = new FileInputStream("文件夹.txt/nihao.txt");
        int len;
        while ((len = inputStream.read(arrs)) != -1) {
            /**
             *  打印出乱码,因为2个字节只能读取中文字符的一部分,转换成字符串自然会出现乱码
             */
            System.out.println(new String(arrs, 0, len));
        }
        inputStream.close();
    }

    /**
     * 刷新缓冲区
     * 关闭流之前会有一次flush刷新,将缓冲区的字节全都刷新到文件上,再将流关闭
     * 防止最后写不满的字节数据没有写出就被关闭
     * flush()刷新缓冲区后可以再次写出
     * close()带缓冲区的流对象的这个方法,会在关闭前刷新缓冲区,关闭后不能再写出
     */
    private static void flushAndClose() throws IOException {

        BufferedInputStream stream = new BufferedInputStream(new FileInputStream("屏幕截图(4).png"));
        BufferedOutputStream stream1 = new BufferedOutputStream(new FileOutputStream("图片.png"));

        int len;
        while ((len = stream.read()) != -1) {
            stream1.write(len);
        }


        stream.close();
        stream1.close();
    }


    /**
     * 带缓冲区的字节传输
     * 缓冲思想:
     * 字节流一次读写一个数组的速度明显比一次读取一个字节的速度快很多
     * 这是因为加入了数组这样的缓冲区效果,java本身在设计的时候
     * 也考虑了这样的设计思想(装饰设计模式),所以提供了字符缓冲流
     * BufferedInputStream
     * BufferedInputStream内置了一个缓冲区(数组)
     * 从BufferedInputStream中读取一个字节时
     * BufferedInputStream会一次性从文件中读取8192个,存在缓冲区里,返回给程序一个
     * 程序再次读取时,就不用找文件了,直接从缓冲区获取
     * 知道缓冲区所有都被使用过,才重新从文件中读取8192个
     * BufferedOutputStream
     * BufferedOutputStream内置了一个缓冲区(数组)
     * 程序向流中写出字节时,不会直接写到文件,先写到缓冲区中
     * 直到缓冲区写满,BufferedOutputStream才会把缓冲区的文件一次性写到文件里
     */
    private static void bufferedCopy() throws IOException {


        /**
         * 定义的小数组如果是8192子节大小和buffer比较的话
         * 小数组会略胜一筹,因为读写都是使用一个数组
         *  Buffered操作的是两个数组
         */
        flushAndClose();
    }


    /**
     * 用小数组去拷贝
     * 1024字节的整数倍
     */
    private static void copyFinal() throws IOException {

        FileInputStream stream = new FileInputStream("屏幕截图(4).png");
        FileOutputStream stream1 = new FileOutputStream("复制2.png");

        //1mb
        byte[] bytes = new byte[1024 * 1024];

        int len;
        while ((len = stream.read(bytes)) != -1) {
            //写入byte数组从0开始len长度的字节
            stream1.write(bytes, 0, len);
        }

        stream.close();
        stream1.close();
    }


    /**
     * 读取多余字节的方法
     */
    private static void copyFour() throws IOException {


        byte[] bytes = new byte[2];
        FileInputStream istream = new FileInputStream("测试.txt");
        FileOutputStream stream = new FileOutputStream("测试2.txt");

        //存储有效字节数
        int len;
        while ((len = istream.read(bytes)) != -1) {
            //写入bytes数组中从0开始len长度的字节
            stream.write(bytes, 0, len);
        }

        istream.close();
        stream.close();
    }


    /**
     * 定义小数组
     * 输出97,98,99,98
     * 因为一个byte存储2个字节,第二次只有一个有效字节,会覆盖第一个,但是第二个却没有变
     */
    private static void copyThree() throws IOException {


        byte[] bytes = new byte[2];
        FileInputStream istream = new FileInputStream("测试.txt");

        //获得读取的字节数
        int read = istream.read(bytes);
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }

        int read2 = istream.read(bytes);
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }
        istream.close();
    }


    /**
     * IO流的文件复制
     * 整个文件的字节拷贝,获取整个文件的字节数,整个进行读写
     * 所有字节全部读入内存,文件大于内存空间会引起内存溢出
     */
    private static void copyTwo() throws IOException {

        FileInputStream stream = new FileInputStream("复制.png");
        byte[] bytes = new byte[stream.available()];

        FileOutputStream stream1 = new FileOutputStream("复制1.png");
        while (stream.read(bytes) != -1) {
            stream1.write(bytes);
        }

        stream.close();
        stream1.close();
    }


    /**
     * IO流的文件复制
     * 逐个字节的拷贝,逐个字节进行读写
     * 这种方式比较慢
     */
    private static void copyOne() throws IOException {

        FileInputStream stream = new FileInputStream("屏幕截图(4).png");
        FileOutputStream stream1 = new FileOutputStream("复制.png");

        int b;
        while ((b = stream.read()) != -1) {
            stream1.write(b);
        }
        stream.close();
        stream1.close();
    }


    /**
     * fileOutputStream的构造
     *
     * @throws IOException
     */
    private static void fileOutConstru() throws IOException {
        /**
         * 字节输出流
         *  如果找不到指定路径下的文件,字节输出流会自动创建一个文件
         */
        FileOutputStream outStream = new FileOutputStream("输出.txt");
        //写入int类型的数值,最终会去除int的前三个字节(前三个八位),在文件中只有一个字节,
        outStream.write(97);
        outStream.write(98);
        outStream.write(99);

        outStream.close();

        /**
         * 字节输出流每次新的写入都会把文件清空
         * 想要在不清空文件的前提下续写,需要在使用该构造器
         */

        FileOutputStream stream = new FileOutputStream("输出.txt", true);

        stream.write(97);
        stream.write(98);
        stream.write(99);
        stream.close();
    }


    /**
     * io流常用父类
     * 字节流的抽象父类
     * InputStream
     * OutputStream
     * 字符流的抽象父类
     * Reader
     * Writer
     * IO程序书写
     * 使用前，导入IO包中的类
     * 使用时，进行IO异常处理
     * 使用后，释放资源
     */
    private static void test() throws IOException {


        //防止文件不存在
        InputStream stream = new FileInputStream("测试.txt");
        //当读出的数值不是-1，继续执行循环体
        int b;
        while ((b = stream.read()) != -1) {
            System.out.println(b);
        }

        stream.close();
        System.exit(0);


        //每次读取下标会向后移动一位
        //返回码表内的位置
       /* int read = stream.read();
        System.out.println(read);
        int read1 = stream.read();
        System.out.println(read1);
        int read2 = stream.read();
        System.out.println(read2);

        //关闭释放资源
        stream.close();*/
    }


}
