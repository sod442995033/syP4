package top.dzygod.designpatterns.factorymethod;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/12 17:13
 * @Description: *
 */
public class DogFactory implements Factory{


    @Override
    public Animal getAnimalInstance() {
        return new Dog();
    }
}
