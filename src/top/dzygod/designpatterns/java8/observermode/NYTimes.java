package top.dzygod.designpatterns.java8.observermode;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/8 12:03
 * @Description: 观察者纽约时报
 */
public class NYTimes implements Observer{


    @Override
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("money")){
            System.out.println("Breaking news in NY! " + tweet);
        }
    }
}
