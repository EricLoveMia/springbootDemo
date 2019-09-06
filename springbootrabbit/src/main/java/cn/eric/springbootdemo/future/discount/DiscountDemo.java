package cn.eric.springbootdemo.future.discount;

import java.text.NumberFormat;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: DiscountDemo
 * @Description: TODO
 * @company lsj
 * @date 2019/5/29 16:04
 **/
public class DiscountDemo {

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String applyDiscount(Quote quote){
        return quote.getName() + "price is" + apply(quote.getPrice(),quote.getDiscount());
    }

    public static String apply(Double price, Discount discount) {
        delay();
        return NumberFormat.getInstance().format(price * (100 - discount.getPercent())/100);
    }
}
