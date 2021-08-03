package cn.eric.springbootdemo.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: Shop
 * @Description: TODO
 * @company lsj
 * @date 2019/5/29 15:39
 **/
public class Shop {

    Random random = new Random();

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public double getPrice(String product) {
        delay();
        return random.nextDouble() * 100;
    }

    public Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> (getPrice(product)));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Shop shop = new Shop("seven 11");
        long start = System.currentTimeMillis();
        Future<Double> some_product = shop.getPriceAsync("some product");
        System.out.println("调用返回，耗时" + (System.currentTimeMillis() - start));
        Double aDouble = some_product.get();
        System.out.println("价格返回，耗时" + (System.currentTimeMillis() - start));
    }
}
