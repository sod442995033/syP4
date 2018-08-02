package top.dzygod.socket.tcp;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.*;
import java.net.Socket;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/30 18:09
 * @Description: 第二个客户端
 */
public class Tcp_client2 {

    /**
     * 客户端
     * 创建Socket链接服务端(指定ip地址,端口号)通过ip地址寻找响应的服务器
     * 调用Socket的getInputStream()和getOutputStream()方法获取和服务器端相连的io流
     * 输入流可以读取服务端输出流写入的数据
     * 输出流可以写出数据到服务端的输出流
     *
     * @param args
     */
    public static void main(String[] args) {

        try (
                Socket socket = new Socket("127.0.0.1", 12345);
        )
        {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintStream printStream= new PrintStream(socket.getOutputStream());

            System.out.println(reader.readLine());
            printStream.println("这里是客户端发送的消息哦(★ ω ★)");
            System.out.println(reader.readLine());
            printStream.println("这里还是客户端发送的消息哦(★ ω ★)");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
