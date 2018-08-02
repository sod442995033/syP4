package top.dzygod.designpatterns.simpleFactory;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/12 11:48
 * @Description: 简单工厂设计模式
 */
public class AnimalFactory {


    public static void main(String[] args) {
        Cat cat = (Cat)AnimalFactory.getAnimalInstance("cat");
        Dog dog = (Dog) AnimalFactory.getAnimalInstance("dog");

        cat.eat();
        dog.eat();
    }

    public static Animal getAnimalInstance(String name) {

        if ("cat".equals(name)) {
            return new Cat();
        } else if ("dog".equals(name)) {
            return new Dog();
        } else {
            return null;
        }
    }

}
