package top.dzygod.designpatterns.factorymethod;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/12 14:44
 * @Description: *
 */
public class Dog extends Animal {

    @Override
    public void eat() {
        System.out.println("我吃肉,不吃屎!");
    }

}
