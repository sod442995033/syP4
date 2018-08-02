package top.dzygod.exception;


import java.io.*;
import java.lang.annotation.AnnotationTypeMismatchException;
import java.nio.BufferOverflowException;
import java.nio.charset.CharacterCodingException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: dingziyuan
 * @Date: 2018/5/14 10:46
 * @Description: 异常, 日志, 断言, 调试
 */
public class Exception {

    private static final String TEST_TXT = "aaaaaaaaaaaaaaaaaaaaa.txt";
    private static final Logger LOGGER = Logger.getLogger(Exception.class.getPackage().getName());


    public static void main(String[] args) throws Throwable {
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


//            test1();
//            test2();
//            test3();
//            test4();
//        System.out.println(test5(2));
//        test9(-1);
//        test11();
//        test12();
//        test13();
//        test14();


    }



    private static void test15() {

    }

    /**
     *  日志配置记录(配置文件)
     *
     *  默认情况下,配置文件在e/lib/logging.properties
     *  要想使用另一个配置文件,就要将java.util.logging.config.flie特性设置为配置文件的存储位置,
     *  并用下列命令启动程序
     *  -Djava.util.logging.config.flie=top.dzygod.exception.Exception;
     *
     * 要想修改默认的日志记录级别,就需要编辑配置文件,并修改以下命令行
     * .level=INFO
     * 可以添加以下内容来指定自己的日志记录级别
     * com.mycompany.myapp.level=FINE 也就是说,在日志记录名后边增加后缀.level
     *
     * 日志处理器并不把消息发送到控制台上,这是处理器的任务.
     * 另外,处理器也有级别.要想在控制台看到FINE级别的消息,需要进行以下配置
     * java.util.logging.ConsoleHandler.level=FINE
     */



