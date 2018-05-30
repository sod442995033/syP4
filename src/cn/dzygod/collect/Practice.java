package cn.dzygod.collect;

import cn.dzygod.bean.StudentPra;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @Author: dingziyuan
 * @Date: 2018/5/8 9:03
 * @Description: 集合框架练习
 */
public class Practice {


    public static void main(String[] args) {
//            praSort();
//            charsSort();
//            reverseSort();
//              objSort();


    }


    /**
     * 键盘录入学生姓名及成绩,根据总成绩排序打印学生信息
     */
    private static void objSort() {

        Scanner scanner = new Scanner(System.in);

        TreeSet<StudentPra> pras = new TreeSet<>(new Comparator<StudentPra>() {
            @Override
            public int compare(StudentPra s1, StudentPra s2) {
                int num = s1.getTotalScore().compareTo(s2.getTotalScore());
                return num == 0?1:num;
            }
        });


        /**
         * nextline()方法读取一整行数据,包括\n但是不会返回\n
         * 而nextInt()则不会读取\n,所以要多加一个nextLine()方法读取\n的下一行(也就是真实数据)
         */
        for (int i = 0;;i++){
           System.out.println("请输入学生姓名:");
           if(i > 0){
             String name = scanner.nextLine();
           }
           String name = scanner.nextLine();
           System.out.println("请输入学生数学成绩:");
           Integer math = scanner.nextInt();
           System.out.println("请输入学生语文成绩:");
           Integer chinese = scanner.nextInt();
           System.out.println("请输入学生体育成绩:");
           Integer sport = scanner.nextInt();

           pras.add(new StudentPra(name,math,chinese,sport));

            if(pras.size()==5){
                break;
            }
       }

        for (StudentPra s : pras) {
            System.out.println(s);
        }
    }


    /**
     * 倒序排列
     * 1.创建Scanner对象
     * 2.创建倒叙排列可重复的TreeSet对象
     * 3.无限循环,将键盘录入的数值加入集合中
     * 4.遍历输出集合中的数据
     */
    private static void reverseSort() {
        Scanner scanner = new Scanner(System.in);

        TreeSet<Integer> integers = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer c1, Integer c2) {
                int num = c2.compareTo(c1);
                return num == 0 ? 1 : num;
            }
        });

        for (; ; ) {
            String str = scanner.nextLine();
            if (str.equals("quit")) {
                break;
            }
            Integer integer = Integer.valueOf(str);
            integers.add(integer);
        }

        System.out.println(integers);
    }


    /**
     * 对输入字符串进行排序
     */
    private static void charsSort() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入字符串:");
        String s = scanner.nextLine();
        char[] chars = s.toCharArray();

        TreeSet<Character> characters = new TreeSet<>(new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                int num = c1.compareTo(c2);
                return num == 0 ? 1 : num;
            }
        });

        for (Character c : chars) {
            characters.add(c);
        }

        System.out.println("排序后的字符串:");
        for (char c : characters) {
            System.out.print(c);
        }
    }


    /**
     * 对无序字符串进行排序,并且不会删除相同字符串
     */
    private void praSort() {
        ArrayList<String> arrs = new ArrayList<>();
        arrs.add("张三");
        arrs.add("里斯");
        arrs.add("张三");
        arrs.add("里斯");
        arrs.add("王二");
        arrs.add("旺热");
        arrs.add("解放螺丝钉");
        arrs.add("螺丝钉");
        arrs.add("解放");
        ArrayList<String> sort = sort(arrs);
        for (String s : sort) {
            System.out.println(s);
        }
    }


    private ArrayList<String> sort(ArrayList arrs) {

        TreeSet<String> sets = new TreeSet<>(new Comparator<String>() {

            @Override
            public int compare(String s1, String s2) {
                int num = s1.compareTo(s2);

                return num == 0 ? 1 : num;
            }
        });

        sets.addAll(arrs);

        arrs.clear();

        arrs.addAll(sets);
        return arrs;
    }


}
