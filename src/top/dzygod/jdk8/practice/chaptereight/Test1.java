package top.dzygod.jdk8.practice.chaptereight;

import sun.rmi.runtime.Log;
import top.dzygod.jdk8.practice.chapterfive.Test;
import top.dzygod.jdk8.practice.chapterfour.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/7 11:22
 * @Description: 第八章, 重构, 测试和调试
 */
public class Test1 {

    public enum CaloricLevel {DIET, NORMAL, FAT}

    public static void main(String[] args) {
//        test1();
//        transform();
//        dataSwitching();
//        flexible();

        /**r
         * 使用 Lambda 重构面向对象的设计模式
         * 使用Lambda表达式后，很多现存的略显臃肿的面向对象设计模式能够用更精简的方式实现了
         * 针对五个设计模式展开讨论
         */

        /**
         * 1.策略模式
         * 策略模式代表了解决一类算法的通用解决方案，你可以在运行时选择使用哪种方案
         *  策略模式包含三部分内容
         *       一个代表某个算法的接口（它是策略模式的接口）。
         *       一个或多个该接口的具体实现，它们代表了算法的多种实现（比如，实体类 Concrete-
         *       StrategyA 或者 ConcreteStrategyB ）。
         *       一个或多个使用策略对象的客户。
         */


    }

    private static void flexible() {
        /**
         * 增加代码的灵活性
         * 1.采用函数式接口
         * 在什么情况下使用函数接口:有条件的延迟执行和环绕执行,可以依照这两种情况重构代码
         * 2.有条件的延迟执行
         * 当控制语句混杂在业务逻辑代码中
         * 3.环绕执行
         * 如果你发现虽然你的业务代码千差万别，但是它们拥有同样的准备和清理阶段，
         * 这时，你完全可以将这部分代码用Lambda实现。
         */
        Logger logger = Logger.getLogger("demo");
        if (logger.isLoggable(Level.FINER)) {
            logger.finer("问题:"+ logger.toString());
        }
        /**
         * 这种方式更好的原因是你不再需要在代码中插入那些条件判断，与此同时日志器的状态也不再被暴露出去
         * 问题是日志消息的输出与否每次都需要判断，即使你已经传递了参数，不开启日志。
         */
        logger.log(Level.FINER,"问题:");

        /**
         * log的重载版本
         * 如果日志器的级别设置恰当， log 方法会在内部执行作为参数传递进来的Lambda表达式。
         *
         * 如果你发现你需要频繁地从客户端代码去查询一个对象的状态（比如前文例子中的日志器的状态），
         * 只是为了传递参数、调用该对象的一个方法（比如输出一条日志），
         * 那么可以考虑实现一个新的方法，
         * 以Lambda或者方法表达式作为参数，
         * 新方法在检查完该对象的状态之后才调用原来的方法。
         * 你的代码会因此而变得更易读（结构更清晰），
         * 封装性更好（对象的状态也不会暴露给客户端代码了）。
         */
        logger.log(Level.FINER,() -> "问题:");
    }


    /**
     *  从命令式的数据处理切换到Stream
     *  我们建议你将所有使用迭代器这种数据处理模式处理集合的代码都转换成Stream API的方式
     *
     *  Stream API能更清晰地表达数据处理管道的意图
     *  通过短路和延迟载入以及利用第7章介绍的现代计算机的多核架构，我们可以对Stream进行优化
     */
    private static void dataSwitching() {
        //这样的代码结构迫使程序员必须彻底搞清楚程序的每个细节才能理解代码的功能
        List<String> dishNames = new ArrayList<>();
        for(Dish dish: Test.menu){
            if(dish.getCalories() > 300){
                dishNames.add(dish.getName());
            }
        }
        //采用这种方式编写的代码读起来更像是问题陈述，并行化也非常容易
        Test.menu.parallelStream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

    /**
     * 从lambda表达式到方法引用的转换
     * 很多通用的归约操作，比如 sum 、 maximum ，都有内建的辅助方法可以和方法引用结合使用
     */
    private static void transform() {

        //你可以将Lambda表达式的内容抽取到一个单独的方法中，将其作为参数传递给 groupingBy方法
        Test.menu.stream()
                .collect(
                        Collectors.groupingBy(dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        }));
        //还需要在 Dish 类中添加 getCaloricLevel 方法
        Map<top.dzygod.jdk8.practice.chaptersix.Test1.CaloricLevel, List<Dish>> collect
                = Test.menu.stream()
                //将lambda表达式抽取到一个方法内
                .collect(Collectors.groupingBy(Dish::getCaloricLevel));
    }

    /**
     * 匿名类与lambda表达式的不同
     * 首先，匿名类和Lambda表达式中的 this 和 super 的含义是不同的。
     * 在匿名类中， this 代表的是类自身，但是在Lambda中，它代表的是包含类。
     * 其次，匿名类可以屏蔽包含类的变量，而Lambda表达式不能（它们会导致编译错误）
     * <p>
     * 匿名类的类型是在初始化时确定的，而Lambda的类型取决于它的上下文
     */
    private static void test1() {
        Task.doSomething(new Task() {
            @Override
            public void execute() {
                System.out.println("Danger danger!!");
            }
        });
        //对 Task 尝试使用显式的类型转换来解决这种模棱两可的情况
        Task.doSomething((Task) () -> System.out.println("测试"));
    }

}
