package top.dzygod.jdk8.行为参数化;

import java.util.Comparator;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/16 12:23
 * @Description: *
 */
public class Apple {
    private String color;
    private Integer weight;
    private String country;


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public  Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public Apple() {
    }

    public Apple(Integer weight) {
        this.weight = weight;
    }

    public Apple(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }


}
