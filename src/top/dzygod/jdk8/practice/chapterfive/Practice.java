package top.dzygod.jdk8.practice.chapterfive;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/27 16:30
 * @Description: 交易员的练习
 */
public class Practice {


    private static final Trader raoul = new Trader("Raoul", "Cambridge");
    private static final Trader mario = new Trader("Mario","Milan");
    private static final Trader alan = new Trader("Alan","Cambridge");
    private static final Trader brian = new Trader("Brian","Cambridge");

    private static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    /**
     * (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
     * (2) 交易员都在哪些不同的城市工作过？
     * (3) 查找所有来自于剑桥的交易员，并按姓名排序。
     * (4) 返回所有交易员的姓名字符串，按字母顺序排序。
     * (5) 有没有交易员是在米兰工作的？
     * (6) 打印生活在剑桥的交易员的所有交易额。
     * (7) 所有交易中，最高的交易额是多少？
     * (8) 找到交易额最小的交易。
     */
    public static void main(String[] args){
//        exercise1();
//        exercise2();
//        exercise3();
//        exercise4();
//        exercise5();
//        exercise6();
//        exercise7();
//        exercise8();
    }

    /**
     * (8) 找到交易额最小的交易。
     */
    private static void exercise8() {
        Optional<Transaction> reduce = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);

        System.out.println(reduce.get());
    }

    /**
     * (7) 所有交易中，最高的交易额是多少？
     */
    private static void exercise7() {
        Optional<Integer> reduce = transactions.stream()
                .map(t -> t.getValue()).reduce(Integer::max);
        System.out.println(reduce.get());
    }

    /**
     * (6) 打印生活在剑桥的交易员的所有交易额。
     */
    private static void exercise6() {
        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(transaction -> transaction.getValue())
                .sorted()
                .forEach(System.out::println);
    }


    /**
     * (5) 有没有交易员是在米兰工作的？
     */
    private static void exercise5() {
        /*Optional<Trader> milan = transactions.stream().map(t -> t.getTrader())
                .filter(trader -> trader.getCity().equals("Milan"))
                .findAny();*/

        boolean milan = transactions.stream().
                anyMatch(transaction -> "Milan".equals(transaction.getTrader().getCity()));

        System.out.println(milan);
    }


    /**
     * (4) 返回所有交易员的姓名字符串，按字母顺序排序。
     */
    private static void exercise4() {
        Optional<String> reduce = transactions.stream().map(t -> t.getTrader().getName())
                .sorted()
                .distinct()
                .reduce((s1, s2) -> s1 + " " +s2);

        System.out.println(reduce.get());
    }


    /**
     * (3) 查找所有来自于剑桥的交易员，并按姓名排序。
     */
    private static void exercise3() {
        List<Trader> cambridge = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .distinct()
                .collect(Collectors.toList());
        cambridge.forEach(System.out::println);
    }


    /**
     * (2) 交易员都在哪些不同的城市工作过？
     */
    private static void exercise2() {
        List<String> collect = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    /**
     * (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
     */
    private static void exercise1() {
        List<Transaction> collect = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }


}

