package top.dzygod.designpatterns.java8.chainofresponsibility;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/9 10:32
 * @Description: *
 */
public class SpellCheckerProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String text) {
        return text.replaceAll("labda", "lambda");
    }
}
