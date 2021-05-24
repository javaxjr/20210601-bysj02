package com.tjetc.demo.domain;

//动物类  更为广泛的父类
public class Animal {
    private double runSpeed;

    public void setRunSpeed(double runSpeed) {
        this.runSpeed = runSpeed;
    }

    public double getRunTime(double distance){
        return (distance/runSpeed);
    }
}
