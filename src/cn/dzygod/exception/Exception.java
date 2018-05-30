package cn.dzygod.exception;

/**
 * @Author: dingziyuan
 * @Date: 2018/5/14 10:46
 * @Description: *
 */
public class Exception {

    public static void main(String[] args) {
        /**
         * 异常的注意事项及如何使用异常处理
         * 异常处理事项
         *     a:子类重写父类方法时,子类必须抛出相同的异常或父类异常的子类
         *     b:如果父类抛出了多个异常,子类重写父类时,只能抛出相同的异常或是它的子集,子类不能抛出父类没有的异常
         *     c:如果被重写的方法没有异常抛出,那么子类的方法绝对不能抛出异常,只能捕获
         * 如何使用异常处理
         *      原则:如果该功能内部可以将问题处理,用try,如果处理不了,交给调用这处理,这时用throws
         *      区别:
         *          后续程序需要继续执行就用try
         *          不需要继续就用throws
         *      如果JDK没有提供相应的异常,需要自定义异常z
         *
         */
    }
}
