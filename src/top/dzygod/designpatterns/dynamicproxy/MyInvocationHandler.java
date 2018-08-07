package top.dzygod.designpatterns.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/7 10:38
 * @Description: *
 */
public class MyInvocationHandler implements InvocationHandler {
    private Object object;

    public MyInvocationHandler(Object proxy) {
        this.object = proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("登陆一波");
        //执行被代理的对象的方法
        Object invoke = method.invoke(object, args);
        System.out.println("注册一波");

        return null;
    }
}
