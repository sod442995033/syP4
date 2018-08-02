package top.dzygod.socket.tcp;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/30 18:16
 * @Description: 服务器端
 */
public class Tcp_server {


    /**
     * 服务端
     * 创建ServerSocket(需要指定端口号)
     * 调用ServerSocket的accept()方法接收一个客户端请求,得到一个socket
     * 调用Socket的getInputStream()和getOutputStream()方法获取和客户端相连的ip流
     * 输入流可以读取客户端输出流写出的数据
     * 输出流可以向写出数据到客户端的输入流
     *
     * @param args
     */
    public static void main(String[] args) {
        try (
                ServerSocket server = new ServerSocket(12345);
        ) {
            Socket accept = server.accept();

            InputStream inputStream = accept.getInputStream();
            OutputStream outputStream = accept.getOutputStream();
            byte[] bytes2 = "alouha!".getBytes();
            outputStream.write(bytes2);
            byte[] bytes = new byte[1024];
            //有入参的read()返回的是缓冲区的总字节数
            int len = inputStream.read(bytes);
            String string = new String(bytes, 0, len);
            System.out.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


