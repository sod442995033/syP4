package top.dzygod.socket.tcp;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/30 18:16
 * @Description: 接受多线程的服务器端
 */
public class Tcp_server2 {


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

            new Thread(() -> {
                try {
                    Socket accept = server.accept();

                    PrintStream printStream = new PrintStream(accept.getOutputStream());
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(accept.getInputStream()));

                    printStream.println("这里是服务端发送来的消息,请仔细阅读!");
                    System.out.println(reader.readLine());
                    printStream.println("这里还是服务端发送来的消息,请仔细阅读!");
                    System.out.println(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}




