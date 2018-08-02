package top.dzygod.designpatterns.factorymethod;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/12 17:12
 * @Description: *
 */
public class CatFactory implements Factory{

    @Override
    public Animal getAnimalInstance() {
        return new Cat();
    }
}
