package top.dzygod.jdk8.practice.appendix;

/**
 * @Author: dingziyuan
 * @Date: 2018/10/8 10:07
 * @Description: 人类
 */
public class Person {

    private String name;
    private String age;
    private String gender;

    public Person(String name, String age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

