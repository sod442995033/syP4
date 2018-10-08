package top.dzygod.jdk8.practice.chapterten;

import java.util.Optional;

/**
 * @Author: dingziyuan
 * @Date: 2018/10/3 10:58
 * @Description: *
 */
public class PersonTwo {
    private Optional<CarTwo> car;

    public Optional<CarTwo> getCar() {
        return car;
    }
}


class CarTwo {
    private Optional<InsuranceTwo> insurance;

    public Optional<InsuranceTwo> getInsurance() {
        return insurance;
    }
}


class InsuranceTwo {
    private String name;

    public String getName() {
        return name;
    }
}

