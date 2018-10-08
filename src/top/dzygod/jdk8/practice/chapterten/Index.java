package top.dzygod.jdk8.practice.chapterten;

import java.util.PropertyPermission;

/**
 * @Author: dingziyuan
 * @Date: 2018/10/3 10:49
 * @Description:
 *      第十章 用 Optional 取代 null
 *      本章内容
 *           null 引用引发的问题，以及为什么要避免 null 引用
 *           从 null 到 Optional ：以 null 安全的方式重写你的域模型
 *           让 Optional 发光发热： 去除代码中对 null 的检查
 *           读取 Optional 中可能值的几种方法
 *           对可能缺失值的再思考
 */
public class Index {


    /**
     *  null引用引发的问题
     *  1.如何为缺失的值建模
     *      1.1 采用防御式检查减少 NullPointerException
     *          深层质疑
     *          过多的退出语句
     *      1.2 null带来的种种问题
     *           它是错误之源
     *           它会使你的代码膨胀
     *           它自身是毫无意义的
     *           它破坏了Java的哲学
     *           它在Java的类型系统上开了个口子
     *
     *  warn:null 的检查只会掩盖问题，并未真正地修复问题。
     *     在你的代码中始终如一地使用 Optional ，
     *     能非常清晰地界定出变量值的缺失是结构上的问题，还是你算法上的缺陷，抑或是你数据中的问题
     *
     */
    public static void main(String[] args){

        /**
         * 应用option的几种模式
         */
        

    }

    /**
     * 过多的退出语句,使得代码的维护异常艰难
     * @param person
     * @return String
     */
    private String getCarInsuranceName2(Person person) {
        if (person == null) {
            return "UnKnown";
        }
        Car car = person.getCar();
        if (car == null) {
            return "UnKnown";
        }
        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return "UnKnown";
        }
        String name = insurance.getName();
        if (name == null) {
            return "UnKnown";
        }
        return name;
    }

    /**
     * 深层质疑
     * 每个 null 检查都会增加调 用链上剩余代码的嵌套层数
     * @param person
     * @return String
     */
    public String getCarInsuranceName(Person person) {
        if (person != null) {
            Car car = person.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance.getName() != null) {
                    return insurance.getName();
                }
            }
        }
        return "UnKnown";
    }

}
