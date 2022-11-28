package com.loveprogrammer.springboot.kafka.responsechain.three.two;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: ProcessB
 * @Description: TODO
 * @company lsj
 * @date 2019/5/30 14:50
 **/
public class ProcessB {

    public void exccute() {
        CarControllerB head = new CarHeaderB();
        CarControllerB body = new CarBodyB();
        CarControllerB tail = new CarTailB();
        CarControllerB drumbeating = new CarDrumbeatingB();
        CarControllerB cosmetology = new CarCosmetologyB();

        // 灵活的组装生产线的顺序，目前规定，先组装头，之后尾部，最后身子！完成之后美容，宣传出去！
        head.setSuccessor(body).setSuccessor(tail).setSuccessor(drumbeating).setSuccessor(cosmetology);

        head.controlCar();
    }

    public static void main(String[] args) {
        ProcessB processB = new ProcessB();
        processB.exccute();
    }
}
