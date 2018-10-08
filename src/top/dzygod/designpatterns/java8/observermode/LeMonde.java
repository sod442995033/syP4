package top.dzygod.designpatterns.java8.observermode;

/**
 * @Author: dingziyuan
 * @Date: 2018/9/8 12:05
 * @Description: 观察者世界报
 */
public class LeMonde  implements Observer{
    @Override
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("wine")){
            System.out.println("Today cheese, wine and news! " + tweet);
        }
    }
}
