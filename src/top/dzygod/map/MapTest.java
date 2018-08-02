package top.dzygod.map;

import top.dzygod.bean.StudentPra;

import java.util.*;

/**
 * @Author: dingziyuan
 * @Date: 2018/5/9 17:01
 * @Description: *
 */
public class MapTest {

    public static void main(String[] args) {
//         mapPut();
//        mapDelete();
//        isTrue();
//        havaMap();
//        traversalOne();
//        traversalTwo();
//        seleRepeat();
//        nestTraverse();
//        hashMapHashTable();



    }




    /**
     * 区别
     */
    private static void hashMapHashTable() {


        Map<Integer, String> map = new HashMap();
        Map<Integer, String> mapTa = new Hashtable<Integer, String>();

        map.put(null, "张三");
        map.put(2, null);

        /*mapTa.put(null,"张三");
        mapTa.put(2,null);*/

        System.out.println(map + "\n" + mapTa);
    }


    /**
     * hashMap嵌套遍历
     */

    private static void nestTraverse() {

        /**
         *  一班
         */
        HashMap<StudentPra, String> map1 = new HashMap();
        map1.put(new StudentPra("张三", 100, 20, 10), "张三");
        map1.put(new StudentPra("里斯", 78, 20, 10), "里斯");
        map1.put(new StudentPra("王五", 12, 20, 10), "王五");
        map1.put(new StudentPra("赵六", 34, 20, 10), "赵六");


        /**
         * 二班
         */
        HashMap<StudentPra, String> map2 = new HashMap<>();
        map2.put(new StudentPra("马龙", 100, 20, 10), "马龙");
        map2.put(new StudentPra("张继科", 78, 20, 10), "张继科");
        map2.put(new StudentPra("许昕", 12, 20, 10), "徐昕");


        HashMap<HashMap<StudentPra, String>, String> grade1 = new HashMap<>();
        HashMap<HashMap<StudentPra, String>, String> grade2 = new HashMap<>();


        grade1.put(map1, "一年级");
        grade2.put(map2, "二年级");

        for (HashMap<StudentPra, String> map : grade2.keySet()) {
            String grade = grade2.get(map);
            for (StudentPra pra : map.keySet()) {
                String name = map2.get(pra);
                /**
                 * 输出:
                 * 二年级徐昕
                 * 二年级张继科
                 * 二年级马龙
                 */
                System.out.println(grade + "" + name);
            }
        }
    }

    /**
     * 查询字符串中有多少重复字符
     * 双列集合
     * 用值加一,因为键是不会变的
     */
    private static void seleRepeat() {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        String nums = "12312423432213142142131";
        char[] chars = nums.toCharArray();
        for (Character c : chars) {
            map.put(c, !map.containsKey(c) ? 1 : map.get(c) + 1);
        }


        System.out.println(map);
    }

    /**
     * 双列集合的第一种遍历方式
     * 2.封装到实体对象中get获取
     */
    private static void traversalTwo() {
        Map<String, Integer> map = new HashMap();
        Integer val = map.put("张三", 12);
        Integer val1 = map.put("李四", 13);
        Integer val2 = map.put("里斯", 13);
        Integer val3 = map.put("王二", 145);

        //Map.Entry说明entry是Map接口的内部接口,将键值封装在Entry对象,存储在set集合中
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry e : entries) {
            System.out.println(e.getKey());
            System.out.println(e.getValue());
        }
    }

    /**
     * 双列集合的第一种遍历方式
     * 1.转换成set集合进行遍历
     */
    private static void traversalOne() {
        Map<String, Integer> map = new HashMap();
        Integer val = map.put("张三", 12);
        Integer val1 = map.put("李四", 13);
        Integer val2 = map.put("里斯", 13);
        Integer val3 = map.put("王二", 145);


        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 获取功能
     */
    private static void havaMap() {
        Map<String, Integer> map = new HashMap();
        Integer val = map.put("张三", 12);
        Integer val1 = map.put("李四", 13);
        Integer val2 = map.put("里斯", 13);
        Integer val3 = map.put("王二", 145);

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Integer in = map.get("张三");
        Set<String> strings = map.keySet();
        Collection<Integer> values = map.values();


        /*
            输出:
            [李四=13, 里斯=13, 张三=12, 王二=145]
            12
            [李四, 里斯, 张三, 王二]
            [13, 13, 12, 145]
         */
        System.out.println(entries + "\n" + in + "\n" + strings + "\n" + values);
    }


    /**
     * 判断功能
     */
    private static void isTrue() {
        Map<String, Integer> map = new HashMap();
        Integer val = map.put("张三", 12);
        Integer val1 = map.put("李四", 13);
        Integer val2 = map.put("里斯", 13);
        Integer val3 = map.put("王二", 145);

        boolean bol = map.containsKey("张三");
        boolean bol1 = map.containsValue("张");
        boolean isEmpty = map.isEmpty();
        /**
         * 返回true,false,false
         */
        System.out.println(bol + "" + bol1 + isEmpty);
    }


    /**
     * 删除功能
     */
    private static void mapDelete() {
        Map<String, Integer> map = new HashMap();
        Integer val = map.put("张三", 12);
        Integer val1 = map.put("李四", 13);
        Integer val2 = map.put("里斯", 13);
        Integer val3 = map.put("王二", 145);

        Integer newMap = map.remove("张三");

        /**
         *
         * 输出12,remove()返回的是被删除元素的value
         {李四=13, 里斯=13, 王二=145}
         */
        System.out.println(newMap + "\n" + map);
    }


    /*
      1.添加功能
   */
    private static void mapPut() {

        Map<String, Integer> map = new HashMap<>();
        Integer val = map.put("张三", 12);
        Integer val1 = map.put("张三", 13);
        Integer val2 = map.put("里斯", 13);
        Integer val3 = map.put("里斯", 145);
        /**
         * 输出null,12,null,13
         * map的put()方法会返回被覆盖Value的值.
         * 第一次没有被覆盖的即为null
         *  key值唯一
         *
         */
        System.out.println(val + "," + val1 + "," + val2 + "," + val3);
    }

}
