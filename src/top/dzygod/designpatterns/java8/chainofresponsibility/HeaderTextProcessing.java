package top.dzygod.designpatterns.java8.chainofresponsibility;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/9 10:31
 * @Description: *
 */
public class HeaderTextProcessing extends ProcessingObject<String> {

    @Override
    protected String handleWork(String text) {
        return "From Raoul, Mario and Alan: " + text;
    }

}
