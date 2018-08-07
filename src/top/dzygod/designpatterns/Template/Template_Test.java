package top.dzygod.designpatterns.Template;


import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/7 11:04
 * @Description: *
 */
public class Template_Test {

    public static void main(String[] args){


        //这段代码需要优化
        /*long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            System.out.println("yoxi");
        }
        long end = System.currentTimeMillis();*/

        Template_Son son = new Template_Son();
        System.out.println(son.getTime());
    }
}


//分解这个式子,做成模板
abstract class Template_Father{

    //不变的逻辑不需要子类关心
    public final long getTime() {
        long start = System.currentTimeMillis();
        code();
        long end = System.currentTimeMillis();
        return end - start;
    }

    //code部分可以进行抽象,和上层逻辑分离
/*
    private void code() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("yoxi");
        }
    }
*/

    abstract void code();
}

class Template_Son extends Template_Father{

    @Override
    void code() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("yoxi");
        }
    }


}