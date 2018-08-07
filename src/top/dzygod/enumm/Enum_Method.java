package top.dzygod.enumm;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/7 12:50
 * @Description: 枚举类常见方法
 */
public class Enum_Method {

    /**
     * int ordinal() 返回枚举常量的序数
     * int compareTo(E o)
     * String name()
     * String toString()
     * <T> T valueOf(Class<T> type,String name)
     * values()
     * 此方法虽然在JDK文档中查不到,但每个枚举类都具有该方法,它用来获取所有枚举项
     *
     * @param args
     */
    public static void main(String[] args){

        Week_ mon = Week_.MON;
        Week_ tus = Week_.TUS;

        //返回枚举常量的序列数
        int ordinal = mon.ordinal();
//        System.out.println(ordinal);

        //返回的是序列数比较后的结果
        int i = mon.compareTo(tus);
//        System.out.println(i);

        //返回当前枚举常量的名字()
        System.out.println(mon.name());

        //通过字节码,调用重写的toString()
        Week_ mon2 = Week_.valueOf(Week_.class,"MON");
        System.out.println(mon2);

        //获取所有枚举的一个数组
        Week_[] values = Week_.values();
        for (Week_ value : values) {
//            System.out.println(value);
        }
    }



}
