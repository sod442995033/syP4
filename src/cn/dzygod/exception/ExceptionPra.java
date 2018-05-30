package cn.dzygod.exception;

import java.lang.Exception;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * @Author: dingziyuan
 * @Date: 2018/5/14 11:14
 * @Description: *
 */
public class ExceptionPra {
    public static void main(String[] args) {


        /**
         *
         * 键盘录入一个int类型的整数,对其求二进制的表现形式
         * 如果录入的整数过大,给予提示,录入的整数过大请重新输入一个整数BigInteger
         * 如果录入的是小数,给予提示,录入的是小数,请重新录入一个整数
         * 如果录入的是其他字符,给予提示,录入的是非法字符,请重新录入一个整数
         */


        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入整数:");
        while (true){
        String s = scanner.nextLine();
            try {
                String string = Integer.toBinaryString(Integer.parseInt(s));
                System.out.println(string);
                break;
            } catch (NumberFormatException e) {
                try {
                    //超过范围抛出异常,不超过不会出错,执行打印
                    new BigInteger(s);
                    System.out.println("数值过大重输:");
                } catch (NumberFormatException e1) {
                    try {
                        //超过范围抛出异常,不超过不会出错,执行打印
                        new BigDecimal(s);
                        System.out.println("不可输入浮点数,重输:");
                    } catch (Exception e2) {
                        System.out.println("不可为非法字符,重输:");
                    }
                }
            }

        }

       /* for (;;) {
            String ints  = scanner.nextLine();
            try {
                String string = Integer.toBinaryString(Integer.parseInt(ints));
                System.out.println("输出的值为:"+string);
                break;
            } catch (Exception e) {
                try {
                    new BigInteger(ints);
                    System.out.println("录入的整数过大,请重新输入一个整数:");
                } catch (Exception e1) {
                    try {
                        new BigDecimal(ints);
                        System.out.println("录入的是小数,重新录入一个整数:");
                    } catch (Exception e2) {
                        System.out.println("录入的是非法字符,重新输入一个整数:");
                    }
                }
            }
        }*/




    }
}
