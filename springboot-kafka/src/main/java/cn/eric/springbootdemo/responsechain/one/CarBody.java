package cn.eric.springbootdemo.responsechain.one;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: CarBody
 * @Description: TODO
 * @company lsj
 * @date 2019/5/30 14:39
 **/
public class CarBody extends CarController{
    @Override
    public void controlCar() {
        System.out.println("组装汽车的身体");
    }
}