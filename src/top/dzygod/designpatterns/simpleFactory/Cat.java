package top.dzygod.designpatterns.simpleFactory;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/12 14:45
 * @Description: *
 */
public class Cat extends Animal{

    @Override
    public void eat() {
        System.out.println("我吃鱼,不吃肉!");
    }

}
