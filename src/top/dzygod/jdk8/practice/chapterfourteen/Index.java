package top.dzygod.jdk8.practice.chapterfourteen;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @Author: dingziyuan
 * @Date: 2018/10/6 17:47
 * @Description: 第十四章, 函数式编程的技巧
 * 本章内容
 *  一等成员、高阶方法、科里化以及局部应用
 *  持久化数据结构
 *  生成Java Stream 时的延迟计算和延迟列表
 *  模式匹配以及如何在Java中应用
 *  引用透明性和缓存
 */
public class Index {
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();

    }

    private static void test3() {
        System.out.println(repeat(3, (Integer x) -> 2 * x).apply(10));
    }


    /**
     * 结合器,高阶函数(接受两个或多个函数，并返回另一个函数)
     * 函数组合（function composition）
     * <p>
     * 这个想法稍作变更可以对迭代概念的更丰富外延进行建模，甚至包括对在迭代之间传递可变状态的函数式模型。
     *
     * @param g
     * @param f
     * @param <A>
     * @param <B>
     * @param <C>
     * @return
     */
    static <A, B, C> Function<A, C> compose(Function<B, C> g, Function<A, B> f) {
        return x -> g.apply(f.apply(x));
    }

    static <A> Function<A, A> repeat(int n, Function<A, A> f) {
        return n == 0 ? x -> x : compose(f, repeat(n - 1, f));
    }


    /**
     * 柯里化
     * 表示一种将一个带有n元组参数的函数转换成n个一元函数链的方法
     * <p>
     * 你可以将一个使用了6个参数的函数科里化成一个接受第2、4、6号参数，
     * 并返回一个接受5号参数的函数，这个函数又返回一个接受剩下的第1号和第3号参数的函数。
     * <p>
     * 一个函数使用所有参数仅有部分被传递时，
     * 通常我们说这个函数是 部分应用的（partiallyapplied）。
     */
    private static void test2() {
        Stream.of(1, 3, 5, 7)
                .map(multiplyCurry(2))
                .forEach(System.out::println);
    }

    static Function<Integer, Integer> multiplyCurry(int x) {
        return (Integer y) -> x * y;
    }

    private static void test1() {
        /**
         * 函数式编程”意指函数或者方法的行为应该像“数学函数”一样——没有任何副作用
         *
         * 对于使用函数式语言的程序员而言，这个术语的范畴更加宽泛
         * 函数可以像任何其他值一样随意使用：可以作为参数传递，可以作为返回值，还能存储在数据结构中。
         *
         * 能够像普通变量一样使用的函数称为一等函数（first-class function）。
         *
         * 函数式编程的世界里，如果函数，比如 Comparator.comparing ，能满足下面任一要求就
         * 可以被称为高阶函数（higher-order function）：
         *  接受至少一个函数作为参数
         *  返回的结果是一个函数
         */
    }

}
