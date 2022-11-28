package com.loveprogrammer.springboot.kafka.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: PriceDemo
 * @Description: TODO
 * @company lsj
 * @date 2019/5/29 15:46
 **/
public class PriceDemo {

    private List<Shop> shops = Arrays.asList(new Shop("shop1"), new Shop("shop2"), new Shop("shop3"), new Shop("shop4"), new Shop("shop5"), new Shop("shop6"), new Shop("shop7"), new Shop("shop8"), new Shop("shop11"), new Shop("shop13"), new Shop("shop14"), new Shop("shop15"), new Shop("shop16"), new Shop("shop17"), new Shop("shop18"), new Shop("shop22"), new Shop("shop23"), new Shop("shop24"), new Shop("shop25"), new Shop("shop26"), new Shop("shop27"), new Shop("shop28"), new Shop("shop9"));

//    public List<String> findPrices(String product){
//        /*
//         * 方法一：加并行流.parallel()
//         * */
//        return shops.stream().parallel().map(shop -> String.format("%s price is %.2f",shop.getName(),shop.getPrice(product)))
//                .collect(Collectors.toList());
//    }

    public List<String> findPricesCompletable(String product) {
        List<CompletableFuture<String>> priceFuture = shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f ", shop.getName(), shop.getPrice(product)))).collect(Collectors.toList());
        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    /**
     * 在这里，可以学习下别人的 newFixedThreadPool 配置线程数，当然，不精确的话 用这个newCachedThreadPool就完事了
     * T=N*U*(1+W/C)
     * N是cpu核数，U是CPU占用比
     * 最佳线程数目 = （线程等待时间与线程CPU时间之比 + 1）* CPU数目
     *
     * @return java.util.List<java.lang.String>
     * @throws
     * @author Eric
     * @date 15:58 2019/5/29
     * @params product
     **/
    public List<String> findPricesExecutor(String product) {
        Executor executor = Executors.newCachedThreadPool();
        //Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(),100));
        List<CompletableFuture<String>> priceFuture = shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f ", shop.getName(), shop.getPrice(product)), executor)).collect(Collectors.toList());
        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        PriceDemo priceDemo = new PriceDemo();
        long start = System.currentTimeMillis();
        // System.out.println(priceDemo.findPrices("苹果x"));
        // System.out.println(priceDemo.findPricesCompletable("苹果x"));
        System.out.println(priceDemo.findPricesExecutor("苹果x"));
        System.out.println("服务耗时" + (System.currentTimeMillis() - start));
    }

    public List<String> findPrices(String product) {
        Executor executor = Executors.newCachedThreadPool();

//        List<CompletableFuture<String>> priceFuture = shops.stream()
//                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product),executor)
//                .thenCombine(CompletableFuture.supplyAsync(() -> ExchangeDemo.getRate("USD","CNY"),executor),
//                        (quote,rate) -> new Quote(quote)
//                )
        return null;
    }
}
