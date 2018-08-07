package top.dzygod.enumm;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/7 11:36
 * @Description: *
 */
public class Enum_Week {
    /**
     * 枚举类概述
     * 是指将变量的值一一列举出来,变量的值只限于列举出来的值的范围内.举例:一周有七天,一年只有12个月
     * 回想单例设计模式
     * 单例是一个类只有一个实例
     *
     * @param args
     */
    public static void main(String[] args){

        //第一种方法
        /*Week mon = Week.MON;
        System.out.println(mon.getWeekDay());*/

        //第二种方法
        Week2 mon = Week2.MON;
        mon.weekDay();
    }


}


//使用成员变量与有参构造实现枚举
class Week{

    private Week(String weekDay) {
        this.weekDay = weekDay;
    }

    private String weekDay;

    public static final Week MON = new Week("周一");
    public static final Week TUS = new Week("周二");
    public static final Week WEN = new Week("周三");

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }
}


//使用匿名内部类实现枚举
abstract class Week2{

    private Week2() {}

    public static final Week2 MON = new Week2(){
        @Override
        void weekDay() {
            System.out.println("星期一");
        }
    };
    public static final Week2 TUS = new Week2(){
        @Override
        void weekDay() {
            System.out.println("星期二");
        }
    };

    public static final Week2 WEN = new Week2(){
        @Override
        void weekDay() {
            System.out.println("星期三");
        }
    };

    abstract void weekDay();
}


