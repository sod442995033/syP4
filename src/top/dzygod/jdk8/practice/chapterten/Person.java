package top.dzygod.jdk8.practice.chapterten;

/**
 * @Author: dingziyuan
 * @Date: 2018/10/3 10:58
 * @Description: *
 */
public class Person {
    private Car car;

    public Car getCar() {
        return car;
    }
}


class Car {
    private Insurance insurance;

    public Insurance getInsurance() {
        return insurance;
    }
}


class Insurance {
    private String name;

    public String getName() {
        return name;
    }
}

