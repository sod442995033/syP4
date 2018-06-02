package cn.dzygod.collections;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Author: dingziyuan
 * @Date: 2018/5/12 17:29
 * @Description: *
 */
public class CollectionsPra {


    public static void main(String[] args) {
        /**
         * Collections
         *
         * public static <T> void sort(List<T> list)
         * public static <T> int  binarySearch(List<T> list,T key)
         * public static <T> T Max(Collection<?> coll)
         * public static <T> void reverse(List<T> list)
         * public static <T> void shuffle(List<T> list)
         */


//        colsSort();
//        colBinaryser();
//        colMinMax();
//        colDesc();
//        random();


    }

    /**
     * 随机分配,
     * 洗牌
     */
    private static void random() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("c");
        strings.add("d");
        strings.add("e");

        /**
         * 随机排序,洗牌
         */
        Collections.shuffle(strings);

        System.out.println(strings);
    }

    /**
     * 倒序
     */
    private static void colDesc() {

        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("c");
        strings.add("d");
        strings.add("e");

        Collections.reverse(strings);

        System.out.println(strings);
    }

    /**
     * 不言自明
     * 底层会先排序
     * 再获取最大和最小的元素
     */
    private static void colMinMax() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("c");
        strings.add("d");
        strings.add("e");
        String max = Collections.max(strings);
        String min = Collections.min(strings);


        System.out.println(max +"\n"+ min);
    }


    /**
     * 二分查找法
     * 传入键,返回所在位置的下标
     * 如果传入的值不存在,返回 -下标+1
     */
    private static void colBinaryser() {

        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("c");
        strings.add("d");
        strings.add("e");
        int b = Collections.binarySearch(strings, "b");
        int c = Collections.binarySearch(strings, "c");
        /**
         * 打印-2与1
         */
        System.out.println(b);
        System.out.println(c);
    }


    /**
     * Collections排序
     * 只要实现了comparable接口,就能使用collections的sort()方法进行排序
     *
     */

    private static void colsSort() {


        ArrayList<String> strings = new ArrayList<>();
        strings.add("zhangsa ");
        strings.add("lisi ");
        strings.add("wangwu ");
        strings.add("zhaoliu ");

        Collections.sort(strings);

        System.out.println(strings);
    }

}
