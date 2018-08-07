package top.dzygod.reflection;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/3 11:43
 * @Description: 反射
 */
public class Reflect {

    public static void main(String[] args) throws Exception {
//        classLoaders();

        /**
         * class.forName();读取配置文件举例
         * 喝果汁的例子
         */

//        接口实现
       /* Juice juice = new Juice();
        juice.run(new Orange());*/

//反射实现
//        reflectImpl();

//        parametricStructure();

//        setPriField();

//        reflectMethod();




    }

    /**
     * 反射执行底层成员方法
     */
    private static void reflectMethod() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        Class<?> aClass = Class.forName("top.dzygod.reflection.Person");
        Constructor<?> constructor = aClass.getConstructor(int.class, String.class);
        Person wangba = (Person)constructor.newInstance(100, "王八");

        //getDeclaredMethod();私有的方法,所以必须要使用这个方法,设置
        Method eat = aClass.getDeclaredMethod("eat");
        //去除私有权限
        eat.setAccessible(true);
        eat.invoke(wangba);

        Method eat1 = aClass.getDeclaredMethod("eat",int.class);
        eat1.setAccessible(true);
        eat1.invoke(wangba,100);
    }

    /**
     * 获取私有字段,为私有成员字段设置值
     * 非私有直接getField即可
     */
    private static void setPriField() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchFieldException {

        Class<?> aClass = Class.forName("top.dzygod.reflection.Person");
        Constructor<?> constructor = aClass.getConstructor(int.class, String.class);
        Person person = (Person)constructor.newInstance(19, "王五");
        System.out.println(person);

        //暴力获取私有字段
        Field name = aClass.getDeclaredField("name");
        //去除私有权限
        name.setAccessible(true);
        name.set(person,"李四");
        System.out.println(person);

    }


    /**
     * 使用有参构造实例化
     */
    private static void parametricStructure() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        Class<?> aClass = Class.forName("top.dzygod.reflection.Person");
        Constructor<?> constructor = aClass.getConstructor(int.class, String.class);
        Object o = constructor.newInstance(13, "李四");
        System.out.println(o);
    }

    /**
     * 反射实现
     */
    private static void reflectImpl() throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        File file = new File("config.properties");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String fstLine = reader.readLine();
        String secLine = reader.readLine();

        Class<?> aClass = Class.forName(fstLine);
        Constructor<?> con = aClass.getDeclaredConstructor();
        con.setAccessible(true);
        //在实例化内部类对象之前先实例化外部类对象
        Object o = con.newInstance();
        //内部类对象的构造方法看似无参其实是有含有外部类对象的有参构造函数
        Class<?> aClass1 = Class.forName(secLine);
        //这才是他真正的构造函数
        con = aClass1.getDeclaredConstructor(Reflect.class);
        con.setAccessible(true);
        //既然含有外部类对象的构造函数自然在构造的时候要把外部类的对象传入
        Fruit fruit = (Fruit) con.newInstance(o);
        fruit.squeeze();


    }

    interface Fruit {
        void squeeze();
    }

    class Apple implements Fruit {

        @Override
        public void squeeze() {
            System.out.println("我在喝苹果汁");
        }
    }

    class Orange implements Fruit {
        @Override
        public void squeeze() {
            System.out.println("我在喝橘子汁");
        }
    }

    class Juice {
        void run(Fruit fruit) {
            fruit.squeeze();
        }
    }


    /**
     * 类加载的三种方式:
     * 源文件阶段
     * Class.forName("全类名");读取配置文件
     * <p>
     * 字节码阶段
     * "类名".class;同步锁
     * <p>
     * 创建对象阶段
     * "对象名".getClass();
     */
    private static void classLoaders() throws ClassNotFoundException {
        Class<?> clazz1 = Class.forName("top.dzygod.reflection.Reflect");

        Class<Reflect> clazz2 = Reflect.class;

        Reflect reflect = new Reflect();
        Class<? extends Reflect> clazz3 = reflect.getClass();

        //返回的都是true,都是同一个字节码对象
        System.out.println(clazz1 == clazz2);
        System.out.println(clazz2 == clazz3);
    }


}
