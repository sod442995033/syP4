package top.dzygod.jdk8.practice.chapterten;

/**
 * @Author: dingziyuan
 * @Date: 2018/10/4 15:45
 * @Description: 第十章总结
 */
public class Note {
    /**
     * 这一章中，你学到了以下的内容。
     *  null 引用在历史上被引入到程序设计语言中，目的是为了表示变量值的缺失。
     *  Java 8中引入了一个新的类 java.util.Optional<T> ，对存在或缺失的变量值进行
     * 建模。
     *  你可以使用静态工厂方法 Optional.empty 、 Optional.of 以及 Optional.ofNull-
     * able 创建 Optional 对象。
     *  Optional 类支持多种方法，比如 map 、 flatMap 、 filter ，它们在概念上与 Stream 类
     * 中对应的方法十分相似。
     *  使用 Optional 会迫使你更积极地解引用 Optional 对象，以应对变量值缺失的问题，最
     * 终，你能更有效地防止代码中出现不期而至的空指针异常。
     *  使用 Optional 能帮助你设计更好的API，用户只需要阅读方法签名，就能了解该方法是
     * 否接受一个 Optional 类型的值。
     */
}
