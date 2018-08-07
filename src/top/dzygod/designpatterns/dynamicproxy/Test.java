package top.dzygod.designpatterns.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/7 10:37
 * @Description: *
 */
public class Test {

    public static void main(String[] args){
        UserImpl userimpl = new UserImpl();


        MyInvocationHandler handler = new MyInvocationHandler(userimpl);


        //通过字节码代理获得接口对象
        User user = (User) Proxy.newProxyInstance(userimpl.getClass().getClassLoader(),
                userimpl.getClass().getInterfaces(), handler);

        user.login();
        user.register();

    }
}
