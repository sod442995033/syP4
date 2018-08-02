package top.dzygod.socket.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/30 18:09
 * @Description: 客户端
 */
public class Tcp_client {

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
        ) {

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            byte[] bytes2 = new byte[1024];
            int len = inputStream.read(bytes2);
            System.out.println(new String(bytes2,0, len));


            byte[] bytes = "你好".getBytes();
            outputStream.write(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
