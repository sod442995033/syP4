package top.dzygod.jdk8.practice.chaptersix;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/4 9:40
 * @Description:
 * 自定义收集器:
 * 1. 建立新的结果容器： supplier 方法
 * 2. 将元素添加到结果容器： accumulator 方法
 * 3. 对结果容器应用最终转换： finisher 方法
 * 4. 合并两个结果容器： combiner 方法
 * 5. characteristics 会返回一个不可变的 Characteristics 集合，它定义了收集器的行为
 *    Characteristics 是一个包含三个项目的枚举
 *       UNORDERED ——归约结果不受流中项目的遍历和累积顺序的影响。
 *       CONCURRENT —— accumulator 函数可以从多个线程同时调用，且该收集器可以并行归
 *          约流。如果收集器没有标为 UNORDERED ，那它仅在用于无序数据源时才可以并行归约。
 *       IDENTITY_FINISH ——这表明完成器方法返回的函数是一个恒等函数，可以跳过。这种
 *         情况下，累加器对象将会直接用作归约过程的最终结果。这也意味着，将累加器 A 不加检
 *         查地转换为结果 R 是安全的。
 */
public class ToListColllector <T> implements Collector<T, List<T>,List<T>> {
    /**
     * 创建集合操作的起点
     * @return
     */
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    /**
     * 累积遍历过的项目,原位修改累加器
     * @return
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List<T>::add;
    }

    /**
     * 恒等函数
     * @return
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }


    /**
     * 修改第一个累加器,将其与第二个累加器的元素结合
     * @return
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }


    /**
     * 为收集器添加IDENTITY_FINISH,CONCURRENT标志
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                Characteristics.IDENTITY_FINISH,Characteristics.CONCURRENT
        ));
    }

}
