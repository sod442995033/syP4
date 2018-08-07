package top.dzygod.reflection;

/**
 * @Author: dingziyuan
 * @Date: 2018/8/4 10:41
 * @Description: *
 */
public class Person {

    private int age;
    private String name;
    private String gender;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    private void eat() {
        System.out.println("今天吃了一顿鲸鱼");
    }

    private void eat(int num) {
        System.out.println("今天吃了" + num + "顿金鱼");
    }

}
