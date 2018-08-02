package top.dzygod.bean;

/**
 * @Author: dingziyuan
 * @Date: 2018-04-23 16:45
 * @Description: 类泛型,泛型方法和静态泛型方法的练习
 */
public class Student<Q> extends User {

    private Q name;

    public Student(String userName) {
        super(userName);
    }

    public Q getName() {
        return name;
    }

    /**
     * 方法中的返回类型最好与类的泛型一致
     * 如果不一致就可以用下边的方式,给方法指定泛型
     *
     * @return
     */
    public <T> void setName(T name) {
        System.out.println(name);
    }


    /**
     * 静态方法必须生成自己的泛型,
     * 因为类的泛型是实例化时候赋值的
     *
     * @param name
     * @param <Q>
     */
    public static <Q> void print(Q name) {
        System.out.println(name);
    }

}
