package top.dzygod.genericity;

import top.dzygod.bean.StudentPra;
import top.dzygod.bean.StudentPra2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @Author: dingziyuan
 * @Date: 2018/5/13 14:55
 * @Description: 泛型固定下边界与上边界
 */
public class GenericityPra {



    public static void main(String[] args) {
//        topBorder();
//        downBorder();
    }



    /**
     * addAll()方法的
     * public boolean addAll(Collection<? extends E> c)
     * ? extends E 固定上边界
     */
    private static void topBorder() {


        ArrayList<StudentPra> pras = new ArrayList<>();
        ArrayList<StudentPra2> prasSon = new ArrayList<>();


        pras.addAll(prasSon);
    }


    /**
     *  固定下边界
     */
    private static void downBorder() {

        TreeSet<StudentPra2> pras2 = new TreeSet<>(new CompareByTotleScore());

        pras2.add(new StudentPra2("张三",12,23,12));
        pras2.add(new StudentPra2("里斯",200,25,13));
        pras2.add(new StudentPra2("王五",12,23,1));


        System.out.println(pras2);
    }

}

/**
 * 固定下边界
 */
class CompareByTotleScore implements Comparator<StudentPra>{

    @Override
    public int compare(StudentPra o1, StudentPra o2) {
        int num = o1.getTotalScore().compareTo(o2.getTotalScore());
        return num;
    }
}
