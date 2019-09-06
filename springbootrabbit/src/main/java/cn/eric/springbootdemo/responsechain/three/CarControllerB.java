package cn.eric.springbootdemo.responsechain.three;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: CarController
 * @Description: TODO
 * @company lsj
 * @date 2019/5/29 17:58
 **/
public abstract class CarControllerB {

    protected CarControllerB successor;

    public CarControllerB getSuccessor() {
        return successor;
    }

    public CarControllerB setSuccessor(CarControllerB successor) {
        this.successor = successor;
        return this.successor;
    }

    public abstract void controlCar();
}
