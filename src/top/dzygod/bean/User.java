package top.dzygod.bean;

import java.util.Objects;

/**
 * 用户bean类
 *
 */
public class User implements Comparable<User>{

    private String userName;
    private Integer age = 0;
    private String remark;


    public User() {
    }

    public User(String userName, Integer age, String remark) {
        this.userName = userName;
        this.age = age;
        this.remark = remark;
    }

    public User(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", remark='" + remark + '\'' +
                '}';
    }

    /**
     * 重写equals()方法必须重写hashCode()方法,
     * 因为hash散列判断数据类实例是否相等
     * 先进行hashcode比较
     * 比较相同后才会进行equals判断
     * 例如:HashSet类中的add()方法
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(age, user.age) &&
                Objects.equals(remark, user.remark);
    }

    /**
     * 1.HashSet原理:
     *      我们使用Set集合都是要去掉重复元素的,如果在存储的时候逐个equals()比较,效率较低
     *      Hash算法提高了去重复的效率,降低了使用equals()方法的次数
     *      当HashSet调用add()方法存储对象的时候,先调用对象的hashCode()方法得到一个哈希值,然后在集合中查找是否有哈希值相同的对象
     *          如果没有哈希值相同的对象就直接存入集合
     *          如果有哈希值相同的对象,就和哈希值相对的对象逐个进行equals()比较,比较结果为false就存入,true则不存
     * 2.将自定义类的对象存入HashSet去重复
     *      类中必须重写HashCode()和equals()方法
     *      hashCode(): 属性相同的对象返回的值必须相同,属性不同的返回值尽量不同(提高效率)
     *      equals():   属性相同返回true,不同返回false
     *
     * @return
     */
    @Override
    public int hashCode() {

        return Objects.hash(userName, age, remark);
    }


    /**
     * 按照姓名长度排序
     * @param o
     * @return
     */
    @Override
    public int compareTo(User o) {

        //主要条件:姓名长度
        int length = this.getUserName().length() - o.getUserName().length();
        //次要条件:姓名大小
        int num = length == 0?this.getUserName().compareTo(o.getUserName()):length;
        //次要条件:年龄大小
        return num == 0 ? this.getAge() - o.getAge():num;
    }


    /**
     * 按照姓名(Unicode值)排序
     * @param o
     * @return
     */
/*
    @Override
    public int compareTo(User o) {
        //主要条件
        int num = this.userName.compareTo(o.getUserName());
        //次要条件
        return num == 0 ? this.age - o.getAge() : num;
    }
*/


    /**
     * 按照年龄排序
     * @param o
     * @return
     */
/*
    @Override
    public int compareTo(User o) {
        //主要条件
        int num = this.age - o.age;
        //次要条件
        return num == 0 ?this.userName.compareTo(o.getUserName()):num;
    }
*/




}
