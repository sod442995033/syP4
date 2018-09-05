package top.dzygod.jdk8.practice.chaptersix;

import top.dzygod.jdk8.practice.chapterfive.Test;
import top.dzygod.jdk8.practice.chapterfour.Dish;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/28 13:14
 * @Description: 第六章, 用流收集数据
 */
public class Test1 {


    public enum CaloricLevel {DIET, NORMAL, FAT}


    /**
     * 在需要将流项目重组成集合时，一般会使用收集器（ Stream 方法 collect的参数）。
     * 再宽泛一点来说，但凡要把流中所有的项目合并成一个结果时就可以用。
     * 对流调用collect 方法将对流中的元素触发一个归约操作（由 Collector 来参数化）。
     * <p>
     * 预定义收集器(从 Collector类提供的工厂方法（例如 groupingBy ）创建的收集器)
     * 它们主要提供了三大功能：
     *  将流元素归约和汇总为一个值
     *  元素分组
     *  元素分区
     */
    public static void main(String[] args) {
//        count();
//        maxAndMin();
//        sumAndAvg();
//        join();
//        reduction();
//        group();
//        group2();
//        group3();
//        group4();
//        partition();
//        partitionPrimes();
//        note();
//        customizeCollector();
    }

    /**
     *  Collector 接口声明的方法,自定义收集器进行归约
     *  1. 建立新的结果容器： supplier 方法
     *  2. 将元素添加到结果容器： accumulator 方法
     *  3. 对结果容器应用最终转换： finisher 方法
     *  4. 合并两个结果容器： combiner 方法
     *  5. characteristics 会返回一个不可变的 Characteristics 集合，它定义了收集器的行为
     *     Characteristics 是一个包含三个项目的枚举
     */
    private static void customizeCollector() {
        //自定义
        List<String> collect = IntStream.range(1, 20).boxed()
                .map(integer -> integer.toString())
                .collect(new ToListColllector<String>());
        //API中提供的
        List<String> collect1 = IntStream.range(1, 20).boxed()
                .map(integer -> integer.toString())
                .collect(toList());
    }

    /**
     * Collectors 类的静态工厂方法
     */
    private static void note() {
        //把流中所有元素收集到一个list集合
        List<Integer> toList = IntStream.rangeClosed(1, 1000).boxed().collect(toList());
        //把流中所有元素收集到一个set集合
        Set<Integer> toSet = IntStream.rangeClosed(1, 1000).boxed().collect(toSet());
        //把流中的项目收集到给定供应源的集合
        ArrayList<Integer> toCollect = IntStream.range(1, 1000).boxed()
                .collect(toCollection(ArrayList::new));
        //计算流中的元素总数
        Long counting = IntStream.rangeClosed(1, 1000).boxed().collect(counting());
        //对流中的整数属性求和
        Integer sum = IntStream.rangeClosed(1, 1000).boxed()
                .collect(summingInt(Integer::intValue));
        //计算流中所有元素的平均数
        Double avg = IntStream.range(1, 20).boxed()
                .collect(averagingInt(Integer::intValue));
        //收集关于流中项目Integer属性的统计值,例如最大,最小,总和与平均值
        IntSummaryStatistics intSummaryStatistics = IntStream.range(1, 20).boxed()
                .collect(summarizingInt(Integer::intValue));
        //连接流中每个项目调用string方法生成的字符串
        String joining = Arrays.asList("1", "123").stream()
                .collect(joining(","));
        //一个包裹了流中按照指定比较器选出的最大元素的optional
        Optional<Integer> maxValue = IntStream.range(1, 20).boxed()
                .collect(maxBy(Integer::compareTo));
        //一个包裹了流中按照指定比较器选出的最小元素的optional
        Optional<Integer> minValue = IntStream.range(1, 20).boxed()
                .collect(minBy(Integer::compareTo));
        //从一个作为累加器的初始值开始,利用binaryOperator与流中的元素结合,将流归约为单个值
        Integer reducingSum = IntStream.range(1, 20).boxed()
                .collect(reducing(0, Integer::intValue, Integer::sum));
        //包裹另一个收集器,对其结果使用转换函数
        Integer andThen = IntStream.range(1, 20).boxed()
                .collect(collectingAndThen(toList(), List::size));
        //分组:根据项目的一个属性值对流中的项目作问组,并将属性值作为结果的键
        Map<Boolean, List<Integer>> groups = IntStream.range(1, 20).boxed()
                .collect(groupingBy(integer -> integer % 2 == 0));
        //分区:根据对流中的每个项目应用谓词的结果来对项目进行分区
        Map<Boolean, List<Integer>> partition = IntStream.range(1, 20).boxed()
                .collect(partitioningBy(integer -> integer % 2 == 0));
    }


