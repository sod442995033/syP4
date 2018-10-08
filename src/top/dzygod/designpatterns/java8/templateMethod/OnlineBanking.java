package top.dzygod.designpatterns.java8.templateMethod;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/8 11:03
 * @Description: 模板方法设计模式
 * 如果你需要采用某个算法的框架，同时又希望有一定的灵活度，能对它的某些部分进行改进，
 * 那么采用模板方法设计模式是比较通用的方案.
 * "希望使用这个算法，但是需要对其中的某些行进行改进，才能达到希望的效果"
 */
abstract class OnlineBanking {

    /**
     * processCustomer 方法搭建了在线银行算法的框架：
     * 获取客户提供的ID，然后提供服务让用户满意。
     * 不同的支行可以通过继承 OnlineBanking 类，对该方法提供差异化的实现
     *
     * @param id
     */
    public void processCustomer(int id) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(c);
    }

    abstract void makeCustomerHappy(Customer c);

}
