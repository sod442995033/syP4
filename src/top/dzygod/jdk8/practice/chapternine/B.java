package top.dzygod.jdk8.practice.chapternine;


/**
 * @Author: dingziyuan
 * @Date: 2018/10/3 9:48
 * @Description: 相似的方法签名也是无法成功编译的,需要重写
 */
public interface B{
    default Number getNumber() {
        return 12;
    }
}
