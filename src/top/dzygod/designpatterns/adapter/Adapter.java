package top.dzygod.designpatterns.adapter;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/26 17:57
 * @Description: 适配器设计模式(鲁智深为例子)
 */
public class Adapter {

        public static void main(String[] args){
            鲁智深 鲁智深 = new 鲁智深();
            鲁智深.练功();
            鲁智深.吃饭();
            鲁智深.念经();

        }
}


interface 和尚{
    public void 吃饭();

    public void 念经();

    public void 练功();
}

/**
 * 实现接口的空方法,抽象类防止创建实例
 */
abstract  class 和尚师傅 implements 和尚{

    @Override
    public void 吃饭() {
        System.out.println("我首先是人,我要吃饭");
    }

    @Override
    public void 念经() {
        System.out.println("我不能忘了我是个和尚!");
    }

    @Override
    public void 练功() {
        System.out.println("练不练功无所谓");
    }
}


class 鲁智深 extends 和尚师傅{
    @Override
    public void 练功() {
        System.out.println("我是鲁智深,我只练功不念经!");
    }
}