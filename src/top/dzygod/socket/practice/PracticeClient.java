package top.dzygod.socket.practice;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/2 12:03
 * @Description: *
 */
public class PracticeClient {

    public static void main(String[] args) {
        try (
                Socket socket = new Socket("127.0.0.1", 6666);
        ) {
            Scanner scanner = new Scanner(System.in);



            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream));
            PrintStream printStream = new PrintStream(outputStream);



            System.out.println(reader.readLine());
//            printStream.println("这里是" + name + "客户端(✧◡✧)");
            System.out.println("输入你想发送的消息:");
            printStream.println(scanner.nextLine());
            System.out.println(reader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
