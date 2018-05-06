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
public class PracticeLink {


    public static void main(String[] args) {
        linkedListPri();
    }

    /**
     * public void addFirst(E e)及addLast(E e);
     * public E getFirst()及getLast();
     * public E removeFirst()及public E removeLast()
     * public E get(int index)
     * */
    private static void linkedListPri() {
        LinkedList list = new LinkedList();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add("赵六");

        list.addFirst("王八");  //[王八, 张三, 李四, 王五, 赵六]
        list.addLast("王九");   //[王八, 张三, 李四, 王五, 赵六, 王九]


        Object first = list.getFirst();//王八
        Object last = list.getLast();//王九

        list.removeFirst();       //[张三, 李四, 王五, 赵六, 王九]
        list.removeLast();          //[张三, 李四, 王五, 赵六]

        Object one = list.get(1);
        System.out.println(one);    //李四,底层会根据传入index和size判断是从链表的头开始查询还是尾部开始查询


        System.out.println(list);
    }
}
