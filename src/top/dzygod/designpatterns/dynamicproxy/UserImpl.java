package top.dzygod.designpatterns.dynamicproxy;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/7 10:36
 * @Description: *
 */
public class UserImpl implements User{


    @Override
    public void register() {
        System.out.println("我注册了");
    }

    @Override
    public void login() {
        System.out.println("我登陆了");
    }
}
