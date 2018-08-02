package top.dzygod.socket.practice;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/2 12:03
 * @Description: TCP第二个练习, 上传文件(客户端)
 */
public class PracticeClient1 {

    /**
     * 重点!
     * TCP讲究一来一回,张弛有度,
     * 没有输出流是不可以访问客户端的
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {

        try (
                Socket socket = new Socket("127.0.0.1", 6666);
        ) {

            PrintStream print = new PrintStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            File file = getFile();

            //将文件名发送到服务器
            print.println(file.getName());
            if ("exists".equals(reader.readLine())) {
                System.out.println("文件已经存在了!");
                return;
            }

            //上传文件到服务器
            try (FileInputStream inputStream = new FileInputStream(file)) {
                int len;
                byte[] bytes = new byte[1024 * 6];

                while ((len = inputStream.read(bytes)) != -1) {
                    print.write(bytes, 0, len);
                }
            }

        }
    }

    private static File getFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入需要上传文件的路径:");

        while (true) {
            String line = scanner.nextLine();

            File file = new File(line);
            if (!file.exists()) {
                System.out.println("文件不存在!");
            } else if (file.isDirectory()) {
                System.out.println("是文件夹!");
            } else {
                return file;
            }
        }
    }


}
