package com.tjetc.demo.designPattern.demo02;

//原型管理器的原型设计模式
public interface Shape extends Cloneable {
    public Object clone();//拷贝

    public void countArea();//计算面积的方法
}
