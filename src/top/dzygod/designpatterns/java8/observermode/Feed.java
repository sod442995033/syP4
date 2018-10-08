package top.dzygod.designpatterns.java8.observermode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/8 14:29
 * @Description: 入口
 */
public class Feed implements Subject {

    private final List<Observer> observers = new ArrayList<>();



    public static void main(String[] args){
//        enter();
//        lambdaEnter();
    }


    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObservers(String tweet) {
        this.observers.forEach(observer -> observer.notify(tweet));
    }

    /**
     * 使用lambda表达式
     * lambda不适用于非常复杂的逻辑
     */
    private static void lambdaEnter() {
        String tweet = "The queen said her favourite book is Java 8 in Action!";
        Feed feed = new Feed();
        feed.registerObserver((String t) -> {
            if(t != null && t.contains("money")){
                System.out.println("Breaking news in NY! " + tweet);
            }
        });
        feed.registerObserver((String t1) -> {
            if(t1 != null && t1.contains("queen")){
                System.out.println("Yet another news in London... " + t1);
            }
        });

        feed.notifyObservers(tweet);
    }


    /**
     * Enter
     */
    private static void enter() {
        String tweet = "The queen said her favourite book is Java 8 in Action!";
        Feed feed = new Feed();
        feed.registerObserver(new Guardian());
        feed.registerObserver(new LeMonde());
        feed.registerObserver(new NYTimes());
        feed.notifyObservers(tweet);
    }
}
