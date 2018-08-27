package top.dzygod.jdk8.practice.chaptertwo;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/16 13:12
 * @Description: 匿名内部类解密题
 */
public class MeaningOfThis {

    private final int value = 4;


    /**
     *  输出的结果会是神马?这三个value怎么在匿名内部类输出?
     */
    public static void main(String[] args){
        MeaningOfThis aThis = new MeaningOfThis();
        aThis.doIt();
    }

    private void doIt() {
        Runnable runnable = new Runnable(){
             private final int value = 5;

            @Override
            public void run() {
                     final int value = 10;
                     System.out.println(MeaningOfThis.this.value);
            }
        };
        runnable.run();
    }

}
