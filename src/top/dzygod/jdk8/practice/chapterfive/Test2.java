package top.dzygod.jdk8.practice.chapterfive;

import top.dzygod.jdk8.practice.chapterfour.Dish;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/27 17:39
 * @Description: 第五章,使用流(2)
 */
public class Test2 {

    /**
     * 数值流
     * Stream API提供了原始类型流特化，专门支持处理数值流的方法
     * Java 8引入了三个原始类型特化流接口来解决这个问题：
     * IntStream 、 DoubleStream 和 LongStream ，
     * 分别将流中的元素特化为 int 、 long 和 double ，从而避免了暗含的装箱成本。
     */
    public static void main(String[] args){

//        special();
//        range();
//        pythagorean();


    }

    /**
     * 勾股数流
     * a*a + b*b = c*c
     */
    private static void pythagorean() {
        Stream<int[]> stream = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b ->
                                        new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
        stream.limit(5).forEach(t -> System.out.println(t[0] + " " + t[1] + " " + t[2]));


        /**
         * 更好的解决方案:
         * 生成所有三元数,再进行过滤
         */
        Stream<double[]> stream1 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                .filter(t -> t[2] % 1 == 0)
                );

        stream1.limit(5).forEach(t -> System.out.println(t[0] + " " + t[1] + " " + t[2]));
    }


    /**
     * 数值范围
     * Java 8引入了两个可以用于 IntStream 和 LongStream 的静态方法，帮助生成这种范围.
     * range 和 rangeClosed 。
     * 这两个方法都是第一个参数接受起始值，第二个参数接受结束值。
     * 但range 是不包含结束值的，而 rangeClosed 则包含结束值。
     */
    private static void range() {
        IntStream stream = IntStream.range(1, 100).filter(i -> i % 2 == 0);
        IntStream stream1 = IntStream.rangeClosed(1, 100).filter(i -> i % 2 == 0);
        System.out.println(stream1.count());
    }


    /**
     * 原始类型流特化
     */
    private static void special() {
        /**
         * 1. 映射到数值流
         * IntStream 还支持其他的方便方法，如max 、 min 、 average 等。
         */
        OptionalInt max = Test.menu.stream()
                .mapToInt(Dish::getCalories)
                .max();


        /**
         * 2. 转换回对象流
         * 下列代码先将Stream流转换为数值流,又将数值流转换为 Stream
         */
        IntStream stream = Test.menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> boxed = stream.boxed();

        /**
         * 3.默认值 OptionalInt
         */
        IntStream stream1 = Test.menu.stream().mapToInt(Dish::getCalories);
        OptionalInt max1 = stream1.max();
        max1.orElse(1);
    }
}