    /**
     * 将数字按质数与非质数分区
     */
    public static void partitionPrimes(int nums) {
        Map<Boolean, List<Integer>> collect = IntStream.range(2, nums)
                .boxed().collect(partitioningBy(condition -> isPrime(condition)));
}

    /**
     * 判断参数是否为质数
     * 传入参数对2到除了自身的所有数取模,没有整除的数,说明入参为质数,
     * 如果是合数,一定是由一个大于质数开方数和一个小于质数开方的数组成的
     */
    private static boolean isPrime(Integer candidate) {
        return IntStream.range(2, (int) Math.sqrt(candidate))
                .noneMatch(i -> candidate % i == 0);
    }


    /**
     * 分区
     * 分区是分组的特殊情况：由一个谓词（返回一个布尔值的函数）作为分类函数，它称分区函数。
     */
    private static void partition() {
        //区分是否为素食
        Map<Boolean, List<Dish>> isVegetarian = Test.menu.stream()
                .collect(partitioningBy(dish -> dish.isVegetarian()));
        System.out.println(isVegetarian.get(true));

        //partitioningBy的重载,不是素食的dish中以type为键的map
        Map<Dish.Type, List<Dish>> map = Test.menu.stream().collect(
                partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType))).get(false);
        System.out.println(map);

        //找到素食和非素食中,热量最高的菜
        Map<Boolean, Dish> collect = Test.menu.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                collectingAndThen(maxBy(comparing(Dish::getCalories)), Optional::get)));
        System.out.println(collect);
    }


    /**
     * 与group联合使用的其他收集器的例子
     * 和 groupingBy 联合使用的一个收集器是 mapping 方法生成的
     * 这个方法接受两个参数：一个函数对流中的元素做变换，另一个则将变换的结果对象收集起来
     */
    private static void group4() {
        //对于每种类型的Dish,菜单中都有哪些CaloricLevel
        Map<Dish.Type, Set<CaloricLevel>> collect = Test.menu.stream()
                .collect(groupingBy(Dish::getType, mapping( dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                }, toSet()
        )));
        System.out.println(collect);

        //toCollection对类型进行控制
        Map<Dish.Type, HashSet<CaloricLevel>> collect1= Test.menu.stream()
                .collect(groupingBy(Dish::getType, mapping( dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        }, toCollection(HashSet::new)
                )));
        System.out.println(collect1);
    }


    /**
     * 按子组收集数据
     * 传递给第一个 groupingBy 的第二个收集器可以是任何类型
     *
     *  普通的单参数 groupingBy(f) （其中 f 是分类函数）
     * 实际上是 groupingBy(f,toList()) 的简便写法。
     */
    private static void group3() {
        Map<Dish.Type, Long> dishCount = Test.menu.stream().collect(
                groupingBy(Dish::getType, counting()));
        System.out.println(dishCount);

        Map<Dish.Type, Optional<Dish>> dishMaxCaloric = Test.menu.stream()
                .collect(groupingBy(Dish::getType,maxBy(comparing(Dish::getCalories))));
        System.out.println(dishMaxCaloric);

        //collectingAndThen这个工厂方法接受两个参数——要转换的收集器以及转换函数，并返回另一个收集器。
        Map<Dish.Type, Dish> collect1 = Test.menu.stream().collect(groupingBy(Dish::getType,
                collectingAndThen(maxBy(comparing(Dish::getCalories)), Optional::get)));
        System.out.println(collect1);
    }


    /**
     * 多级分组
     * 第二个参数中输入二级分类函数
     *{MEAT={DIET=[chicken], FAT=[pork], NORMAL=[beef]},
     * FISH={DIET=[prawns], NORMAL=[salmon]},
     * OTHER={DIET=[rice, season fruit], NORMAL=[french fries, pizza]}}
     *
     * 这种多级分组操作可以扩展至任意层级，n级分组就会得到一个代表n级树形结构的n级Map
     */
    private static void group2() {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> collect = Test.menu.stream()
                .collect(groupingBy(Dish::getType,
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                })
        ));
        System.out.println(collect);
    }

    /**
     * 分组
     * 给 groupingBy 方法传递了一个 Function （以方法引用的形式）
     * 它提取了流中每一道 Dish 的 Dish.Type
     * 我们把这个 Function 叫作分类函数，因为它用来把流中的元素分成不同的组
     *
     * 单参数 groupingBy(f) （其中 f 是分类函数）实际上是 groupingBy(f,toList()) 的简便写法。
     *
     *  分组操作的结果是一个 Map ，把分组函数返回的值作为映射的键，
     * 把流中所有具有这个分类值的项目的列表作为对应的映射值
     *
     */
    private static void group() {
        Map<Dish.Type, List<Dish>> collect = Test.menu.stream()
                    .collect(groupingBy(Dish::getType));


        //将菜单按照食物的卡路里进行分组
        Map<CaloricLevel, List<Dish>> Dish = Test.menu.stream().collect(groupingBy(dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                }
        ));
    }

    /**
     * 广义的归约汇总
     * 事实上，我们已经讨论的所有收集器，都是一个可以用 reducing 工厂方法定义的归约过程
     * 的特殊情况而已,Collectors.reducing 工厂方法是所有这些特殊情况的一般化
     *
     * 它需要三个参数。(初始值)(转换函数)(累积函数)
     *  第一个参数是归约操作的起始值，也是流中没有元素时的返回值，所以很显然对于数值
     * 和而言 0 是一个合适的值。
     *  第二个参数就是你在6.2.2节中使用的函数，将菜肴转换成一个表示其所含热量的 int 。
     *  第三个参数是一个 BinaryOperator ，将两个项目累积成一个同类型的值。这里它就是
     * 对两个 int 求和
     */
    private static void reduction() {

        /**
         * 根据情况选择最佳的解决方案
         * 函数式编程通常提供了多种方法来执行同一个操作。
         * 收集器在某种程度上比 Stream 接口上直接提供的方法用起来更复杂，
         * 但好处在于它们能提供更高水平的抽象和概括，也更容易重用和自定义
         */

        //三参数
        Integer collect = Test.menu.stream()
                .collect(reducing(0, Dish::getCalories, (i, j) -> i + j));

        //要是把单参数 reducing 收集器传递给空流的 collect 方法，收集器就没有起点,所以返回一个optional
        Optional<Dish> collect2 = Test.menu.stream()
                .collect(reducing(
                        (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)
                );


        /**
         *  Stream 接口的 collect和 reduce 方法有何不同
         *  reduce 方法旨在把两个值结合起来生成一个新值，它是一个不可变的归约。
         *  collect 方法的设计就是要改变容器，从而累积要输出的结果
         */
        Integer collect1 = Test.menu.stream()
                .collect(reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(collect1);
    }

    /**
     * 连接字符串
     * joining 工厂方法返回的收集器会把对流中每一个对象应用 toString 方法得到的所有字符
     * 串连接成一个字符串
     * joining 在内部使用了 StringBuilder 来把生成的字符串逐个追加起来
     */
    private static void join() {
        String collect = Test.menu.stream()
                .map(Dish::getName).collect(joining(","));
        System.out.println(collect);
    }


    /**
     * 汇总
     * 返回单个值的归约操作是对流中对象的一个数值字段求和
     * 或者你可能想要求平均数。这种操作被称为汇总操作。
     * 求出菜单列表的总热量
     * <p>
     * 练习:使用收集器来给流中的元素计数
     * 在遍历流时，会把每一道菜都映射为其热量，然后把这个数字累加到一个累加器（这里的初始值 0 ）。
     * Collectors.summingLong 和 Collectors.summingDouble 方法的作用完全一样，
     * 可以用于求和字段为 long 或 double 的情况。
     */
    private static void sumAndAvg() {
        Integer sum = Test.menu.stream()
                .collect(summingInt(Dish::getCalories));
        System.out.println(sum);

        Double avg = Test.menu.stream()
                .collect(averagingInt(Dish::getCalories));
        System.out.println(avg);

        /**
         *  summarizingInt 工厂方法返回的收集器
         *  通过一次 summarizing 操作你可以就数出菜单中元素的个数，
         *  并得到菜肴热量总和、平均值、最大值和最小值
         */
        IntSummaryStatistics collect = Test.menu.stream()
                .collect(summarizingInt(Dish::getCalories));
        System.out.println(collect);


    }

    /**
     * 返回菜单里菜的总数,结果相同
     */
    private static void count() {
        Long collect = Test.menu.stream().collect(counting());
        long count = Test.menu.stream().count();
    }

    /**
     * 查找流中指定条件的最大值和最小值
     */
    private static void maxAndMin() {
        Comparator<Dish> comparator = comparingInt(Dish::getCalories);
        Optional<Dish> maxDish = Test.menu.stream().collect(maxBy(comparator));
        Optional<Dish> minDish = Test.menu.stream().collect(minBy(comparator));
        System.out.println(maxDish);
        System.out.println(minDish);
    }


}
