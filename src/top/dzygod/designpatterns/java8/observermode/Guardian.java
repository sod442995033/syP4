package top.dzygod.designpatterns.java8.observermode;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/8 12:04
 * @Description: 观察者卫报
 */
public class Guardian implements Observer{


    @Override
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("queen")){
            System.out.println("Yet another news in London... " + tweet);
        }
    }
}
