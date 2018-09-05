package top.dzygod.jdk8.practice.chapterfive;

import top.dzygod.jdk8.practice.chapterfour.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/27 11:32
 * @Description: 第五章,使用流
 */
public class Test {

    public static List<Dish> menu = Arrays.asList(
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

    public static void main(String[] args){

//        test1();
//        test2();
//        test3();
//        match();
//        findEle();
//        reduction();
//        reduceTest();

    }

    /**
     * 归约的练习
     * 利用map和reduce计算一共有多少道菜
     */
    private static void reduceTest() {

        Integer reduce = menu.stream().map(dish -> 1).reduce(0, Integer::sum);
        long count = menu.stream().count();
        System.out.println(count);
    }


    /**
     * 归约(将流归约成一个值)
     * 函数式编程语言的术语来说，这称为折叠（fold），因为你可以将这个操
     * 作看成把一张长长的纸（你的流）反复折叠成一个小方块，而这就是折叠操作的结果。
     */
    private static void reduction() {
        //元素求和,Lambda反复结合每个元素，直到流被归约成一个值。
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);

        /**
         * 在Java 8中， Integer 类现在有了一个静态的
         * sum方法来对两个数求和，这恰好是我们想要的，用不着反复用Lambda写同一段代码了
         * Integer reduce = integers.stream().reduce(0, (a, b) -> a + b);
         * reduce 还有一个重载的变体，它不接受初始值，但是会返回一个 Optional 对象
         */
        Integer reduce = integers.stream().reduce(0, Integer::sum);
        Optional<Integer> sum = integers.stream().reduce(Integer::sum);
        Optional<Integer> max = integers.stream().reduce(Integer::max);
        Optional<Integer> min = integers.stream().reduce(Integer::min);
        System.out.println(min.get());
    }

    private static void findEle() {
        /**
         * 查找元素
         * findAny 方法将返回当前流中的任意元素
         * 流水线将在后台进行优化使其只需走一遍，并在利用短路找到结果时立即结束
         * Optional<T> 类（ java.util.Optional ）是一个容器类，代表一个值存在或不存在。
         */
        Optional<Dish> any = menu.stream().filter(Dish::isVegetarian).findAny();

        /**
         * 有些流有一个出现顺序（encounter order）来指定流中项目出现的逻辑顺序（比如由 List 或
         * 排序好的数据列生成的流）
         * 对于这种流，你可能想要找到第一个元素
         * 为此有一个 findFirst
         * 方法，它的工作方式类似于 findany
         */
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> first = integers.stream()
                .map(i -> i * i).filter(i -> i % 3 == 0).findFirst();
        System.out.println(first.get());


        /**
         * 何时使用 findFirst 和 findAny
         * 你可能会想，为什么会同时有 findFirst 和 findAny 呢？答案是并行。找到第一个元素
         * 在并行上限制更多。如果你不关心返回的元素是哪个，请使用 findAny ，因为它在使用并行流
         * 时限制较少。
         */}


    /**
     * 查找和匹配
     * anyMatch 方法可以回答“流中是否有一个元素能匹配给定的谓词”,返回为boolean类型,是终端操作
     * allMatch 方法的工作原理和 anyMatch 类似，但它会看看流中的元素是否都能匹配给定的谓词
     * noneMatch 方法可以确保流中没有任何元素与给定的谓词匹配
     *
     * anyMatch 、 allMatch 和 noneMatch 这三个操作都用到了我们所谓的短路，这就是大家熟悉
     * 的Java中 && 和 || 运算符短路在流中的版本。
     */
    private static void match() {

        /**
         * 短路求值
         * 有些操作不需要处理整个流就能得到结果。例如，假设你需要对一个用 and 连起来的大布
         * 尔表达式求值。不管表达式有多长，你只需找到一个表达式为 false ，就可以推断整个表达式
         * 将返回 false ，所以用不着计算整个表达式。这就是 短路。
         * 对于流而言，某些操作（例如 allMatch 、 anyMatch 、 noneMatch 、 findFirst 和 findAny ）
         * 不用处理整个流就能得到结果。只要找到一个元素，就可以有结果了。同样， limit 也是一个
         * 短路操作：它只需要创建一个给定大小的流，而用不着处理流中所有的元素。在碰到无限大小
         * 的流的时候，这种操作就有用了：它们可以把无限流变成有限流。
         */
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("至少有一个素菜");
        }

        if (menu.stream().allMatch(dish -> dish.getCalories() < 1000)) {
            System.out.println("所有菜的卡路里都小于1000");
        }

        if (menu.stream().noneMatch(dish -> dish.getCalories() > 1000)) {
            System.out.println("没有一个菜的卡路里大于1000");
        }
    }



    private static void test3() {
        /**
         * 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
         * 例如，给定[1, 2, 3, 4, 5]，应该返回[1, 4, 9, 16, 25]。
         */
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect = integers.stream()
                .map(integer -> integer * integer).collect(Collectors.toList());
        System.out.println(collect);

        /**
         * 给定两个数字列表，如何返回所有的数对呢？
         * 例如，给定列表[1, 2, 3]和列表[3, 4]，
         * 应该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。
         * 为简单起见，你可以用有两个元素的数组来代表数对
         */
        List<Integer> integers1 = Arrays.asList(1, 2, 3);
        List<Integer> integers2 = Arrays.asList(3,4);

        List<int[]> collect1 = integers1.stream()
                .flatMap(i -> integers2.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
//      foreach(collect1);

        /**
         * 如何扩展前一个例子，只返回总和能被3整除的数对呢？例如(2, 4)和(3, 3)是可以的。
         * 答案：你在前面看到了， filter 可以配合谓词使用来筛选流中的元素。因为在 flatMap
         * 操作后，你有了一个代表数对的 int[] 流，所以你只需要一个谓词来检查总和是否能被3整除
         * 就可以了
         */

        List<int[]> collect2 = integers1.stream()
                .flatMap(i -> integers2.stream().filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        foreach(collect2);
    }

    private static void foreach(List<int[]> collect1) {
        collect1.forEach(ints -> {
            for (int anInt : ints) {
                System.out.println(anInt);
            }
        });
    }

    /**
     * 流的扁平化
     * 给 定 单 词 列 表["Hello","World"] ，你想要返回列表 ["H","e","l", "o","W","r","d"] 。
     */
    private static void test2() {
        List<String> strings = Arrays.asList("Hello", "World");


        /**
         * Arrays.stream方法可以接收一个数组,返回一个流.
         * 使用 flatMap 方法的效果是，各个数组并不是分别映射成一个流，而是映射成流的内容。所
         * 有使用 map(Arrays::stream) 时生成的单个流都被合并起来，即扁平化为一个流。
         * 一言以蔽之， flatmap 方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接
         * 起来成为一个流。
         */
        List<String> collect = strings.stream().
                map(s -> s.split("")).
                flatMap(Arrays::stream).
                distinct().
                collect(Collectors.toList());
    }


    /**
     * 你将如何利用流来筛选第二道开始的前两个荤菜呢?
     */
    private static void test1() {
        List<Dish> collect = menu.stream().filter(dish -> dish.getType() == Dish.Type.MEAT)
                .skip(1).limit(2).collect(Collectors.toList());
        System.out.println(collect);
    }

}
