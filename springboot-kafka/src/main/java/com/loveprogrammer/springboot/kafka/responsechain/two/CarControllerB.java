package com.loveprogrammer.springboot.kafka.responsechain.two;

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

    public void setSuccessor(CarControllerB successor) {
        this.successor = successor;
    }

    public abstract void controlCar();
}
