package top.dzygod.reflection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/4 16:14
 * @Description: 反射的练习
 */
public class Practice {

    public static void main(String[] args) throws Exception {


//        test1();
//        test2();
//        test3();

    }



    /**
     * 第三个练习
     * 通用方法，已经创建的对象通过反射对它的field设置值
     * @throws Exception
     */
    private static void test3() throws Exception {
        Person person = new Person(19,"网吧");
        Person person1 = (Person) setProperties(person,"name","王五");
        System.out.println(person1.getName());
    }


    /**
     * 通过反射写一个通用方法
     * 为某个对象的某个字段设置值
     */
    public static <T,R> T setProperties(T obj,String properName,R value) throws Exception {

        Class<?> aClass = obj.getClass();
        Field field = aClass.getDeclaredField(properName);
        field.setAccessible(true);
        field.set(obj,value);

        return obj;
    }

    /**
     * 使用反射运行Person类的eat方法
     * 防止硬编码,配置全都在config.properties里边
     */
    private static void test2() throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Properties properties = new Properties();

        properties.load(new InputStreamReader(new FileInputStream("config.properties")));

        String url = (String) properties.get("PersonURL");
        Integer age = Integer.parseInt((String) properties.get("age"));
        String name = (String) properties.get("name");


        Class<?> aClass = Class.forName(url);

        Constructor<?> constructor = aClass.getDeclaredConstructor(int.class, String.class);
        Object o = constructor.newInstance(age, name);

        Method eat = aClass.getDeclaredMethod("eat", int.class);
        eat.setAccessible(true);
        eat.invoke(o, 50);
    }

    /**
     * 使用泛型
     * 在ArrayList<Intege>;中加入一个String元素
     * 思路:
     * 在运行器拿到字节码对象,获取方法添加元素即可(因为泛型在编译期会被擦除)
     */

    private static void test1() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ArrayList<Integer> integers = new ArrayList<>();

        Class<? extends ArrayList> aClass = integers.getClass();

        Method add = aClass.getMethod("add", Object.class);
        add.invoke(integers, "字符串暴力插入");

        System.out.println(integers);
    }


}
