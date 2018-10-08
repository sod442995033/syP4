package top.dzygod.jdk8.practice.chaptereleven;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: dingziyuan
 * @Date: 2018/10/4 15:47
 * @Description: 第十一章:
 * CompletableFuture ：组合式异步编程
 * 本章内容
 *  创建异步计算，并获取计算结果
 *  使用非阻塞操作提升吞吐量
 *  设计和实现异步API
 *  如何以异步的方式使用同步的API
 *  如何对两个或多个异步操作进行流水线和合并操作
 *  如何处理异步操作的完成状态
 */
public class Index {

    private static List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("MakeYouLoveShopping"),
            new Shop("BuyEverything"),
            new Shop("ComeHere"),
            new Shop("NeverStop"),
            new Shop("YouWontStop"),
            new Shop("ThreeHands"),
            new Shop("FourHands")
    );

    /**
     * 定制执行器
     * 自制线程池,第一个参数指定线程池最大线程数
     */
    private static ExecutorService executor = Executors
            .newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setDaemon(true);
                    return thread;
                }
            });

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
        /**
         * 对多个异步任务进行流水线操作
         * 假设所有的商店都同意使用一个集中式的折扣服务
         * 折扣服务提供了五个不同的折扣代码，每个折扣代码对应不同的折扣率。
         */

        System.out.println(new Shop("aaa").getPricesTwo("shirt"));

    }


    /**
     * 并行到达CPU极限,使用执行器优化
     */
    private static void test6() {
        long start = System.nanoTime() / 100_0000;
        System.out.println(findPricesThree("shirt"));
        System.out.println(System.nanoTime() / 100_0000 - start + " ms");
    }


    /**
     * 定制执行器:
     * 处理任务线程数大于cpu最大线程数,就需要配置自定义的执行器来进行优化了
     * Runtime.getRuntime().availableProcessors();
     * 这个方法获得当前cpu支持的最大线程数
     */
    private static List<String> findPricesThree(String product) {

        List<CompletableFuture<String>> collect = shops.stream()
                .map(shop -> CompletableFuture
                        .supplyAsync(() -> String.format("%s价格是:%s", shop.getShopName()
                                , shop.getPrice(product)), executor))
                .collect(Collectors.toList());

        return collect.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }






    /**
     * 使用工厂类直接完成,
     * 耗费时间2082 ms(4个商店)
     */
    private static void test5() {
        long start = System.nanoTime() / 100_0000;
        System.out.println(findPricesTwo("shirt"));
        System.out.println(System.nanoTime() / 100_0000 - start + " ms");
    }


    private static List<String> findPricesTwo(String product) {
        List<CompletableFuture<String>> pricesFuture = shops.stream()
                .map(shop -> CompletableFuture
                        .supplyAsync(() -> String.format(shop.getShopName()
                                + " 的价格:" + shop.getPrice(product))))
                .collect(Collectors.toList());

        return pricesFuture.stream()
                .map(CompletableFuture::join).collect(Collectors.toList());
    }


    /**
     * 使用并行流对请求进行并行操作
     * 串行速度 4087 ms
     * 并行速度 1102 ms
     */
    private static void test4() {
        long start = System.nanoTime() / 100_0000;
        System.out.println(parallelFindPrices("shirt"));
        System.out.println(System.nanoTime() / 100_0000 - start + " ms");
    }


    public static List<String> parallelFindPrices(String product) {

        return shops.parallelStream()
                .map(s -> String.format("%s 价格是  %.2f", s.getShopName(),
                        s.getPrice(product))).collect(Collectors.toList());
    }

    ;


    private static void test3() {
        long start = System.nanoTime() / 100_0000;
        System.out.println(findPrices("shirt"));
        System.out.println(System.nanoTime() / 100_0000 - start + " ms");
    }

    /**
     * 返回一个字符串列表，
     * 这个字符串列表中包括商店的名称、该商店中指定商品的价格：
     *
     * @param product
     * @return
     */
    public static List<String> findPrices(String product) {

        return shops.stream()
                .map(s -> String.format("%s 价格是  %.2f", s.getShopName(),
                        s.getPrice(product))).collect(Collectors.toList());
    }

    ;


    private static void test2() {
        /**
         * 使用completableFuture构建异步应用
         * 实现异步API
         */
        Shop shop = new Shop("testShop");
        long startTime = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("cyclingPants");
        System.out.println(System.nanoTime() - startTime / 100_0000 + " msecs");

        //其他的业务
        for (int i = 0; i < 1000; i++) {

        }
        try {
            //获取异步线程返回数据,指定最长等待时间
            Double realPrice = futurePrice.get(5, TimeUnit.SECONDS);
            System.out.println("price is: " + realPrice);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long priceTime = System.nanoTime() - startTime / 100_0000;
        System.out.println("花费了:" + priceTime + " msecs");
    }

    private static void test1() {
        /**
         * Future接口
         * Future 接口在Java 5中被引入，设计初衷是对将来某个时刻会发生的结果进行建模。
         * 它建模了一种异步计算，返回一个执行运算结果的引用，当运算结束后，这个引用被返回给调用方。
         * 在Future 中触发那些潜在耗时的操作把调用线程解放出来，让它能继续执行其他有价值的工作，
         * 不再需要呆呆等待耗时的操作完成。
         *
         * 使用Future会出现的一些问题,java8中对下列的需求都做了实现解决
         *  将两个异步计算合并为一个——这两个异步计算之间相互独立，同时第二个又依赖于第
         * 一个的结果。
         *  等待 Future 集合中的所有任务都完成。
         *  仅等待 Future 集合中最快结束的任务完成（有可能因为它们试图通过不同的方式计算同
         * 一个值），并返回它的结果。
         *  通过编程方式完成一个 Future 任务的执行（即以手工设定异步操作结果的方式）。
         *  应对 Future 的完成事件（即当 Future 的完成事件发生时会收到通知，并能使用 Future
         * 计算的结果进行下一步的操作，不只是简单地阻塞等待操作的结果）。
         */}
}
