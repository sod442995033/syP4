package top.dzygod.jdk8.practice.chaptereight;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/26 14:41
 * @Description: 调试
 */
public class Test2 {
    public static void main(String[] args){
//        test1();
//        peekTest();

    }

    /**
     * 使用peek查看流水线操作中每一步的操作结果
     *  调用foreach无法了解流中数据细节
     *         list.stream().map(x -> x+17).filter(x -> x%2==0).forEach(System.out::println);
     */
    private static void peekTest() {
        List<Integer> list = Arrays.asList(2, 3, 4, 5);


        List<Integer> collect =
                list.stream().peek(x-> System.out.println("beforePeek:"+x))
                .map(x -> x + 17).peek(x-> System.out.println("afterPeek:"+x))
                .filter(x -> x % 2 == 0).peek(x-> System.out.println("endPeek:"+x))
                .collect(Collectors.toList());
        System.out.println(collect);
    }


    /**
     * lambda表达式和栈跟踪
     * 下边的代码会产生异常错误,因为集合中第一个参数为null
     * 程序实际上是在处理一个空引用
     *
     * 程序的每次方法调用都会产生相应的调用信息，
     * 包括程序中方法调用的位置、该方法调用使用的参数、被调用方法的本地变量。
     * 这些信息被保存在栈帧上。
     * 程序失败时，你会得到它的栈跟踪，通过一个又一个栈帧，你可以了解程序失败时的概略信息
     */
    private static void test1() {


//        List<String> strings = Arrays.asList(null, new String());
        //lambda没有名称,lambda$main$0是编译器为其生成的名字
//        strings.stream().map(e -> new String(e).toUpperCase()).forEach(System.out::println);
        //即使换成方法引用也会产生难以分析的栈跟踪
//        strings.stream().map(String::toUpperCase).forEach(System.out::println);
        //在同一个类中声明的方法,它的名称是可以在栈跟踪中显示的
//        List<Integer> intList = Arrays.asList(1, 2, 3);
//        intList.stream().map(Test2::test).forEach(System.out::println);
    }

    public static int test(int num) {
        return num / 0;
    }
}
