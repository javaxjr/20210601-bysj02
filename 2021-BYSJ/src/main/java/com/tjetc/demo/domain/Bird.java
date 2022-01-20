package com.tjetc.demo.domain;

//鸟类  能够飞行的鸟类的父类  机选飞行速度
public class Bird extends Animal{
    private double flySpeed;

    public void setFlySpeed(double flySpeed) {
        this.flySpeed = flySpeed;
    }

    //父类中计算的方法
    public double getFlySpeed(double distance){
        return (distance/flySpeed);
    }
}
