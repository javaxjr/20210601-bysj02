package com.tjetc.demo.structure.demo02;

//目标：发动机
public interface Motor {
    public void drive();
}

//适配者1：电能发动机
class ElectricMotor{
    public void electricDrive(){
        System.out.println("电能发动机驱动汽车！");
    }
}

//适配者2：光能发动机
class OpticalMotor{
    public void opticalDrive(){
        System.out.println("光能发动机驱动汽车！");
    }
}

//电能适配器
class ElectricAdapter implements Motor{

    private ElectricMotor electricMotor;

    public ElectricAdapter(){
        electricMotor = new ElectricMotor();
    }

    @Override
    public void drive() {
        System.out.println("适配器测试：");
        electricMotor.electricDrive();
    }
}

//光能适配器
class OpticalAdapter implements Motor
{
    private OpticalMotor opticalDrive;
    public OpticalAdapter()
    {
        opticalDrive=new OpticalMotor();
    }
    public void drive()
    {
        System.out.println("适配器测试：");
        opticalDrive.opticalDrive();
    }
}
