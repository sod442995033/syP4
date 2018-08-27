package top.dzygod.jdk8.行为参数化;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/16 12:24
 * @Description: 行为参数化的练习
 */
public class MainParameterize {


    //我需要红色大于150斤的苹果
    public static void main(String[] args){

//        JDK8的函数参数化,有新的功能都不需要加一个类
//        JDK8();

//        策略模式的实现,有新的功能只需要加一个类
//        strategyMode();

//        普适的方法filterEverything(),可以处理所有情况
//        predicate();

        Apple[] apples = {new Apple("红色",160),
                new Apple("蓝色",140),new Apple("紫色",162)};

        List<Apple> applesList = Arrays.asList(apples);

        applesList.sort((Apple a,Apple b) -> a.getWeight().compareTo(b.getWeight()));

        System.out.println(applesList);

    }

    private static void predicate() {
        Apple[] apples = {new Apple("红色",160),
                new Apple("蓝色",140),new Apple("紫色",162)};

        List<Apple> applesList = Arrays.asList(apples);

        ArrayList<Apple> list = filterEverything(applesList,
                (Apple a) -> "红色".equals(a.getColor()));

        System.out.println(list);
    }


    private static void strategyMode() {
        Apple[] apples = {new Apple("红色", 160),
                new Apple("蓝色", 140), new Apple("紫色", 162)};

        List<Apple> applesList = Arrays.asList(apples);

        ArrayList<Apple> list = filterApple(applesList, new AppleRedAndHeavyPredicate());

        System.out.println(list);
    }

    private static void JDK8() {
        Apple[] apples = {new Apple("红色",160),
                new Apple("蓝色",140),new Apple("紫色",162)};

        List<Apple> applesList = Arrays.asList(apples);

        ArrayList<Apple> list = filterApple(applesList,
                //在这里,直接函数参数化,函数真正的成为了一等公民!!!接口也不需要实现了!!
                (Apple a) -> a.getColor().equals("红色") && a.getWeight() > 150);

        System.out.println(list);
    }

    private static ArrayList<Apple> filterApple(List<Apple> apples,ApplePredicate predicate) {

        ArrayList<Apple> applesArr = new ArrayList();
        for (Apple apple : apples) {
            //将这一句predicate.test(apple)抽象了出来
            if (predicate.test(apple)) {
                applesArr.add(apple);
            }
        }

        return applesArr;
    }


    //普适过滤,可以过滤任何事情
    private static <T> ArrayList<T>  filterEverything (List<T> list,Predicate<T> everything) {
        ArrayList<T> lists = new ArrayList<>();

        for (T t : list) {
            if (everything.test(t)) {
                lists.add(t);
            }
        }

        return lists;
    }

}
