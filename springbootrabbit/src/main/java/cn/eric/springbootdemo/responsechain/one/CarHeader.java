package cn.eric.springbootdemo.responsechain.one;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: CarHeader
 * @Description: TODO
 * @company lsj
 * @date 2019/5/29 18:00
 **/
public class CarHeader extends CarController{
    @Override
    public void controlCar() {
        System.out.println("组装汽车的头部");
    }
}
