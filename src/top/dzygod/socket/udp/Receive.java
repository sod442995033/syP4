package top.dzygod.socket.udp;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/26 18:51
 * @Description: UDP协议 Socket套接字的接收端,
 */
public class Receive {


    /**
     * 接收Receive
     * 创建DatagramSocket,指定端口号
     * 创建DatagramPacket,指定数组,长度
     * 使用DatagramSocket接收DatagramPacket
     * 关闭DatagramSocket
     * 从DatagramPacket中获取数据
     * @param args
     */
    public static void main(String[] args){

        //插座里边放上包!!就可以发射了!
        try (DatagramSocket socket = new DatagramSocket(6666);){
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            socket.receive(packet);
            byte[] data = packet.getData();
            int length = packet.getLength();

            System.out.println(new String(data,0,length));

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
