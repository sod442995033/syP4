package top.dzygod.jdk8.行为参数化;

import java.util.ArrayList;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/16 12:46
 * @Description: 策略模式
 */
public class AppleRedAndHeavyPredicate implements ApplePredicate{


    @Override
    public boolean test(Apple apple) {
        return apple.getColor().equals("红色")&& apple.getWeight() > 150;
    }



}
