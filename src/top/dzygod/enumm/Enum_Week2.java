package top.dzygod.enumm;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/7 12:02
 * @Description: 枚举对之前的第一种实现
 */
public class Enum_Week2 {

/**
 * 注意事项:
 *  定义枚举要使用关键字enum
 *  所有枚举类都是Enum的子类
 *  枚举类中的第一行必须是枚举项,最后一个枚举项后的分号是可以省略的,但是如果枚举类有其他的东西,
 *  这个分号就不能省略,建议不要省略
 *  枚举类可以有构造器,但必须是private的,它默认的也是private的
 *  枚举类可以有抽象方法,但是枚举项必须重写该方法
 *  枚举可以在switch中使用
 * @param args
 */
 public static void main(String[] args){

     //第一个测试,有参构造与get方法
     /*Week_ mon = Week_.MON;
     System.out.println(mon.getWeekDay());*/

     //第二个测试,匿名内部类
     /*Week_1 mon = Week_1.MON;
     mon.getDayOfWeek();*/

     /*Week_ mon = Week_.MON;
     switch (mon) {
         case MON:
             System.out.println("周一");
             break;
         case TUS:
             System.out.println("周二");
             break;
         case WEN:
             System.out.println("周三");
             break;
         default:
             System.out.println("默认周一");
     }*/


 }

}


enum Week_{
    /**
     * 每一天
     */
    MON("周一"),TUS("周二"),WEN("周三");

    private String weekDay;

    Week_(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getWeekDay() {
        return weekDay;
    }

    @Override
    public String toString() {
        return weekDay;
    }
}

/**
 * 枚举对之前的第二种实现
 * 匿名内部类实现
 */
enum Week_1 {

    /**
     * 一周中的每一天
     */
    MON(){
        @Override
        void getDayOfWeek() {
            System.out.println("周一");
        }
    },
    TUS(){
        @Override
        void getDayOfWeek() {
            System.out.println("周二");
        }
    },
    WEN(){
        @Override
        void getDayOfWeek() {
            System.out.println("周三");
        }
    };

    abstract void getDayOfWeek();

}
