package top.dzygod.list;

import java.util.LinkedList;

/**
 * @Author: dzyGod
 * @Date: 2018-04-10 12:22
 * @Description: 对LinkedList进行封装,用它的方法完成栈数据结构的模拟实现
 */
public class Stack {
    private LinkedList linkedList = new LinkedList();

    /**
     * 进栈
     */
    void in(Object obj) {
        linkedList.addLast(obj);
    }

    /**
     * 弹栈
     */
     Object out() {
        return linkedList.removeLast();
    }

    /**
     * 是否为空
     */
     boolean isEmpty(){
        return linkedList.isEmpty();
    }


}
