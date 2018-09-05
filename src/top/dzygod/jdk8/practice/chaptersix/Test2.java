package top.dzygod.jdk8.practice.chaptersix;

import top.dzygod.collect.Collect;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/4 10:58
 * @Description: *
 */
public class Test2 {

    public static void main(String[] args){

        /**
         *  仅用质数做除数
         *  必须自己开发一个收集器的原因在于，在收集过程中是没有办法访问部分结果的
         *  这意味着，当测试某一个数字是否是质数的时候，你没法访问目前已经找到的其他质数的列表
         *   filter要处理整个流才能返回恰当的结果
         *
         */
        long fastest = Long.MAX_VALUE;

        for (int i = 0; i < 10; i++) {
            long startTime = System.nanoTime();
            partitionPrimesWithCustomCollector(100_0000);
            long duration = (System.nanoTime() - startTime) / 100_0000;
            if (duration < fastest){fastest = duration;}
        }
            System.out.println(fastest + "msecs");
    }

    private void noete() {
        /**
         * 以下是你应从本章中学到的关键概念。
         *  collect 是一个终端操作，它接受的参数是将流中元素累积到汇总结果的各种方式（称
         * 为收集器）。
         *  预定义收集器包括将流元素归约和汇总到一个值，例如计算最小值、最大值或平均值。
         * 这些收集器总结在表6-1中。
         *  预定义收集器可以用 groupingBy 对流中元素进行分组，或用 partitioningBy 进行分区。
         *  收集器可以高效地复合起来，进行多级分组、分区和归约。
         *  你可以实现 Collector 接口中定义的方法来开发你自己的收集器。
         */
    }


    private static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }
}
