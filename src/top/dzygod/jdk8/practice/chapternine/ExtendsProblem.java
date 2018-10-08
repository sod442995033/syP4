package top.dzygod.jdk8.practice.chapternine;

/**
 * @Author: dingziyuan
 * @Date: 2018/10/3 9:06
 * @Description:
 * 默认方法产生冲突的结局规则
 *   如果实现了两个接口有相同的方法签名
 *        解决冲突的规则:
 *        (1) 类中的方法优先级最高。类或父类中声明的方法的优先级高于任何声明为默认方法的优
 *       先级。
 *       (2) 如果无法依据第一条进行判断，那么子接口的优先级更高：函数签名相同时，优先选择
 *       拥有最具体实现的默认方法的接口，即如果 B 继承了 A ，那么 B 就比 A 更加具体。
 *       (3) 最后，如果还是无法判断，继承了多个接口的类必须通过显式覆盖和调用期望的方法，
 *       显式地选择使用哪一个默认方法的实现
 */
public class ExtendsProblem extends EndImpl implements Fathter,Son {

    public static void main(String[] args){
        new ExtendsProblem().doSomething();
    }

}

interface Fathter{
    default void doSomething() {
        System.out.println("父亲");
    }
}

interface Son extends Fathter {
    @Override
    default void doSomething() {
        System.out.println("儿子");
    }
}

interface Daughter extends Fathter {
    @Override
    default void doSomething() {
        System.out.println("女儿");
    }
}

class EndImpl implements Daughter{
    @Override
    public void doSomething() {
        System.out.println("我是最终的类");
    }
}
