package cn.dzygod.jdk5;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: dingziyuan
 * @Date: 2018-04-24 10:18
 * @Description: 数组转集合, 集合转数组
 */
public class AsList {
    public static void main(String[] args) {
        /*
             数组转集合
         */
//        referenceAs();
//        primaryAs();

        /*
            集合转数组
         */
//        toArray();


    }

    private static void toArray() {
        ArrayList<String> list = new ArrayList();
        list.add("张三");
        list.add("张三");
        list.add("张三");

        //数组参数的长度小于集合的size时,转换后的数组长度等于集合长度
        //当给定数组的长度大于集合的size时,转换后的数组长度就是你指定数组的长度
        String[] strings = list.toArray(new String[0]);
        for (String s : strings) {
            System.out.println(s);
        }
    }

    private static void primaryAs() {
        int[] i = {1, 2, 3, 4};
        List<int[]> ints = Arrays.asList(i);
        /*
            输出地址值,因为是基本数据类型的数组,整个数组是一个对象
            将int类型改为Integer即可掉调用toStrng输出
         */
        System.out.println(ints);
    }


    /**
     * 引用数据类型的集合转换为数组
     */
    private static void referenceAs() {
        String[] s = {"张三", "张三", "张三"};
        List<String> strings = Arrays.asList(s);
        System.out.println(strings);
    }

}
