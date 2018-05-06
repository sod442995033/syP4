package cn.dzygod.list;

import java.util.LinkedList;

/**
 * @Author: dzyGod
 * @Date: 2018-04-10 12:10
 * @Description: *
 */
public class StackAndQueue {

    public static void main(String[] args) {
//        stackTest();


    }

    private static void stackTest() {
        //linkedList来模拟栈的数据结构,先进后出
        LinkedList list = new LinkedList();
        list.addLast("1");
        list.addLast("2");
        list.addLast("3");

      /*  System.out.println(list.removeLast());
        System.out.println(list.removeLast());
        System.out.println(list.removeLast());*/

   /*   while(!list.isEmpty()){
          System.out.println(list.removeLast());
      }*/

        //对linkedList中的方法进行封装,实现栈的数据结构
        Stack stack = new Stack();
        stack.in("张三");
        stack.in("李四");
        stack.in("王五");

        while (!stack.isEmpty()){
            System.out.println(stack.out());
        }
    }


}
