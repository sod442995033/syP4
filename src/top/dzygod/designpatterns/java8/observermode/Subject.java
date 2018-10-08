package top.dzygod.designpatterns.java8.observermode;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/8 14:28
 * @Description:
 */
public interface Subject {

    /**
     * 注册一个新的观察者
     * @param o
     */
    void registerObserver(Observer o);


    /**
     * 通知它的观察者一个新闻的到来
     * @param tweet
     */
    void notifyObservers(String tweet);
}

