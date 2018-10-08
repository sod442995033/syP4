package top.dzygod.jdk8.practice.chapterten;

import javafx.beans.binding.IntegerExpression;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Properties;

/**
 * @Author: dingziyuan
 * @Date: 2018/10/3 15:36
 * @Description: Option类
 */
public class OptionalTest {


    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
//        test8();
//        test9();
    }

    private static void test9() {
        System.out.println(readDuration(new Properties(), "张三"));
    }

    /**
     * optional整合练习
     * 如果任何一个操作返回一个空的 Optional 对象，
     * 该方法都会返回 orElse 方法设置的默认值 0 ；否则就返回封装在 Optional 对象中的正整数
     * @param props
     * @param name
     * @return
     */
    public static int readDuration(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(OptionalTest::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }

    /**
     * optional类型转换
     * @param num
     * @return
     */
    private static Optional<Integer> stringToInt(String num) {
        try {
            return Optional.of(Integer.parseInt(num));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * 异常与 Optional 的对比
     * 不推荐使用基础类型的 Optional,
     * 因为基础类型的 Optional 不支持 map 、flatMap 以及 filter 方法，
     */
    private static void test8() {
        OptionalInt empty = OptionalInt.of(100);
        System.out.println(empty.getAsInt());
    }

    /**
     * 用Optional封装可能为null的值
     */
    private static void test7() {
        Optional<Object> t = Optional.ofNullable(new HashMap<String, String>());
    }

    /**
     * 使用 filter 剔除特定的值
     * filter 方法接受一个谓词作为参数。
     * 如果 Optional 对象的值存在，
     * 并且它符合谓词的条件，filter 方法就返回其值；否则它就返回一个空的 Optional 对象
     */
    private static void test6() {
        Optional<Insurance> insurance = Optional.of(new Insurance());
        insurance.filter(ins -> "剑桥大学".equals(ins.getName()))
                .ifPresent(x -> System.out.println("正确"));
    }

    private static void test5() {
        Optional<Insurance> insurance = new OptionalTest().nullSafeFindCheapestInsurance
                (Optional.of(new Person()), Optional.of(new Car()));
        insurance.ifPresent(b -> System.out.println("名字是:"+b));
    }


    /**
     * 以不解包的方式组合两个 Optional 对象:
     *  你对第一个 Optional 对象调用 flatMap 方法，
     *  如果它是个空值，传递给它的Lambda表达式不会执行，
     *  这次调用会直接返回一个空的 Optional 对象。
     *  反之，如果 person对象存在，这次调用就会将其作为函数 Function 的输入，
     *  并按照与 flatMap 方法的约定返回一个 Optional<Insurance> 对象。
     * @param person
     * @param car
     * @return
     */
    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person,
                                                             Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }

    /**
     * 两个 Optional 对象的组合
     */
    public Insurance findCheapestInsurance(Person person, Car car) {

        return new Insurance();
    }


    private static void test4() {
        /**
         * 默认行为及解引用 Optional 对象
         *   get()
         *      是这些方法中最简单但又最不安全的方法。
         *   orElse(T other)
         *      它允许你在Optional 对象不包含值时提供一个默认值。
         *   orElseGet(Supplier<? extends T> other)
         *      是 orElse 方法的延迟调用版， Supplier方法只有在 Optional 对象不含值时才执行调用
         *   orElseThrow(Supplier<? extends X> exceptionSupplier)
         *      和 get 方法非常类似，它们遭遇 Optional 对象为空时都会抛出一个异常，
         *      但是使用 orElseThrow 你可以定制希望抛出的异常类型。
         *   ifPresent(Consumer<? super T>
         *      让你能在变量值存在时执行一个作为参数传入的方法，否则就不进行任何操作
         */}

    /**
     * 使用 Optional 获取 car 的保险公司名称
     * 如果调用链上的任何一个方法返回一个空的 Optional ，
     * 那么结果就为空，否则返回的值就是你期望的保险公司的名称
     */
    private static void test3() {
        String mes = new OptionalTest()
                .getCarInsuranceName(Optional.ofNullable(null));
        System.out.println(mes);
    }

    /**
     * flatMap过滤
     * 使用 flatMap 链接 Optional 对象
     * 方法生成的各个流会被合并或者扁平化为一个单一的流
     *
     * @return
     */
    private String getCarInsuranceName(Optional<PersonTwo> person) {
        return person
                .flatMap(PersonTwo::getCar).flatMap(CarTwo::getInsurance)
                .map(InsuranceTwo::getName).orElse("notEmpty");
    }


    /**
     * 使用map从optional对象中提取和转换值
     * 如果 Optional 包含一个值，那函数就将该值作为参数传递给 map ，
     * 对该值进行转换。如果 Optional 为空，就什么也不做
     */
    private static void test2() {
        Optional<Car> car = Optional.ofNullable(new Car());
//        Optional<Insurance> insurance = car.map(Car::getInsurance);
//        System.out.println(insurance.isPresent());
    }

    /**
     * 创建一个Optional对象
     * 1.声明一个空的Optional对象
     * 2.依据一个空值创建Optional
     * 3.可接受null的Optional
     */
    private static void test1() {
        //1.声明一个空的Optional对象
        Optional<Car> empty = Optional.empty();
        //2.如果car是一个null指针,那么这段代码会直接抛出空指针异常,而不是当你访问car中的属性值才返回一个错误
        Optional<Car> car = Optional.of(new Car());
        //3.允许null值的optional对象,如果car是null,那么得到的car就是一个空对象
        Optional<Car> car2 = Optional.ofNullable(new Car());
    }
}
