package top.dzygod.set;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @Author: dingziyuan
 * @Date: 2018-04-25 17:21
 * @Description: Comparator
 */
public class ComparatorTest {
    public static void main(String[] args) {
        binaryS();


    }


    /**
     *  练习
     */
    private static void binaryS() {

        TreeSet<String> strings = new TreeSet<>(new Test());
        strings.add("张三而");
        strings.add("张三");
        System.out.println(strings);
    }

}

class Test implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        int num = o1.length() - o2.length();
        return num == 0?o1.compareTo(o2):num;
    }
}
