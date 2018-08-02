package top.dzygod.socket.udp;


import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/26 18:51
 * @Description: UDP协议 Socket套接字的接收端,
 */
public class Demo_Thread {


    /**
     * 接收Receive
     * 创建DatagramSocket,指定端口号
     * 创建DatagramPacket,指定数组,长度
     * 使用DatagramSocket接收DatagramPacket
     * 关闭DatagramSocket
     * 从DatagramPacket中获取数据
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {


       Receive_Thread();
       Send_Thread();

    }

    private static void Send_Thread() {

        new Thread(() -> {


            byte[] bytes = new byte[1024];
            Scanner scanner = new Scanner(System.in);

            try (
                    DatagramSocket socket = new DatagramSocket();
            ) {
                while (true) {
                    String message = scanner.nextLine();
                    DatagramPacket packet = new DatagramPacket(bytes, 1024,
                            InetAddress.getByName("127.0.0.1"), 6666);
                    packet.setData(message.getBytes());
                    socket.send(packet);

                    if (message.equals("exit")) {
                        return;
                    }
                }

            } catch (SocketException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void Receive_Thread() {


        new Thread(() -> {

            try (
                    DatagramSocket socket = new DatagramSocket(6666);
            ) {
                while (true) {
                    DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
                    socket.receive(packet);

                    byte[] data = packet.getData();
                    int length = packet.getLength();

                    String message = new String(data, 0, length);

                    if ("exit".equals(message)) {
                        return;
                    }
                    System.out.println(message);
                }

            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}


