package cn.eric.springbootdemo.responsechain.one;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: MainClass
 * @Description: TODO
 * @company lsj
 * @date 2019/5/30 14:40
 **/
public class MainClass {

    public static void main(String[] args) {

        // 进行组装
        CarController head = new CarHeader();
        CarController body = new CarBody();
        CarController tall = new CarTall();

        head.controlCar();
        body.controlCar();
        tall.controlCar();
    }
}
