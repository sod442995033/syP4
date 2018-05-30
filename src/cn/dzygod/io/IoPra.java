package cn.dzygod.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: dingziyuan
 * @Date: 2018/5/30 16:29
 * @Description: io的练习
 */
public class IoPra {


    public static void main(String[] args) {
//        encryption();

    }


    /**
     * 加密图片
     * 对写入的字节进行异或,一个字节异或2次就是它本身
     */
    private static void encryption() {
        try (
                FileInputStream inputStream = new FileInputStream("图片加密.png");
                FileOutputStream outputStream = new FileOutputStream("图片解密.png");
            )
        {
            int len;
            while ((len = inputStream.read()) != -1){
                outputStream.write(len ^ 123);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
