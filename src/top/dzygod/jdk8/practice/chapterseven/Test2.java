package top.dzygod.jdk8.practice.chapterseven;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/7 8:13
 * @Description 实现自己的 Spliterator
 */
public class Test2 {


    public static final String STRING = "张三 他 叫 张三";

    /**
     * spliterator(可分迭代器)
     * 和 Iterator 一样， Spliterator 也用于遍历数据源中的元素，但它是为了并行执行而设计的。
     * Java 8已经为集合框架中包含的所有数据结构提供了一个默认的 Spliterator 实现。
     * 集合实现了 Spliterator 接口，接口提供了一个 spliterator 方法。
     *
     * public interface Spliterator<T> {
     *      boolean tryAdvance(Consumer<? super T> action);
     *      Spliterator<T> trySplit();
     *      long estimateSize();
     *      int characteristics();
     * }
     *  tryAdvance 方法的行为类似于普通的Iterator ，因为它会按顺序一个一个使用 Spliterator 中的元素，
     *  并且如果还有其他元素要遍历就返回 true 。
     *  但 trySplit 是专为 Spliterator 接口设计的，因为它可以把一些元素划出去分
     * 给第二个 Spliterator （由该方法返回），让它们两个并行处理。 Spliterator 还可通过
     * estimateSize 方法估计还剩下多少元素要遍历，因为即使不那么确切，能快速算出来是一个值
     * 也有助于让拆分均匀一点
     *
     *  Spliterator 还有最后一个值得注意的功能，就是可以在第一次遍历、
     *  第一次拆分或第一次查询估计大小时绑定元素的数据源，而不是在创建时就绑定。
     *  这种情况下，它称为延迟绑定（late-binding）的 Spliterator 。
     */
    public static void main(String[] args) {
//        countTest();
//        note();
        Stream<Character> stream = IntStream
                .range(0, STRING.length()).mapToObj(STRING::charAt);

        //并行流会出现错误,因为原始的 String 在任意位置拆分，所以有时一个词会被分为两个词，然后数了两次
        System.out.println(countWords(stream));
    }

    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }

    private static void note() {
        /**
         *  内部迭代让你可以并行处理一个流，而无需在代码中显式使用和协调不同的线程。
         *  虽然并行处理一个流很容易，却不能保证程序在所有情况下都运行得更快。并行软件的
         * 行为和性能有时是违反直觉的，因此一定要测量，确保你并没有把程序拖得更慢。
         *  像并行流那样对一个数据集并行执行操作可以提升性能，特别是要处理的元素数量庞大，
         * 或处理单个元素特别耗时的时候。
         *  从性能角度来看，使用正确的数据结构，如尽可能利用原始流而不是一般化的流，几乎
         * 总是比尝试并行化某些操作更为重要。
         *  分支/合并框架让你得以用递归方式将可以并行的任务拆分成更小的任务，在不同的线程
         * 上执行，然后将各个子任务的结果合并起来生成整体结果。
         *  Spliterator 定义了并行流如何拆分它要遍历的数据。
         */}

    /**
     * 单词数量统计调用
     */
    private static void countTest() {
        String s = STRING;
        System.out.println(countWordsIterative(s));
    }


    /**
     * 迭代式单词数统计
     * @param words
     */
    private static int countWordsIterative(String words) {
        int count = 0;
        boolean nextChar = true;
        for (char c : words.toCharArray()) {
            if (Character.isWhitespace(c)) {
                nextChar = true;
            } else {
                if (nextChar == true) { count++; }
                nextChar = false;
            }
        }
        return count;
    }



}
