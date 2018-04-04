package cn.dzygod.bean;

//用户bean类
public class User {

    private String userName;
    private Integer age;
    private String remark;


    public User() {
    }

    public User(String userName, Integer age, String remark) {
        this.userName = userName;
        this.age = age;
        this.remark = remark;
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



}
