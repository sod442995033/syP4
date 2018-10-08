package top.dzygod.designpatterns.java8.strategy;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/8 10:43
 * @Description: 策略模式
 */
public interface ValidationStrategy {
    boolean execute(String s);

}
