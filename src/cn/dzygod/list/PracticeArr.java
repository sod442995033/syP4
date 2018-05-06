package cn.dzygod.list;

import cn.dzygod.bean.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: dzyGod
 * @Date: 2018-04-09 21:08
 * @Description: *
 */
public class PracticeArr {


    public static void main(String[] args) {
        //去除arrayList中相同字符串元素的方法
//        ArrayList list = new ArrayList();
//
//        list.add("张三");
//        list.add("张三");
//        list.add("张三");
//        list.add("李四");
//        list.add("李四");
//        list.add("李四");
//        list.add("王五");
//        list.add("王五");
//        list.add("赵六");
//        list.add("赵六");


//        List retList = toRepeat(list);
//        ArrayList list1 = toRepeat2(list);
//        System.out.println(list1);


        ArrayList list = new ArrayList();

        list.add(new User("张三",14,"无"));
        list.add(new User("张三",14,"无"));
        list.add(new User("张三",14,"无"));
        list.add(new User("李四",14,"无"));
        list.add(new User("李四",14,"无"));
        list.add(new User("李四",14,"无"));
        list.add(new User("王五",14,"无"));
        list.add(new User("王五",14,"无"));
        list.add(new User("赵六",14,"无"));
        list.add(new User("赵六",14,"无"));

        ArrayList single = getSingle(list);
        //remove底层也是equals方法来进行判断,没重写前比较的是地址值,重写之后比较的是元素值
        single.remove(new User("张三",14,"无"));
        System.out.println(single);
    }


    //去重复字符串的方法
    public static List toRepeat(List outList) {
        ArrayList list = new ArrayList();
        for (Object one : outList) {
            if (!list.contains(one)) {
                list.add((String) one);
            }
        }
        return list;
    }

    //去重复字符串的方法2
    public static ArrayList toRepeat2(List outList) {

        ArrayList list = new ArrayList();
        Iterator itrt = outList.iterator();

        while (itrt.hasNext()) {
            Object next = itrt.next();
            if (!list.contains(next)) {
                list.add(next);
            }
        }
        return list;
    }

    //去List中元素重复的bean类的方法
    public static ArrayList getSingle(ArrayList outList){
        ArrayList InnerList = new ArrayList();
        Iterator itrt = outList.iterator();

        while(itrt.hasNext()){
            Object next = itrt.next();
            if(!InnerList.contains(next)){       //底层判断用的是equals()方法,将User类中的equals()方法进行重写
                InnerList.add(next);
            }
        }

        return InnerList;
    }



}
