package top.dzygod.designpatterns.java8.strategy;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/8 10:44
 * @Description: *
 */
public class IsNumeric implements ValidationStrategy {
    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}
