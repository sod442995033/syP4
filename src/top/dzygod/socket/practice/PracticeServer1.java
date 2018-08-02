package top.dzygod.socket.practice;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/2 11:23
 * @Description: Socket学习之后的第二个练习
 */
public class PracticeServer1 {

    /**
     * 服务器上传文件
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {

        try (
                ServerSocket socket = new ServerSocket(6666);

        ) {

            System.out.println("当前服务器端口号6666");


            while (true) {
                Socket accept = socket.accept();
                new Thread(() -> {
                    try {
                        InputStream stream = accept.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                        PrintStream print = new PrintStream(accept.getOutputStream());
                        String child = reader.readLine();


                        File dir = new File("server");
                        dir.mkdir();

                        File serverFile = new File(dir, child);


                        if (serverFile.exists()) {
                            print.println("exists");
                            return;
                        }else{
                            print.println("!exists");
                        }

                        //接收上传的文件
                        try (FileOutputStream outputStream = new FileOutputStream(serverFile))
                        {
                            int len;
                            byte[] bytes = new byte[1024 * 6];

                            while ((len = stream.read(bytes)) != -1) {
                                outputStream.write(bytes, 0, len);
                            }
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }
}


