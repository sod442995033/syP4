package top.dzygod.jdk5;

/**
 * @Author: dingziyuan
 * @Date: 2018-04-24 9:51
 * @Description: 1.5新特性可变参数
 */
public class ChangeableArgs {

    public static void main(String[] args) {
        /**
         *  可变参数概述:
         *      定义方法的时候不知道该定义多少个参数
         *   格式:
         *      修饰符 返回值类型 方法名
         *   注意事项:
         *      这里的参数其实是一个数组
         *      如果一个方法中有多个参数,可变参数必须是最后一个
         */

        Integer[] i = {1,2,3,4,5};
//        changeArg(i);
        /*
            与转递数组的区别:
                1.参数不写不会报错
                       changeArg();
                2.可以直接传递多个参数
                        changeArg(1,2);
                3.存在多个参数时,可变参数必须为最后一个
                        changeArg(11,2,3,4,5);
         */




    }



   /* private static void changeArg(Integer ... i){
        for (Integer j : i ) {
            System.out.println(j);
        }
    }
    */
    private static void changeArg(Integer k, Integer... i){
        for (Integer j : i ) {
            System.out.println(j);
        }
    }
}
