package top.dzygod.jdk8.practice.chapternine;

/**
 * @Author: dingziyuan
 * @Date: 2018/10/3 10:34
 * @Description: 菱形继承问题
 */
public class DiamondExtendsProblem implements UnderOne,UnderTwo{
    public static void main(String[] args){
        new DiamondExtendsProblem().test();
    }

    @Override
    public void test() {
        UnderTwo.super.test();
    }
}

interface Top{
    default void test(){
        System.out.println("你好");
    };
}

interface UnderOne extends Top{
    @Override
    default void test() {
        System.out.println("我是一号");
    }
}

interface UnderTwo extends Top {
    @Override
    default void test() {
        System.out.println("我是二号");
    }
}

