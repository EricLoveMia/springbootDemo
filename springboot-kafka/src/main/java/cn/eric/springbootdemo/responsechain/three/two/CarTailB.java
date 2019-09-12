package cn.eric.springbootdemo.responsechain.three.two;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: CarHeader
 * @Description: TODO
 * @company lsj
 * @date 2019/5/29 18:00
 **/
public class CarTailB extends CarControllerB {

    @Override
    public void controlCar() {

        System.out.println("组装汽车的尾部");
        if (getSuccessor() != null) {
            getSuccessor().controlCar();
        }
    }
}
