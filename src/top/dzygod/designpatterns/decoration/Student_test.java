package top.dzygod.designpatterns.decoration;

public class Student_test {
    public static void main(String[] args) {
        /**
         * 装饰者设计模式
         * 对指定对象进行装饰
         */
        new School().code();
        Heima heima = new Heima(new School());
        heima.code();
    }
}
