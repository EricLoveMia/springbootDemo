package cn.eric.springbootdemo.responsechain.two;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: CarHeader
 * @Description: TODO
 * @company lsj
 * @date 2019/5/29 18:00
 **/
public class CarDrumbeatingB extends CarControllerB{

    @Override
    public void controlCar() {

        System.out.println("进行宣传工作");
        if(getSuccessor() != null){
            getSuccessor().controlCar();
        }
    }
}
