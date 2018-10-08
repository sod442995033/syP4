package top.dzygod.jdk8.practice.chapterfour;

import top.dzygod.jdk8.practice.chaptersix.Test1;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/27 9:43
 * @Description: *
 */
public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public enum Type {MEAT, FISH, OTHER}

    public Test1.CaloricLevel getCaloricLevel() {
        if (this.getCalories() <= 400) {
            return Test1.CaloricLevel.DIET;
        } else if (this.getCalories() <= 700) {
            return Test1.CaloricLevel.NORMAL;
        } else {
            return Test1.CaloricLevel.FAT;
        }
    }

}
