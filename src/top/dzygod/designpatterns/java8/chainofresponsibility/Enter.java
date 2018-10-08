package top.dzygod.designpatterns.java8.chainofresponsibility;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/9 14:35
 * @Description: 责任链的入口
 */
public class Enter {
    public static void main(String[] args){
//        normal();
//        lambda();
    }


    /**
     * lambda实现,不需要所有的继承关系
     */
    private static void lambda() {
        UnaryOperator<String> headerTextProcessing =
                (String s) -> "From Raoul, Mario and Alan:" + s;

        UnaryOperator<String> spellCheckerProcessing =
                (String s) -> s.replaceAll("labda","lamda");

        //多次使用流水线方法andThen()可以持续增加责任链上的节点
        Function<String, String> function = headerTextProcessing
                .andThen(spellCheckerProcessing);
        System.out.println(function.apply("labda"));
    }


 /*   private static void normal() {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();

        p1.setSuccessor(p2);
        System.out.println(p1.handle("labda"));
    }*/

}
