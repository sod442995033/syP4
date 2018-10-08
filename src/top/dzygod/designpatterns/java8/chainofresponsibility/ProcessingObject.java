package top.dzygod.designpatterns.java8.chainofresponsibility;

/**
 * 责任链模式是一种创建处理对象序列（比如操作序列）的通用方案
 *
 * @Author: dingziyuan
 * @Date: 2018/9/8 15:07
 * @Description:
 *
 */
public abstract class ProcessingObject<T> {
    protected ProcessingObject<T> successor;

    public void setSuccessor(ProcessingObject<T> successor){
        this.successor = successor;
    }


    public T handle(T input){
        T r = handleWork(input);
        if(successor != null){
            return successor.handle(r);
        }
        return r;
    }

    abstract protected T handleWork(T input);
}
