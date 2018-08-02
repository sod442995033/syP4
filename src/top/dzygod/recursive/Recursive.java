package top.dzygod.recursive;

/**
 * @author dingziyuan
 * @description 递归的练习
 */
public class Recursive {

    public static void main(String[] args) {

        /**
         * 递归:自己调用自己
         * 5的阶乘
         *
         * 递归的弊端:不能调用次数过多,容易导致栈内存溢出
         * 递归的好处:不用知道循环次数
         *
         * 构造方法不能使用递归调用
         *
         * 递归调用不一定必须要有返回值,可以有也可以没有
         */
            System.out.println(factorial(10000));

    }

    private static int factorial(int num) {

        if (num == 1) {
            return 1;
        } else {
            return num = num * factorial(num - 1);
        }


    }

}
