package top.dzygod.designpatterns.factorymethod;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/12 17:14
 * @Description: 工厂方法模式
 */
public class Test {
    public static void main(String[] args){

        DogFactory factory = new DogFactory();
        Dog dog = (Dog)factory.getAnimalInstance();
        dog.eat();

        CatFactory factory1 = new CatFactory();
        Cat cat = (Cat)factory1.getAnimalInstance();
        cat.eat();
    }
}
