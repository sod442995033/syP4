package cn.dzygod.collect;

import cn.dzygod.bean.User;

import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Collect {

    public static void main(String[] args) {
//        addTest();
//        methodTest();
//        primaryConvert();
//        referenceConvert();
//        haveAllMethod();
//        itrtString();
//        itrtObj();
    }


    private static void haveAllMethod() {
        /**
         * 带all的功能演示
         *  boolean addALL(collection c);
         *  boolean removeALL(collection c);
         *  boolean containsALL(collection c);
         *  boolean retainALL(collection c);
         */

        ArrayList c = new ArrayList();
        c.add(1);
        c.add(2);
        c.add(3);

        ArrayList d = new ArrayList();
        d.add(2);
        d.add(4);

//       boolean boo = c.add(d);
//        System.out.println(boo);           //true
//       System.out.println(c);             //打印结果:[1,2,3,[2,4]]

//         boolean boo = c.addAll(d);
//        System.out.println(boo);            //true
//        System.out.println(c);               //打印结果:[1, 2, 3, 2, 4]
//
//        boolean boo = c.removeAll(d);
//        System.out.println(boo);          //true
//       System.out.println(c);             //打印结果:[1, 3]

//        boolean boo = c.containsAll(d);
//        System.out.println(boo);          //打印结果:false(不包含)

        boolean boo = c.retainAll(d);
        System.out.println(boo);            //true
        System.out.println(c);              //打印结果:[2]
    }

    //引用数据类型为元素的集合转数组
    private static void referenceConvert() {
        Collection c = new ArrayList();         //父类引用指向子类对象
        c.add(new User("张三", 12, "没人性"));//Object object = new User();
        c.add(new User("李四", 11, "有人性"));
        Object[] objects = c.toArray();

        for (int i = 0; i < objects.length; i++) {
            User arr = (User) objects[i];          //需要向下转型后才可以进行调用
            System.out.println(arr.getUserName());
        }
    }

    //基本类型为元素的集合转数组
    private static void primaryConvert() {
        Collection c = new ArrayList();         //父类引用指向子类对象
        c.add("李四");
        c.add(1);
        c.add(true);
        Object[] ob = c.toArray();

        for (Object o : ob) {
            System.out.println(o);              //输出的依旧是子类的toString()方法
        }
    }

    private static void methodTest() {
        Collection c = new ArrayList();         //父类引用指向子类对象
        c.add("李四");
        c.add(2);
        c.add(true);
        System.out.println(c);
        c.remove(3);                    //删除集合中指定的元素,如果没有该元素不会做任何操作
        c.clear();                          //清空集合中的所有元素

        System.out.println(c.contains(2));//是否包含,包含为true
        System.out.println(c.isEmpty());//是否为空
        System.out.println(c.size());//集合长度
    }


    //add方法
    public static void addTest() {

        Collection c = new ArrayList();         //父类引用指向子类对象

        User zhang = new User("张三", 14, "是个人");
        User li = new User("李四", 15, "不是个人");
        boolean zs = c.add(zhang);
        boolean ls = c.add(li);

        //集合中传入了基本数据类型的元素,编译器没有报错,因为会转化为引用数据类型
        c.add(1);

        //arraylist的父类的父类重写了toString()方法,所以输出的不是全类名+@+0xhashcode
        System.out.println(c);

        //两个都是true,因为collection接口中add方法在ArrayList类中是只返回true的,
        // 而在collection接口的set实现类中是不能有重复元素的,重复就会返回false
        System.out.println(zs + "\n" + ls);

        //User{userName='张三', age=14, remark='是个人'} 重写了User类的toString()方法
        System.out.println(zhang + "\n" + li);

    }

    //迭代
    public static void itrtString() {
        Collection c = new ArrayList();

        c.add("a");
        c.add("b");
        c.add("c");
        c.add("d");

        Iterator it = c.iterator();           //获取迭代器对象
        while (it.hasNext()) {               //判断指针是否不等于集合长度,如果二者相同返回false.这样做是为了防止触发NoSuchElementException()
            System.out.println(it.next());                   //调用next()方法后,会使指针+1
        }
    }


    //迭代bean对象
    public static void itrtObj() {
        Collection c = new ArrayList();

        c.add(new User("张三", 12, "没问题"));   //实际上是Object obj = new User("张三", 12, "没问题")
        c.add(new User("李四", 12, "年轻人"));
        c.add(new User("赵武", 12, "年纪其实一样"));
        Iterator iterator = c.iterator();
        while (iterator.hasNext()) {
//            System.out.println(iterator.next());   //迭代不了对象中的方法,父类无法指向子类具体方法
            User user = (User) iterator.next();      //向下转型
            System.out.println(user.getRemark() + "\t" + user.getAge() + "\t" + user.getUserName() + "\n");
        }

    }


}
