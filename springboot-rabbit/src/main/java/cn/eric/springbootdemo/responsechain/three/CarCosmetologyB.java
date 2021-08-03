package cn.eric.springbootdemo.responsechain.three;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: CarHeader
 * @Description: TODO
 * @company lsj
 * @date 2019/5/29 18:00
 **/
public class CarCosmetologyB extends CarControllerB {

    @Override
    public void controlCar() {

        System.out.println("美容工作");
        if (getSuccessor() != null) {
            getSuccessor().controlCar();
        }
    }
}
