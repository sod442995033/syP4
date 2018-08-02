package top.dzygod.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * @Author: dingziyuan
 * @Date: 2018/5/12 17:43
 * @Description: 模拟斗地主
 */
public class PokerGame {
    public static void main(String[] args) {
//        noSort();
//        sort();


    }



    /**
     *  双列集合实现
     *
     *  1.获取扑克
     *  2.洗牌
     *  3.发牌
     *  4.看牌
     */
    private static void sort() {
        String[] num = {"3","4","5","6","7","8","9","10","J","Q","K","A","2"};
        String[] color = {"红桃","黑桃","方片","梅花"};
        //存储索引和扑克牌
        HashMap<Integer, String> map = new HashMap<>();
        //存储扑克牌
        ArrayList<Integer> indexs= new ArrayList<>();
        //密码
        int index = 0;


        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < color.length; j++) {
                map.put(index,color[j].concat(num[i]));
                indexs.add(index);
                index++;
            }
        }


        indexs.add(index);
        map.put(index,"小王");
        index++;
        map.put(index,"大王");
        indexs.add(index);

        /**
         * 洗牌
         */

        Collections.shuffle(indexs);

        TreeSet<Integer> zhangsan = new TreeSet<>();
        TreeSet<Integer> lisi = new TreeSet<>();
        TreeSet<Integer> wanger = new TreeSet<>();
        TreeSet<Integer> dipai = new TreeSet<>();

        /**
         * 发牌
         */
        for (Integer integer : indexs) {
           if(integer >= map.size()-3){
               dipai.add(indexs.get(integer));
           }else if(integer % 3 == 0){
                zhangsan.add(indexs.get(integer));
            }else if(integer % 3 == 1){
                lisi.add(indexs.get(integer));
            }else {
                wanger.add(indexs.get(integer));
            }
        }


        /**
         * 看牌
         */
        lookPokers(zhangsan,map,"张三");
        lookPokers(lisi,map,"李四");
        lookPokers(wanger,map,"王二");
        lookPokers(dipai,map,"底牌");
    }


    private static void lookPokers(TreeSet<Integer> pokerIndex,HashMap<Integer, String> pokers,String name){
        System.out.print(name+":");
        for (Integer index : pokerIndex) {
            System.out.print(pokers.get(index)+" ");
        }
        System.out.println();
    }

    /**
     * 1.买一副扑克
     * 两个数组
     * 一个用来保存花色
     * 一个用来保存数字
     */
    private static void noSort() {

        String[] color = {"方片","红桃","黑桃","梅花"};
        String[] num = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        ArrayList<String> pokers = new ArrayList<>();

        for (String c : color) {
            for (String n : num) {
                    pokers.add(c.concat(n));
            }
        }
        pokers.add("大王");
        pokers.add("小王");


        /**
         * 洗牌
         */
        Collections.shuffle(pokers);


        /**
         * 发牌
         */
        ArrayList<String> zhangsan = new ArrayList<>();
        ArrayList<String> lisi = new ArrayList<>();
        ArrayList<String> wanger = new ArrayList<>();
        ArrayList<String> dipai = new ArrayList<>();


        for (int i = 0; i < pokers.size(); i++) {
            if (i>=pokers.size()-3){
                 dipai.add(pokers.get(i));
            }else if(i%3==0){
                zhangsan.add(pokers.get(i));
            }else if(i%3==1){
                lisi.add(pokers.get(i));
            }else{
                wanger.add(pokers.get(i));
            }
        }

        /**
         * 看牌
         */
        System.out.println("张三:"+zhangsan +"\n李四:"+lisi +"\n王二:"+ wanger+"\n底牌:"+dipai);
    }

}
