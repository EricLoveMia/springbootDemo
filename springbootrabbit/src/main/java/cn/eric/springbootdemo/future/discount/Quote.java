package cn.eric.springbootdemo.future.discount;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: Quote
 * @Description: TODO
 * @company lsj
 * @date 2019/5/29 16:08
 **/
@Getter
@Setter
public class Quote {

    private String name;

    private Double price;
    private Discount discount;

    Random random = new Random();

    public Quote(String name, Double price, Discount discount) {
        this.name = name;
        this.price = price;
        this.discount = discount;
    }
}
