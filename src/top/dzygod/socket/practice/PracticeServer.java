package top.dzygod.socket.practice;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/2 11:23
 * @Description: Socket学习之后的第一个练习
 */
public class PracticeServer {

    /**
     * 客户端向服务器写字符串(键盘录入),
     * 服务器(多线程)将字符串反转后写回,
     * 客户端再次读取到是反转后的字符串
     *
     * @param args
     */
    public static void main(String[] args) {

        while (true) {
             new Thread(() -> {
                Socket accept = null;
                try (ServerSocket socket = new ServerSocket(6666)) {
                    accept = socket.accept();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(accept.getInputStream()));
                    PrintStream printStream = new PrintStream(accept.getOutputStream());

                    printStream.println("这里接收字符串并进行反转~!!");
                    String reverse = new StringBuilder(reader.readLine()).reverse().toString();
                    System.out.println(reverse);
                    printStream.println(reverse);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}


