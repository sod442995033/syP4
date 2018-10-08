package top.dzygod.jdk8.practice.chaptereleven;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @Author: dingziyuan
 * @Date: 2018/10/4 17:43
 * @Description: 异步API的实现
 */
public class Shop {

    private static Random random = new Random();
    private String shopName;

    public Shop(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }


    /**
     *  随机的价格与随机的折扣
     * @param product
     * @return
     */
    public String getPricesTwo(String product) {
        double price = calculatePrice(product);
         DisCount.Code code = DisCount.Code.values()[
                 //0到指定参数范围内的随机数
                random.nextInt(DisCount.Code.values().length)];

        return String.format("%s:%s:%s",product,price,code);
    }

    /**
     * 第一种实现
     *
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync(String product) {
        //创建completabaleFuture对象
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception e) {
                //抛出导致线程阻塞的原因
                futurePrice.completeExceptionally(e);
            }
        }).start();

        return futurePrice;
    }



    public double getPrice(String product) {
        Future<Double> async2 = getPriceAsync2(product);
        try {
            return async2.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 第二种实现
     * 工厂方法创建,
     * 与第一种方法一致,内部处理了异常
     *
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync2(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }


    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 模拟业务处理一秒延时
     */
    private void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
