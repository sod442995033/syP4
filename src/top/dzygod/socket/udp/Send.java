package top.dzygod.socket.udp;

import java.io.IOException;
import java.net.*;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/26 18:33
 * @Description: UDP协议 Socket套接字的发送端
 */
public class Send {

    /**
     * 1.发送socket
     * 创建DatagramSocket, 随机端口号
     * 创建DatagramPacket  指定数据,长度,地址,端口
     * 使用DatagramSocket发送DatagramPacket
     * 关闭DatagramSocket
     * @param args
     */
    public static void main(String[] args) {

        byte[] message = "这是发过来的数据,你看对吗?".getBytes();


        try (DatagramSocket socket = new DatagramSocket();){
            DatagramPacket packet = new DatagramPacket(
                    message,message.length,InetAddress.getByName("127.0.0.1"),6666
            );
            socket.send(packet);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
