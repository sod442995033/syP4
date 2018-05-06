package cn.dzygod.list;

import cn.dzygod.bean.User;

import java.util.*;

/**
 * @Author: dzyGod
 * @Date: 2018-04-05 19:02
 * @Description: *
 */
public class ListTest {
    /**
     * void add(int index,e element)
     * E  remove(int index)
     * E  get(int index)
     * E  set(int index,E element)
     */

    public static void main(String[] args) {
//        testAdd();
//        testRemove();
//        testGet();
//        testSet();
//        example();
//        example2();
//        testExample();
//        testPrevious();

        new LinkedList();
    }

    private static void testPrevious() {
        List l = new ArrayList();

        l.add("张三");
        l.add("李四");
        l.add("王二");
        l.add("孙七");

        ListIterator lsit = l.listIterator();

        while (lsit.hasNext()){
            System.out.println(lsit.next());
        }

        System.out.println("---------------------------------------------");

        while (lsit.hasPrevious()){                     //判断前一个是否不为第一个元素
            System.out.println(lsit.previous());        //反向遍历,指针-1
        }
    }

    private static void testExample() {
        //集合在遍历的时候
        List l = new ArrayList();

        l.add("张三");
        l.add("李四");
        l.add("王二");
        l.add("孙七");


//        Iterator it = l.iterator();
//        while(it.hasNext()){
//            if("张三".equals(it.next())){
//                l.add("王八");    //ConcurrentModificationException并发修改异常,集合没有告诉迭代器的情况下就新增元素
//            };
//        }

        ListIterator it = l.listIterator();         //使用listIterator()方法获得list的迭代器
        while(it.hasNext()){
            if("张三".equals(it.next())){
                it.add("王八");                   //listIterator类中的add()方法不会触发并发修改异常,因为是迭代器内部的方法
            };
        }
        System.out.println(l);                      //输出结果:[张三, 王八, 李四, 王二, 孙七]
    }

    private static void example2() {
        /**
         * 案例2:
         * 需求:我有一个集合,请问,我想判断里边有没有"world"这个元素,如果有我就添加一个"javaee"元素
         * */
        List list = new ArrayList();

        list.add("countrl");
        list.add("china");
        list.add("world");

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).equals("world")){
                list.set(i,"javaee");
            }
        }

        System.out.println(list);
    }

    private static void example() {
        /**
         * 案例演示:
         * 向list集合中存储用户对象
         * 通过size()和get()方法结合使用便利
         * */

        List uList = new ArrayList();

        uList.add(new User("张三"));
        uList.add(new User("李四"));
        uList.add(new User("王五"));

        for (int i = 0; i < uList.size(); i++) {
//            System.out.println(uList.get(i));       //通过索引获取每一个元素
            User user = (User) uList.get(i);          //向下转型
            System.out.print(user.getUserName() +"\n");
        }
    }

    private static void testSet() {
        List list = new ArrayList();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add("d");

        list.set(1,"张三");       //在指定角标插入指定元素,会将该角标之前的元素删除
        System.out.println(list);//输出为[1, 张三, 3, d]
    }

    private static void testGet() {
        List list = new ArrayList();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add("d");


        System.out.println(list.get(3));//输出为d
    }

    private static void testRemove() {
        List list = new ArrayList();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add("d");

        list.remove("d");
        list.remove(1);//实参如果是数值,不会进行自动装箱,因为无法判断删除的是index角标,还是对象
        System.out.println(list);//输出[1, 3],角标为1和为d的字符串对象都被删除了
    }


    private static void testAdd() {
        List list = new ArrayList();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        list.add(1,"c");    //输出[a, c, b, c, d],在指定角标插入元素,角标后所有的元素都向后移动一位

        System.out.println(list);
    }


}
