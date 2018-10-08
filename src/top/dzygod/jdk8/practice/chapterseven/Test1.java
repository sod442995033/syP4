package top.dzygod.jdk8.practice.chapterseven;

import java.util.Collection;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/5 11:13
 * @Description:
 * 第七章,并行数据的处理与性能:
 *  用并行流并行处理数据
 *  并行流的性能分析
 *  分支/合并框架
 *  使用 Spliterator 分割流
 */
public class Test1 {
    public static void main(String[] args) {
//        parallel();`
//        sequential();
//        performanceTesting();
//        howToUseParallel();
//        branchMerge();
//        steal();



    }

    private static void steal() {
        /**
         * 工作窃取算法
         * 理想情况下，划分并行任务时，
         * 应该让每个任务都用完全相同的时间完成，让所有的CPU内核都同样繁忙。不幸的是，实际中，每
         * 个子任务所花的时间可能天差地别，要么是因为划分策略效率低，要么是有不可预知的原因，比如
         * 磁盘访问慢，或是需要和外部服务协调执行。
         *分支/合并框架工程用一种称为工作窃取（work stealing）的技术来解决这个问题
            工作窃取:使用双向链表解决这个问题,
                线程池中每一个线程都为分配给他的线程生成一个双向链表,
                单一线程任务完成,可以从其他工作线程尾部偷取任务,
                分支/合并框架分成很多小任务也是因为小任务平衡负载更加容易
         */}

    private static void branchMerge() {
        /**
         * 分支/合并框架
         * 分支/合并框架的目的是以递归方式将可以并行的任务拆分成更小的任务，
         * 然后将每个子任务的结果合并起来生成整体结果。
         * 它是 ExecutorService 接口的一个实现，它把子任务分配给
         * 线程池（称为 ForkJoinPool ）中的工作线程。首先来看看如何定义任务和子任务。
         *
         * 要把任务提交到这个池，必须创建 RecursiveTask<R> 的一个子类，
         * 其中 R 是并行化任务（以及所有子任务）产生的结果类型，
         * 或者如果任务不返回结果，则是 RecursiveAction 类型（当然它可能会更新其他非局部机构）。
         * 要定义 RecursiveTask， 只需实现它唯一的抽象方法compute.
         *
         * 这个方法的实现类似于下面的伪代码：
         *  if (任务足够小或不可分) {
         *       顺序计算该任务
         *  } else {
         *       将任务分成两个子任务
         *  递归调用本方法，拆分每个子任务，等待所有子任务完成
         *      合并每个子任务的结果
         *  }
         */}

    private static void howToUseParallel() {
        /**
         * 何时使用并行流
         *   如果有疑问，测量。
         *   留意装箱
         *   有些操作本身在并行流上的性能就比顺序流差。特别是 limit 和 findFirst 等依赖于元
         * 素顺序的操作，它们在并行流上执行的代价非常大。
         *   还要考虑流的操作流水线的总计算成本。
         *   对于较小的数据量，选择并行流几乎从来都不是一个好的决定。
         *   要考虑流背后的数据结构是否易于分解。
         *   流自身的特点，以及流水线中的中间操作修改流的方式，都可能会改变分解过程的性能。
         *   还要考虑终端操作中合并步骤的代价是大是小（例如 Collector 中的 combiner 方法）。
         */}

    /**
     * 测量流的性能
     */
    private static void performanceTesting() {
        System.out.println("Sequential sum done in:" +
                measureSumPerf(Test1::partitionPrimes, 100_0000) + " msecs");
        System.out.println("Parallel range sum done in:" +
                measureSumPerf(Test1::parallelRangedSum, 100_00000L) + " msecs");
    }


    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }



    public static Integer partitionPrimes(Integer n) {

        return IntStream.rangeClosed(0, n).boxed()
                .collect(Collectors.summingInt(Integer::intValue));

    }

    /**
     * 指定长度的求和
     * @param num
     */
    public static Long sequentialSum(Long num) {
        long reduce = LongStream.range(0, num)
                .reduce(0, (left, right) -> left + right);
        return reduce;
    }

    /**
     * 测量类
     * @param function
     * @param n
     * @return
     */
    private static<T> Long measureSumPerf(Function<T, T> function, T n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            function.apply(n);
            long duration = (System.nanoTime() - start) / 100_0000;
            if (duration < fastest){fastest = duration;}
        }
            return fastest;
    }


    /**
     * 重载测量类
     * @param supplier
     * @param <T>
     * @return
     */
    private static<T> Long measureSumPerf(Supplier<T> supplier) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            supplier.get();
            long duration = (System.nanoTime() - start) / 100_0000;
            if (duration < fastest){fastest = duration;}
        }
            return fastest;
    }




    /**
     * 将并行流变为顺序流,需要使用sequential()方法
     * 最后一次 parallel 或 sequential 调用会影响整个流水线
     * 比如下边的代码中,整个流水线其实是顺序执行的
     */
    private static void sequential() {
        Long reduce = Stream.iterate(1L, i -> i + 1)
                .limit(20)
                .parallel().limit(10)
                .sequential().reduce(0L, Long::sum);
    }

    /**
     *  将顺序流变为并行流只需要调用parallel()方法,之后的所有操作都会并行处理
     *  Stream 在内部分成了几块。因此可以对不同的块独立并行进行归纳操作
     *   生成线程数取决于你中央处理器内核的个数,际上返回的是可用内核的数量，包括超线程生成的虚拟内核。
     *   这个值是由Runtime.getRuntime().availableProcessors();得到的
     *   可以通过设置系统属性改变并行线程池的大小
     *   System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","12");
     */
    private static void parallel() {
        Long sum = Stream.iterate(1L, i -> i + 1)
                .limit(20).parallel().reduce(0L, Long::sum);


    }
}

