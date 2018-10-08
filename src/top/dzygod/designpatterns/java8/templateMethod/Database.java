package top.dzygod.designpatterns.java8.templateMethod;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/8 11:11
 * @Description: *
 */
public class Database {

    public static Customer getCustomerWithId(int id) {

        return new Customer();
    }

}
