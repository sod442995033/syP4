package top.dzygod.designpatterns.java8.strategy;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/8 10:45
 * @Description: 策略模式,利用多态(父类引用指向子类对象)
 */
public class Validator {
    private final ValidationStrategy strategy;

    public Validator(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean validate(String s) {
        return strategy.execute(s);
    }

    /**
     * 使用lambda表达式:
     *  ValidationStrategy 是一个函数接口,它与 Predicate<String> 具有同样的函数描述
     *  不需要声明新的类来实现不同的策略，通过直接传递Lambda表达式就能达到同样的目的
     *  接口与实现类都可以进行省略
     *  Lambda表达式实际已经对部分代码（或策略）进行了封装，而这就是创建策略设计模式的初衷
     */
    public static void main(String[] args){
//        traditionImpl();
        Validator numericValidator = new Validator(s -> s.matches("\\d+"));
        boolean b1 = numericValidator.validate("aaaa");
        Validator lowerCaseValidator = new Validator(s -> s.matches("[a-z]+"));
        boolean b2 = lowerCaseValidator.validate("bbbb");
        System.out.println(b1 + "\n" + b2);

    }

    /**
     * 验证策略
     */
    private static void traditionImpl() {
        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaaa");
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("bbbb");
        System.out.println(b1 + "\n" + b2);
    }

}
