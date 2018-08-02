package top.dzygod.list;

import top.dzygod.bean.Student;
import top.dzygod.bean.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: dingziyuan
 * @Date: 2018-04-23 16:18
 * @Description: 泛型的练习类
 */
public class Generic {
    public static void main(String[] args) {
//        gnrcString();
//        gnrtUser();
//        currencyGnrc();
    }

    private static void currencyGnrc() {
        /**
         * A: 泛型通配符<?>
         *      * 任意类型,如果没有明确,那就是Object以及任意java类了
         * B: ? extends E
         *      * 向下限定,E及其子类
         * C: ? Super E
         *      * 向上限定,E及其父类
         */

        //当右边的泛型不确定时,左边可以指定为?
        ArrayList<?> arr = new ArrayList<String>();


        ArrayList<User> users = new ArrayList<>();
        users.add(new User("张三"));
        users.add(new User("里斯"));
        users.add(new User("王五"));
        users.add(new User("找刘"));

        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("马尾"));
        students.add(new Student("蟹子"));
        students.add(new Student("狐"));

        //addAll()的参数为 ? extends E
        users.addAll(students);

        //调用的是父类的toString()
        System.out.println(users);
    }

    /**
     * User类集合为泛型的练习
     */
    private static void gnrtUser() {
        List<User> s = new ArrayList();
        s.add(new User("张三"));
        s.add(new User("里斯"));
        s.add(new User("王无"));

        Iterator<User> iterator = s.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getUserName());
        }
    }


    /**
     * String为泛型的练习
     */
    private static void gnrcString() {
        List<String> s = new ArrayList();
        s.add("张三");
        s.add("里斯");
        s.add("王五");
        s.add("找刘");
        Iterator<String> iterator = s.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}

/**
 * 泛型接口的练习
 */
interface Inter<T> {
    public void print(T t);
}

/**
 * 这是第一种实现方式
 * 直接给接口指定引用类型
 */
class demo implements Inter<String> {

    @Override
    public void print(String s) {
        System.out.println(s);
    }
}


/**
 * 第二种方式
 * 将实例化指定的泛型类型,
 * 赋值给接口
 *
 * @param <T>
 */
class demo2<T> implements Inter<T> {

    @Override
    public void print(T t) {
        System.out.println(t);
    }
}