package top.dzygod.designpatterns.java8.factorymode;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 使用工厂模式，你无需向客户暴露实例化的逻辑就能完成对象的创建。
 *
 * @Author: dingziyuan
 * @Date: 2018/9/9 15:10
 * @Description: 工厂模式
 */
public class ProductFactory {
    public static final Map<String, Supplier<Product>> SUPPLIER_MAP = new HashMap();

    static {
        SUPPLIER_MAP.put("loan", Loan::new);
        SUPPLIER_MAP.put("stock", Stock::new);
        SUPPLIER_MAP.put("bond", Bond::new);
    }

    public static Product createProduct(String name) {
        Supplier<Product> supplier = SUPPLIER_MAP.get(name);
        return supplier.get();
    }


    /**
     * 你在创建对象时不用再担心会将构造函数或者配置暴露给客户，这使得客户创建产品时更加简单
     *
     * @param args
     */
    public static void main(String[] args) {
//        normal();
        Product loan = createProduct("loan");
        System.out.println(loan.name);
    }



/*
    public static Product createProduct(String name) {
        switch (name) {
            case "loan":
                return new Loan();
            case "stock":
                return new Stock();
            case "bond":
                return new Bond();
            default:
                throw new RuntimeException("No such product " + name);
        }
    }


    private static void normal() {
        Product p = ProductFactory.createProduct("loan");
    }
*/
}


class Product {
    String name = "酷炫";
}

class Loan extends Product {

}

class Stock extends Product {

}

class Bond extends Product {

}