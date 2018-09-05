package top.dzygod.jdk8.practice.chapterthree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/17 11:59
 * @Description: java8自带的三个函数式接口api
 */
public class Test1 {

    /**
     * java.util.function.Predicate<T>
     * @param args
     */
    public static void main(String[] args){

//        predicate();
//        consumer();
//        function();
//        note();


    }

    @SuppressWarnings("AlibabaAvoidCommentBehindStatement")
    private static void note() {
        /**
         * 如果一个Lambda的主体是一个语句表达式(list.add()这样的)，
         * 它就和一个返回 void 的函数描述符兼容（当然需要参数列表也兼容）。
         */
        ArrayList<String> list = new ArrayList<>();

        //java编译器可以通过上下文推断lambda的参数类型,省去标注参数类型,
        //当lambda仅有一个类型需要推断的参数时,参数名称两边的括号也可以省略

        //没有类型推断
        Predicate<String> p = s -> list.add(s);
        Consumer<String> c = s -> list.add(s);
        //有类型推断
        Predicate<String> p1 =(String s)  -> list.add(s);
        Consumer<String> c1 = (String s) -> list.add(s);

        /**
         *   Object o = () -> {System.out.println("Tricky example"); };
         *   无法通过编译,因为lambda表达式的上下文(目标类型)是Object.
         *   但Object不是一个函数式接口
         */


    }


    /**
     * function
     * 传入一个对象,可以返回其他任意对象
     */
    private static void function() {
        map(Arrays.asList("123", "42", "2131"),(String i) -> i.equals("123"));
    }


    private static <T,R> void map(List<T> list ,Function<T,R> function) {
        for (T t : list) {
            System.out.println(function.apply(t));
        }
    }


    /**
     * java.util.function.Consumer<T> 定义了一个名叫 accept 的抽象方法
     * 接受对象,没有返回值
     */
    private static void consumer() {
        foreach(Arrays.asList("中昂三", "撒大声", "加啊四开发电缆"),
                (String s)-> System.out.println(s));
    }


    /**
     * 打印list中的每一个数
     * @param consumer
     * @param <T>
     */
    public static <T> void foreach(List<T> strings,Consumer<T> consumer) {
        for (T string : strings) {
            consumer.accept(string);
        }
    }

    /**
     * java.util.function.Predicate<T>这个函数式接口,自带了() -> boolean这样的lamda
     */
    private static void predicate() {
        String[] s = {"占山","里斯","王阿布"};
        List<String> strings = Arrays.asList(s);
        List<String> strings1 = filter(strings, (String s1) -> s1.equals("占山"));
        System.out.println(strings1);
    }

    public static <T> List<T> filter(List<T> list,Predicate<T> predicate) {

        ArrayList<T> ts = new ArrayList<>();

        for (T t : list) {
            if (predicate.test(t)) {
                ts.add(t);
            }
        }
        return ts;
    }

}
