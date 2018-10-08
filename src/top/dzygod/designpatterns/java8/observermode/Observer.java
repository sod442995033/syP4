package top.dzygod.designpatterns.java8.observermode;

/**
 * 某些事件发生时（比如状态转变），如果一个对象（通常我们称之为主题）需要自动地通知其他多个对象（称为观察者），
 * 就会采用该方案。
 *
 * @Author: dingziyuan
 * @Date: 2018/9/8 11:45
 * @Description: 观察者模式
 */
public interface Observer {

    /**
     * 通知
     *
     * @param tweet
     */
    void notify(String tweet);


}
