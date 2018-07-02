package cn.dzygod.io;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @Author: dingziyuan
 * @Date: 2018/6/12 11:19
 * @Description: Properties类的概述和作为map集合的使用
 */
public class ProperOverview {

    public static void main(String[] args) throws IOException {
//        test1();
//        test2();
//        test3();

    }

    /**
     * properties的读写
     * hashtable为底层,所以写出的顺序不一致
     *
     */
    private static void test3() throws IOException {
        Properties properties = new Properties();
        properties.load(new InputStreamReader(new FileInputStream("test.properties"),"gbk"));
        String name = (String)properties.get("name");
        String age = (String)properties.get("age");
        String gender = (String)properties.get("gender");

        System.out.println(name);
        System.out.println(age);
        System.out.println(gender);


        Properties outProperties = new Properties();

        outProperties.setProperty("height","180cm");
        outProperties.setProperty("name","Tony");
        outProperties.setProperty("age","19");
        outProperties.setProperty("gender","man");
        //第二个参数用来描述文件列表,不描述给个null即可
        outProperties.store(new FileOutputStream("test.properties"),null);
    }

    /**
     * Properties的特殊功能使用
     *  public Object setProperty(String key,String value)
     *  public Object getProperty(String key)
     *  public Enumeration<String> PropertyNames()
     */
    private static void test2() {

        Properties properties = new Properties();
        Object o = properties.setProperty("11", "1");
        if (o == null) {
            String property = properties.getProperty("11");
            System.out.println(property);
        }

        properties.setProperty("22", "2");
        properties.setProperty("33", "2");
        properties.setProperty("44", "2");
        properties.setProperty("55", "2");

        Enumeration<String> enumeration = (Enumeration<String>)
                properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = (String) properties.get(key);
            System.out.println(key.concat("==").concat(value));
        }
    }

    /**
     * A.Properties的概述
     *      Properties类表示了一个持久的属性集
     *      Properties可保存在流中或在流中加载
     *      属性列表中每个键及其对应值都是一个字符串
     * B.案例演示
     *      Properties作为map集合的使用
     */
    private static void test1() {


        Properties properties = new Properties();
        Object a = properties.put("a", 123);
        System.out.println(a);
        System.out.println(properties);
    }
}
