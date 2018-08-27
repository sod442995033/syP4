package top.dzygod.jdk8.practice.chapterThree;

import sun.font.FontRunIterator;
import top.dzygod.jdk8.行为参数化.Apple;

import java.io.FilenameFilter;
import java.util.*;
import java.util.function.*;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/18 20:08
 * @Description: *
 */
@SuppressWarnings("ALL")
public class Test2 {




    public static void main(String[] args){
//        localVar();
//        constructorTest();
        test2();
    }


    /**
     * 复合lambda表达式的有用方法
     */
    private static void test2() {
        List<Apple> apples = Arrays.asList(new Apple("红色",1),
                new Apple("绿色",2), new Apple("紫色",3));


        /**
         * 比较器复合
         * thenComparing()接受一个函数作为参数,
         * 如果两个对象用第一个 Comparator 比较之后是一样的，就提供第二个Comparator
         */
        apples.sort(Comparator.comparing(Apple::getWeight).reversed()
                .thenComparing(Apple::getCountry));


        /**
         * 谓词复合
         * 谓词接口包括三个方法:negate,and和or,让你可以重用已有的 Predicate 来创建更复杂的谓词
         * 与或非
         */
        Predicate<Apple> redApple = (apple -> apple.getColor().equals("红色"));

        Predicate<Apple> notRedApple = redApple.negate();

        Predicate<Apple> redAndWeightApple = notRedApple.and(apple -> apple.getWeight() > 1);
        Predicate<Apple> redAndWeightAppleAndGreen = redApple.and(apple ->
                apple.getWeight() == 2).or(apple -> apple.getColor().equals("绿色"));


        for (Apple apple : apples) {
            System.out.print(apple);
            System.out.print(redApple.test(apple)+",");
            System.out.print(redAndWeightApple.test(apple)+",");
            System.out.println(redAndWeightAppleAndGreen.test(apple)+",");
        }


        /**
         * 函数复合
         * Function 接口为此配了andThen和compose两个默认方法,它们都会返回 Function 的一个实例
         * 输出结果是4与3
         *
         * andThen 方法会返回一个函数，它先对输入应用一个给定函数，再对输出应用另一个函数。
         * f(g(x))
         *
         * compose 方法先把给定的函数用作 compose 的参数里面给的那个函数，然后再把函数本身用于结果。
         * g(f(x))
         */
        Function<Integer,Integer> f1 = (x) -> x + 1;
        Function<Integer,Integer> f2 = (x) -> x * 2;
        Function<Integer, Integer> function = f1.andThen(f2);
        Function<Integer, Integer> compose = f1.compose(f2);
        System.out.println(function.apply(1));
        System.out.println(compose.apply(1));

    }

    /**
     * @Author: dzy
     * @Date: 2018/8/24 11:15
     * @param
     * @Description: 排序苹果练习这一章学到的,函数式接口,lambda表达式,方法引用
     */
    private static void test() {
        List<Apple> apples = Arrays.asList(new Apple(1), new Apple(2), new Apple(3));


        //lambda
        apples.sort((a,b) -> a.getWeight().compareTo(b.getWeight()));
        //comparing它可以接受一个 Function 来提取 Comparable 键值，并生成一个 Comparator 对象
        apples.sort(Comparator.comparing((Apple a) -> a.getWeight()));

        //方法引用,最终解决方案
        apples.sort(Comparator.comparing(Apple::getWeight));

    }

    /**
     * 构造函数的方法引用
     * 对于一个现有构造函数,你可以利用它的名称和关键字new 来创建它的一个引用
     */
    private static void constructorTest() {

        //无参构造,返回值类型是Apple,
        //等价于Supplier<Apple> c1 = () -> new Apple();
        Supplier<Apple> s = Apple::new;
        //get()方法产生一个新的Apple,等价于Apple a1 = c1.get();
        Apple apple = s.get();

        //单个参数的构造,
        //等价于Function<Integer,Apple> fun = (weight) -> new Apple(weight);
        Function<Integer,Apple> fun  = Apple::new;
        Apple apply = fun.apply(100);

        //两个参数的构造,
        //等价于BiFunction<String,Integer,Apple> bi = (str,inte) -> new Apple(str,inte);
        BiFunction<String,Integer,Apple> bi = Apple::new;
        Apple apple1 = bi.apply("彩色的", 100000);
        System.out.println(apple1);

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Apple> map = map(integers, Apple::new);
//        List<Apple> map = map(integers, (Integer inte) -> new Apple(inte));
//        System.out.println(map);


    }

    private static <T,R> List<R> map(List<T> list,Function<T,R> fun) {

        ArrayList<R> ts = new ArrayList();

        for (T t : list) {
            R apply = fun.apply(t);
            ts.add(apply);
        }
        return ts;
    }


    /**
     * 方法引用
     * 1.指向静态方法的方法引用（例如 Integer 的 parseInt 方法，写作 Integer::parseInt ）。
     * 2.指向任意类型实例方法的方法引用（例如String的length方法，写作String::length ）。
     * 3.指向现有对象的实例方法的方法引用
     * (假设你有一个局部变量expensiveTransaction用于存放Transacation类型的对象,
     * 它支持实例方法getValue,那么你就可以写expensiveTransaction::getValue)
     */
    private static void methQuote() {

        BiPredicate<List<String>,String> filter = (list, element) -> list.contains(element);

        //编译器根据上下文,检查是否可以编译通过
        BiPredicate<List<String>, String> filter1 = List::contains;

    }

    private static void localVar() {
        /**
         * lambda使用局部变量,被称作捕获Lambda
         * Lambda可以没有限制地捕获（也就是在其主体中引用）实例变量和静态变量
         * 但局部变量必须显式声明为 final,或事实上是 final(),
         * (没有final修饰的局部变量,不能第二次赋值,下边的变量s如果被二次赋值,会编译出错)
         * 局部变量使用final修饰的原因:
         * 局部变量加载到栈内存中,方法执行结束会弹栈.
         * 如果捕获lambda在另一个线程中,那么访问一个已经不存在的变量就会报错.
         * final修饰之后进入堆内存常量池中
         */
        String s = "";
        Consumer<String> c = s1 -> s.toString();
    }

}
