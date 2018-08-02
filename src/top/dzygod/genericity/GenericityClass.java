package top.dzygod.genericity;

/**
 * @Author: dingziyuan
 * @Date: 2018/7/19 15:35
 * @Description: 泛型类:泛型的重新理解, 泛型类可以看做普通类的工厂
 */
public class GenericityClass<T, S, U, P> {
    /**
     * 泛型的程序设计,划分为三个能力级别
     * 1.基本级别是,仅仅使用泛型类.典型的是想ArrayList这样的集合,不必考虑他们的工作方式和原因
     */
    private T a;
    private S b;
    private U c;
    private P d;

    public GenericityClass(T a, S b) {
        this.a = a;
        this.b = b;
    }

    public GenericityClass(T a, S b, U c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public GenericityClass(T a, S b, U c, P d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public T getA() {
        return a;
    }

    public S getB() {
        return b;
    }

    public U getC() {
        return c;
    }

    public P getD() {
        return d;
    }

    public static void main(String[] args) {

/*
        GenericityClass<String, String, String, String> pra1 = new GenericityClass<>
                ("张三", "里斯", "王二", "赵四");

        System.out.println(pra1.getA());
        System.out.println(pra1.getB());
        System.out.println(pra1.getC());
        System.out.println(pra1.getD());
*/

        Integer[] integers = {11, 99, 33, 11, 55, 22, 87};
        GenericityClass<Integer, Integer, Integer, Integer> arrs = getMinMax(integers);

//        System.out.println(arrs.getA());
//        System.out.println(arrs.getB());
    }


    /**
     * 算出最大数与最小数,生成练习实例
     * @param integers
     * @return
     */
    private static GenericityClass<Integer, Integer, Integer, Integer>
    getMinMax(Integer[] integers) {


        if (integers == null || integers.length == 0) {
            throw new RuntimeException("参数未传!!!");
        }

        Integer max = integers[0];
        Integer min = integers[0];


        for (int i = 0; i < integers.length; i++) {
            if (integers[i].compareTo(max) > 0) {
                max = integers[i];
            }
            if (integers[i].compareTo(min) < 0) {
                min = integers[i];
            }
        }

        return new GenericityClass<>(min, max);
    }


}
