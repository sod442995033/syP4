package cn.dzygod.designpatterns;

public class Test {
    public static void main(String[] args) {
        /**
         * 装饰者设计模式
         */
        new School().code();
        Heima heima = new Heima(new School());
        heima.code();
    }
}
