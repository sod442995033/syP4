package top.dzygod.socket.udp;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/26 18:33
 * @Description: UDP协议 Socket套接字的发送端
 */
public class Send2 {

    /**
     * 1.发送socket
     * 创建DatagramSocket, 随机端口号
     * 创建DatagramPacket  指定数据,长度,地址,端口
     * 使用DatagramSocket发送DatagramPacket
     * 关闭DatagramSocket
     *
     * @param args
     */
    public static void main(String[] args) {


        /**
         * 键盘录入
         */

        Scanner scanner = new Scanner(System.in);

        try (DatagramSocket socket = new DatagramSocket()) {

            while (true) {
                String message = scanner.nextLine();
                if ("exit".equals(message)) {
                    break;
                }
                DatagramPacket packet = new DatagramPacket(
                        message.getBytes(), message.getBytes().length,
                        InetAddress.getByName("127.0.0.1"), 6666


                );
                socket.send(packet);

            }


        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
