package top.dzygod.designpatterns.java8.templateMethod;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/8 11:21
 * @Description: OnlineBanking的lambda实现
 */
public class OnlineBankingLambda {

    /**
     * 使用lambda:
     * 现在，你可以很方便地通过传递Lambda表达式，直接插入不同的行为，
     * 不再需要继承OnlineBanking 类了：
     *
     * @param id
     * @param makeCustomHappy
     */
    public void processCustomer(int id, Consumer<Customer> makeCustomHappy) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomHappy.accept(c);
    }

    public static void main(String[] args){
        new OnlineBankingLambda().processCustomer(10086,
                (s -> System.out.println("你好! " + s.getName())));
    }


}
