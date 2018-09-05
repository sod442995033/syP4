package top.dzygod.jdk8.practice.chaptersix;


import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/4 16:09
 * @Descriptio n: 自定义收集器,分组解决是否为质数的问题 1. 第一步：定义 Collector 类的签名
 * @generic: Collector:流中元素的类型,累加器类型,collect操作的结果类型
 * */
public class PrimeNumbersCollector implements Collector<Integer,
        Map<Boolean, List<Integer>>,
        Map<Boolean, List<Integer>>> {


    /**
     *  2. 第二步：实现归约过程
     * @return
     */
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
         return () -> new HashMap<Boolean,List<Integer>>(){{
            put(true,new ArrayList<Integer>());
            put(false,new ArrayList<Integer>());
        }};
    }

    /**
     * 2. 第二步：实现归约过程
     * @return
     */
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean,List<Integer>> acc, Integer candidate) ->
                acc.get(isPrime(acc.get(true),candidate))
                        .add(candidate);
    }

    /**
     * 第三步：让收集器并行工作（如果可能
     * @return
     */
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return ((Map<Boolean,List<Integer>> map1, Map<Boolean,List<Integer>> map2) ->{
            map1.put(true, map2.get(true));
            map1.put(false, map2.get(false));
            return map1;
        });
    }

    /**
     * 第四步： finisher 方法和收集器的 characteristics 方法
     * @return
     */
    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }


    private static boolean isPrime(List<Integer> primes,int candidate) {
        int candidateRoot = (int) Math.sqrt((double)candidate);
        return talkWhile(primes, i -> (int) i <= candidateRoot)
                .stream().noneMatch(p -> candidate % p == 0);
    }


    /**
     * 规则
     * @param list
     * @param predicate
     * @param <T>
     * @return
     */
    private static<T> List<T> talkWhile(List<T> list, Predicate predicate) {
        int i = 0;
        for (T t : list) {
            if (!predicate.test(t)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

}