    /**
     * 日志配置记录
     * 在虚拟机对执行过程优化过之后,无法准确得到一些类的调用信息,
     * 需要使用logp方法获得调用类和方法的确切位置
     *
     * @methodSignature: void logp(Level l,String className,String methName,String message)
     * @methodSignature: void entering(String className,String methName)
     * @return
     */
    private static Integer test14() {

        String name = Exception.class.getName();
        String methName = "test14";

        try (
                FileInputStream inputStream = new FileInputStream(TEST_TXT);
        ) {
            int read = inputStream.read();
        } catch (FileNotFoundException e) {
//            LOGGER.throwing(Exception.class.getName(),"test14",e);
//            LOGGER.setLevel(Level.FINER);
//            LOGGER.log(Level.FINER, "你给的文件现在找不到了!!");
//            LOGGER.fine("你给的文件现在找不到了!!");

//获得调用类的方法的确切位置
            LOGGER.logp(Level.FINER,name,methName,"你给的文件现在找不到了!");
//用来跟踪执行流的方法
            LOGGER.entering(name, methName,new Object[]{TEST_TXT});

            LOGGER.exiting(name, methName);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 记录日志
     * 记录日志的常见用途是记录那些不可预料的异常.
     * 可以使用下面的两个方法提供日志记录中包含的异常描述内容
     * 使用throwing()可以记录一条FINER级别的记录与一条THROW级别的记录
     *
     * throwing()
     * log()
     */
    private static void test13() throws IOException {

        if (1 < 2) {
            IOException exception = new IOException("出了一些IO异常");
            LOGGER.throwing(Exception.class.getName(),"test13",exception);
            throw exception;
        }

    }

    /**
     * 高级日志
     * 与包名相比,日志记录器的层次性更强.
     * 对于包来说,一个包的名字与其父包的名字之间没有语义关系
     * 但是日志记录器的父与子之间将共享某些属性.
     * <p>
     * 日志记录器有7个级别 (严重程度降序排列)
     * SEVERE       严重
     * WARNING      警告
     * INFO         信息
     * CONFIG       配置
     * FINE         良好
     * FINER        较好
     * FINEST       最好
     * 默认情况下,只记录前三个级别.也可以设置其他级别.
     * <p>
     * 如果将记录级别设置为INFO或更低,则需要修改日志处理器的配置.
     * 默认的日志处理器不会处理低于INFO级别的信息.
     */
    private static void test12() {
        //如果对top.dzygod日志记录器设置了级别,他的子记录器也会继承这个级别
        Logger logger = Logger.getLogger("top.dzygod.exception");

        //使用实例logger.setLevel(Level.FINE)方法进行设置
//        logger.setLevel(Level.FINE);

        //还可以使用level.ALL开启所有记录,或者使用Level.OFF关闭所有级别的记录
//        logger.setLevel(Level.ALL);
//        logger.setLevel(Level.OFF);

        //对于所有级别有以下几种记录方法,
//        logger.warning("出错了!");
//        logger.fine("没有问题了!");

        //同时,还可以使用log方法指定级别
        logger.log(Level.FINE, "没有问题了!");


    }

    /**
     * 基本日志记录
     * 自动包含了,时间,调用的类名与方法名
     * Logger.getGlobal().setLevel(Level.OFF);
     * 在日志生成之前使用这个方法,可以使日志全局无效
     */
    private static void test11() {

        //在修正bug 7184195之前,还需要调用Logger.getGlobal().setLevel(Level.INFO);
        // 来激活全局日志记录器
        Logger.getGlobal().setLevel(Level.OFF);
        Logger.getGlobal().info("发生了一些错误");

    }

    /**
     * 在java语言中,给出了三种处理系统错误的机制
     * 抛出一个异常
     * 日志
     * 使用断言
     * <p>
     * 在方法中操作断言
     * 使用类加载器操作断言的方式
     */
    private static void test10() {

        //对于通过类加载器加载的所有类来说,如果没有显式的说明类或包的断言状态,就启用或禁用断言
        ClassLoader loader = Exception.class.getClassLoader();
        loader.setDefaultAssertionStatus(Boolean.TRUE);

        //对于给定的类和他的内部类,启用或禁用断言
        loader.setClassAssertionStatus(Exception.class.getName(), Boolean.TRUE);

        //对于给定包和其子包中的所有类,启用或禁用断言
        String packageName = "";
        loader.setPackageAssertionStatus(packageName, Boolean.TRUE);

        //移去所有类和包的显式断言设置,并禁用所有通过这个类加载器加载的类的断言
        loader.clearAssertionStatus();
    }


    /**
     * 断言
     * 断言会对表达式进行检测,如果结果为false,则抛出一个AsserttionError异常
     * 第二种表达式会将参数传进AsserttionError的有参构造中,转换成一个消息字符串
     * 表达式的唯一目的就是产生一个消息字符串,不存储表达式的值.因此,不可能在以后得到它
     * <p>
     * 默认情况下,断言被禁用,
     * 可以在运行程序时指定
     * -enableassertions或-ea选项来启用它
     * -disableassertions或-da选项来禁用某个类或包下的断言
     * * java -ea MyApp
     * 在启用或禁用断言时不会重新编译程序,启用或禁用断言是类加载器的功能.
     * 禁用之后类加载器就会跳过断言代码
     * 也可以开启某个类或某个包下所有类的断言
     * <p>
     * 注:
     * 有些类直接由jvm加载,不由类加载器加载,这些类可以使用开闭指令,开启或关闭指定的类或包.
     * 但是不能使用启用或禁用所有类的,-ea或-da指令操作那些不使用类加载器加载的系统类
     * 对于这些系统类来说,需要使用-enablesystemassertions/-esa开关启动断言
     *
     * @param s
     * @throws FileNotFoundException
     */
    private static void test9(Integer s) throws FileNotFoundException {
        /*
            assert表达式的两种写法
                1.assert 条件
                2.assert 条件 : 表达式
         */
        assert s > 0;
//        assert s >0 : s;
    }

    /**
     * 被抑制的异常如何处理
     * 在try块中出现异常会先执行finally后再向调用调用者抛出,而finally块中如果有异常的话
     * 就会将try块中的异常覆盖(抑制),抛出finally块中的异常.
     * JDK7之前一般选择抛出try块中的原始异常,忽略在finally中的异常,
     * 这么做的出发点是try块中的出发点是:try块中的异常是根源
     * <p>
     * JDK7之后,Throwable类增加addSuppressed()方法
     * 可以在添加被抑制异常记录,被抑制的异常会出现在抛出的异常的堆栈信息中,
     * 也可以通过getSuppressed方法来获取这些异常。
     */
    private static void test8() throws IOException {

        FileInputStream inputStream = null;
        RuntimeException exception = null;

        try {
            inputStream = new FileInputStream(TEST_TXT);
            int read = inputStream.read();
        } catch (FileNotFoundException e) {
            exception = new RuntimeException();
            exception.initCause(e);
            throw exception;
        } catch (IOException e) {
            exception = new RuntimeException();
            exception.initCause(e);
            throw exception;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.addSuppressed(exception);
                //得到这个异常的所有抑制异常
                Throwable[] suppressed = e.getSuppressed();
                throw e;
            }
        }
    }


    /**
     * 堆栈跟踪
     * e.printStackTrace();访问堆栈跟踪文本描述信息
     * e.getStackTrace();获取StackTraceElement[]数组,可以在程序中分析这个数组
     */
    private static void test7() {
        try (
                FileInputStream inputStream = new FileInputStream(TEST_TXT);
        ) {
            int read = inputStream.read();
        } catch (IOException e) {

//            访问堆栈跟踪文本描述信息
//            e.printStackTrace();
//            e.printStackTrace();

//            获取StackTraceElement[]数组,可以在程序中分析这个数组
//            e.getStackTrace();

            /*
            StackTraceElement[] trace = e.getStackTrace();
            for (StackTraceElement element : trace) {
                String name = element.getFileName();
                int number = element.getLineNumber();
            }
            */

            //Thread.getAllStackTraces()可以产生所有线程堆栈跟踪信息
            Map<Thread, StackTraceElement[]> traces = Thread.getAllStackTraces();
            for (Thread t : traces.keySet()) {
                StackTraceElement[] elements = traces.get(t);
                for (StackTraceElement element : elements) {
                    String name = element.getFileName();
                    int number = element.getLineNumber();
                }
            }
        }
    }


    /**
     * 如果异常出现在finally块中,
     * 就会变得非常难处理,比如在finally块中关闭输入流,这个操作本身也可能抛出异常,就需要再次进行捕捉
     * JDK7中有了更简单的处理方式
     * 这个块正常退出或存在异常时,都会去执行这个实例的close()方法
     * 手动逻辑需要两个嵌套的try catch
     */
    private static void test6() {
        try (
                FileInputStream inputStream = new FileInputStream(TEST_TXT);
        ) {
            int read = inputStream.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 异常中的return与finally的优先级
     * 假设使用return语句从try块中退出,在方法返回前finally子句先执行
     * 如果finally子句中也有一个return语句,这个返回值将会覆盖原始的返回值
     */
    private static Integer test5(Integer num) {
        try {
            FileInputStream inputStream = new FileInputStream(TEST_TXT);
            int read = inputStream.read();
            return num;
        } catch (RuntimeException e) {
            return num + 1;
        } finally {
            return num + 1;
        }
    }


    /**
     * finally块的作用,在try块中捕捉到异常后会退出方法,
     * 如果有些方法获得了本地资源,并且只有这个方法自己知道,又如果这些方法在退出之前必须被回收
     * 那么就会产生资源回收问题.
     * <p>
     * 使用finally可以有效解决例如数据库连接等异常问题
     * 始终都会关闭事务
     */
    private static void test4() throws IOException {

        FileInputStream inputStream = null;
        /*
             内侧的try,是确认关闭输入流
             外侧的try是为了确保出现的错误
             这种设计方式不仅清楚,而且还会报告finally子句中出现的错误
         */
        try {
            try {
                inputStream = new FileInputStream(new File(TEST_TXT));
                int read = inputStream.read();
            } finally {
                //流始终都会关闭
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    /**
     * try块中仅有的已检查异常,
     * 在catch块中如果实例没有变化,throws抛出相同异常实例就是合法的
     */
    private static void test3() throws Throwable {
        try {
            FileInputStream inputStream = new FileInputStream(new File(TEST_TXT));
        } catch (FileNotFoundException e) {
            throw new CharacterCodingException().initCause(e);
        }
    }


    /**
     * 在catch重新抛出新的异常实例,可以改变异常的类型
     * 适用于不追究细节原因,只想知道是否报错
     * <p>
     * 推荐使用:
     * initCause()方法进行包装,将此throwable的 原因初始化为指定值。
     * 这样就可以使用指定实例的getCause()方法,来获取原始异常,不会丢失细节问题
     */
    private static void test2() {


        try {
            Integer[] integers = new Integer[10];
            System.out.println(integers[11]);
        } catch (java.lang.Exception e) {
            //打印的是原始异常信息
            RuntimeException se = new RuntimeException();
            se.initCause(e);
            throw se;
        }
    }

    /**
     * jdk7的新特性可以捕获多个异常类型
     * 只有捕获的异常之间,没有父子级关系时才可以使用
     * 生成的字节码只包含一个对应公共catch子句的代码块
     * <p>
     * RuntimeException与Error的子类为未受检异常
     * IOException与其他异常为受检异常
     */
    private static void test1() {

        //可以捕捉多个未受检异常(编译可以通过,即说明未受jvm检查)
        try {
            Integer[] integers = new Integer[10];
            System.out.println(integers[11]);
        } catch (AnnotationTypeMismatchException | ArithmeticException |
                BufferOverflowException e) {
            e.printStackTrace();
        }


    }

}
