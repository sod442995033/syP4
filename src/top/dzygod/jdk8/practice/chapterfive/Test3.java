package top.dzygod.jdk8.practice.chapterfive;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/27 18:42
 * @Description: 创建流
 */
public class Test3 {

    public static void main(String[] args) {

//        test1();
//        test2();
//        test3();
//        unlimitedStream();
//        note();

    }

    private static void note() {
        /**
         *  Streams API可以表达复杂的数据处理查询。常用的流操作总结在表5-1中。
         *  你可以使用 filter 、 distinct 、 skip 和 limit 对流做筛选和切片。
         *  你可以使用 map 和 flatMap 提取或转换流中的元素。
         *  你可以使用 findFirst 和 findAny 方法查找流中的元素。你可以用 allMatch 、
         * noneMatch 和 anyMatch 方法让流匹配给定的谓词。
         *  这些方法都利用了短路：找到结果就立即停止计算；没有必要处理整个流。
         *  你可以利用 reduce 方法将流中所有的元素迭代合并成一个结果，例如求和或查找最大元素。
         *  filter 和 map 等操作是无状态的，它们并不存储任何状态。 reduce 等操作要存储状态才
         * 能计算出一个值。 sorted 和 distinct 等操作也要存储状态，因为它们需要把流中的所
         * 有元素缓存起来才能返回一个新的流。这种操作称为有状态操作。
         *  流有三种基本的原始类型特化： IntStream 、 DoubleStream 和 LongStream 。它们的操
         * 作也有相应的特化。
         *  流不仅可以从集合创建，也可从值、数组、文件以及 iterate 与 generate 等特定方法
         * 创建。
         *  无限流是没有固定大小的流。
         */

    }

    /**
     * 由函数生成流,创建无限流
     * Stream API提供了两个静态方法来从函数生成流： Stream.iterate 和 Stream.generate
     * 无限流：不像从固定集合创建的流那样有固定大小的流。
     */
    private static void unlimitedStream() {
        /**
         * 1.迭代
         * 斐波纳契数列 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55…
         * 数列中开始的两个数字是0和1，后续的每个数字都是前两个数字之和
         *  iterate 需要一个Lambda来确定后续的元素(UnaryOperator<t> 类型)
         */
        int[] ints = {0, 1};
        Stream.iterate(ints, in -> new int[]{in[1],in[0] + in[1]})
                .limit(20).map(t -> t[0]).
                forEach(System.out::println);

        /**
         * 2.生成
         * 与 iterate 方法类似， generate 方法也可让你按需生成一个无限流。
         * 但 generate 不是依次对每个新生成的值应用函数的。
         * 它接受一个 Supplier<T> 类型的Lambda提供新的值。
         *
         * getAsInt 在调用时会改变对象的状态
         * ，由此在每次调用时产生新的值。
         * 相比之下，使用 iterate 的方法则是纯粹不变的：它没有修改现有状态，但在每次迭代时会创建新的元组
         * 应该始终采用不变的方法，以便并行处理流，并保持结果正确
         */
        IntSupplier supplier = new IntSupplier() {
            private int prefix = 0;
            private int suffix = 1;

            @Override
            public int getAsInt() {
                int oldPrefix = prefix;
                int newSuffix = this.suffix + this.prefix;
                this.prefix = this.suffix;
                this.suffix = newSuffix;
                return oldPrefix;
            }
        };
        IntStream.generate(supplier).limit(20).forEach(System.out::println);
    }

    /**
     * 由文件生成流
     * 可以使用 Files.lines 得到一个流，其中的每个元素都是给定文件中的一行
     * 对 line 调用 split 方法将行拆分成单词
     * <p>
     * 看看文件中一共有多少不同的单词:
     */
    private static void test3() {
        try (
                Stream<String> lines =
                        Files.lines(Paths.get("测试.txt"), Charset.defaultCharset());
        ) {

            long count = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 由数组创建流
     */
    private static void test2() {

        int[] ints = {1, 2, 3, 4, 5};
        IntStream stream = Arrays.stream(ints);
        System.out.println(stream.sum());
    }

    /**
     * 由值创建流
     * 可以使用静态方法 Stream.of ，通过显式值创建一个流。它可以接受任意数量的参数
     * 可以使用 empty 得到一个空流
     */
    private static void test1() {
        Stream<String> a = Stream.of("a", "b", "c", "d");
        a.map(String::toUpperCase).forEach(System.out::println);

        Stream<String> empty = Stream.empty();
    }
}

