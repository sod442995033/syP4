package top.dzygod.set;

import top.dzygod.bean.User;

import java.util.*;

/**
 * @Author: dingziyuan
 * @Date: 2018-04-24 11:22
 * @Description: 看类名
 */
public class HashSetTest {

    public static void main(String[] args) {
//        rudiments();
//        LinkedHashSet<String> s = linkedHash();
//        System.out.println(s);
//        practice();
//        noRepeat();
//        toReapt();
//        treeSetB();
//        binarySet();
//        sortString();
//        sortByLengeh();


    }


    /**
     * 根据字符串长度进行排序
     */
    private static void sortByLengeh() {

        TreeSet<User> strings = new TreeSet();

        strings.add(new User("张三"));
        strings.add(new User("张三迷哦哎"));
        strings.add(new User("张三科"));

        System.out.println(strings);
    }


    /**
     * TreeSet按照姓名去排序
     *  根据Unicode码表数值大小进行排序
     */
    private static void sortString() {

        TreeSet<String> strings = new TreeSet<>();

        strings.add("张三");
        strings.add("王五");
        strings.add("李四");

        /*
            24352
            26446
            29579
         */
        System.out.println('张' + 0);
        System.out.println('李' + 0);
        System.out.println('王' + 0);

        System.out.println(strings);
    }


    /**
     * TreeSet实现去重复,需要bean类实现Comparable接口,重写comparaTo方法
     * 保证元素唯一和自然排序的原理:二叉树排序
     * 返回值为0,不存
     * 返回值为正书,正序
     * 返回值为负数,倒序
     */
    private static void binarySet() {

        TreeSet<User> users = new TreeSet<>();
        users.add(new User("张三",10));
        users.add(new User("张三",11));
        users.add(new User("张三",22));
        users.add(new User("李四",22));
        users.add(new User("王伟",24));

        System.out.println(users);
    }


    /**
     * TreeSet集合
     * 可以对元素进行排序
     */
    private static void treeSetB() {

        TreeSet<String> strings = new TreeSet<>();
        strings.add("2");
        strings.add("1");
        strings.add("1");
        strings.add("1");
        strings.add("3");
        System.out.println(strings);
    }

    /**
     * 去除list集合中的重复元素
     * 在User这个bean中重写了hashcode和equals方法
     */
    private static void toReapt() {

        ArrayList<User> strings = new ArrayList<>();

        LinkedHashSet<User> users = new LinkedHashSet<>();
        strings.add(new User("张三"));
        strings.add(new User("张三"));
        strings.add(new User("李四"));
        strings.add(new User("李四"));
        strings.add(new User("王伟"));
        strings.add(new User("麻溜"));

        users.addAll(strings);
        System.out.println(users);
    }


    /**
     * 使用scanner获取键盘录入,去掉重复字符,打印出不同的字符
     */
    private static void noRepeat() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入需要去重的字符:");
        String s = scanner.nextLine();
        char[] chars = s.toCharArray();
        HashSet<Character> characters = new HashSet<>();

        for (char c : chars) {
            characters.add(c);
        }

        System.out.println(characters);
    }

    /**
     *  10个1-20的不重复随机数,输出到控制台
     */
    private static void practice() {

        HashSet<Integer> inte = new HashSet<>();
        Random r = new Random();

        while(inte.size() <10){
            //r.nextInt(20)会得到0-19内的随机数,想要获取1-20的随机数还需要+1
        int i = r.nextInt(20);
            inte.add(i+1);
        }

        System.out.println(inte);
    }


    /**
     * LinkedHashSet是Set集合中唯一的有序集合,存取一致
     */
    private static LinkedHashSet<String> linkedHash() {

        LinkedHashSet<String> s = new LinkedHashSet<>();
        s.add("张三");
        s.add("张三");
        s.add("张三");
        s.add("李四");
        s.add("王五");
        s.add("赵磊");
        return s;
    }

    private static void rudiments() {
        /**
         *  set集合 :无索引,不可重复,无序(存取不一致)
         */
        HashSet<String> set = new HashSet();

        boolean a = set.add("张三");
        boolean b = set.add("张三");

        set.add("里斯");
        set.add("王五");

        System.out.println(set);
        //true
        System.out.println(a);
        //false(不可重复)
        System.out.println(b);

        for (String e : set) {
            System.out.println(e);
        }
    }


}
