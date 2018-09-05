package top.dzygod.jdk8.practice.chapterfour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/27 9:29
 * @Description: 第三章streanAPI
 */
public class Test1 {

    private static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );



    /**
     * java8 中的streamAPI写出的代码有以下几个特点
     * 1.声明性 ----更简洁,易读
     * 2.可复合 ---- 更灵活
     * 3.可并行 ---- 性能更好
     *
     * @param args
     */
    public static void main(String[] args) {
//        demo1();
//        listStreamDifferent();
//        terminalAndMiddle();
//        note();

    }



    private static void note() {
        /**
         *  以下是你应从本章中学到的一些关键概念。
         *  流是“从支持数据处理操作的源生成的一系列元素”。
         *  流利用内部迭代：迭代通过 filter 、 map 、 sorted 等操作被抽象掉了。
         *  流操作有两类：中间操作和终端操作。
         *  filter 和 map 等中间操作会返回一个流，并可以链接在一起。可以用它们来设置一条流
         * 水线，但并不会生成任何结果。
         *  forEach 和 count 等终端操作会返回一个非流的值，并处理流水线以返回结果。
         *  流中的元素是按需计算的。
         */

    }


    /**
     * 流的操作分为中间操作和终端操作
     * 可以连接起来的流称为中间操作,关闭流的操作称为终端操作
     * 除非流水线上触发一个终端操作,否则中间操作不会进行任何处理,终端操作一次性全部处理
     */
    private static void terminalAndMiddle() {
        /**
         * 好几种优化利用了流的延迟性质。
         * 第一，尽管很多菜的热量都高于300卡路里,但只选出了前三个！这是因为 limit 操作和一种称为短路的技巧
         * 第二，尽管 filter 和 map 是两个独立的操作，但它们合并到同一次遍历中了（我们把这种技术叫作循环
         * 合并）。
         */
        List<String> collect = menu.stream().filter(dish -> {
            System.out.println("filter " + dish.getName());
            return dish.getCalories() > 300;
        }).map(dish -> {
            System.out.println("map " + dish.getName());
            return dish.getName();
        }).limit(3).collect(Collectors.toList());
        System.out.println(collect);


        //终端操作返回任意不是流的值,这一串代码返回的是void
        menu.stream().collect(Collectors.toList()).forEach(System.out::print);
    }

    /**
     * 流与集合的区别
     * 内外部迭代关键在于划分,内部迭代可以理解为不用显式的展示出迭代过程,stream流会自动处理
     */
    private static void listStreamDifferent() {
        //用for-each外部迭代
        ArrayList<String> strings = new ArrayList<>();
        for (Dish dish : menu) {
            strings.add(dish.getName());
        }

        //用stream做内部迭代=
        List<String> collect = menu.parallelStream().map(Dish::getName)
                .collect(Collectors.toList());
    }


    /**
     * 除了collect以外,所有的操作都返回另一个流,这样他们就可以接成一条流水线,可以看做对源的一个查询
     * filter(筛选),map(提取),limit(截断)
     */
    private static void demo1() {
        List<String> collect = menu.parallelStream()
                .filter(dish -> dish.getCalories() > 300)
                .sorted(Comparator.comparing(Dish::getCalories).reversed())
                .map(Dish::getName).limit(3).collect(Collectors.toList());

        System.out.println(collect);
    }
}
