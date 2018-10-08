package top.dzygod.jdk8.practice.appendix;

import javax.print.attribute.standard.Destination;
import java.util.*;

/**
 * @Author: dingziyuan
 * @Date: 2018/10/8 10:09
 * @Description: 附录
 *  java8的其他增强
 *      1.注解
 *      2.通用目标类型推断
 *  类库的更新
 *      1.集合
 *          map
 *          集合
 *          列表
 *      Collections类
 *      Comparator接口
 *      2.并发
 *          原子操作
 *          ConcurrentHashMap 类
 *      Arrays
 *          parallelSort
 *          setAll 和 parallelSetAll
 *          parallelPrefix
 *      Number 和 Math
 *      Files
 *           Files.list ——生成由指定目录中所有条目构成的 Stream<Path> 。这个列表不是递归
 *              包含的。由于流是延迟消费的，处理包含内容非常庞大的目录时，这个方法非常有用。
 *           Files.walk ——和 Files.list 有些类似，它也生成包含给定目录中所有条目的
 *              Stream<Path> 。不过这个列表是递归的，你可以设定递归的深度。注意，该遍历是依照
 *              深度优先进行的。
 *           Files.find ——通过递归地遍历一个目录找到符合条件的条目，并生成一个
 *              Stream<Path> 对象。
 *      Reflection
 *          Relection接口的另一个变化是新增了可以查询方法参数信息的API
 *      String
 *          String 类也新增了一个静态方法，名叫 join 。
 *          它可以用一个分隔符将多个字符串连接起来。
 *
 */
public class Index {

    public static void main(String[] args) {
//        test();
//        test2();
//        test3();
        Integer.valueOf()
    }


    /**
     * map的新增api
     *  getOrDefault
     *      提供一个默认值,如果map中没有该键,会返回默认值
     *      这一方法仅在没有映射时才生效
     *  computeIfAbsent
     *      它能帮助你非常方便地使用缓存模式
     *  removeIf 方法可以移除集合中满足某个谓词的所有元素
     *  replaceAll 方法会对列表中的每一个元素执行特定的操作，并用处理的结果替换该元素。
     */
    private static void test3() {
        HashMap map = new HashMap();
        map.put("1", "张三");
        map.put("2", "李四");
        map.put("3", "王二");

        Object aDefault = map.getOrDefault("1", "");
        System.out.println(aDefault);
    }

    private static void test2() {
        /**
         * Java 8中，目标类型包括向方法传递的参数
         * 伴随Java 8而来的改进让你只需要一句Collectors.toList() 就能完成期望的工作，
         * 不再需要编写像 Collectors.<Car>toList()这么复杂的代码了。
         */
        List<String> strings = Collections.<String>emptyList();
        List<String> strings1 = Collections.emptyList();

        cleanCars(Collections.emptyList());
    }

    static void cleanCars(List<Person> cars) {
    }


    private static void test() {
        List<Person> people = new ArrayList<>();
        String name = new Person(null, null, null).getName();
        System.out.println(name);
    }
}
