package com.tjetc.demo.designPattern.demo06;

//v(3) 具体建造者：实现了抽象建造者接口
public class ConcreteBuilder extends Builder {
    @Override
    public void buildPartA() {
        product.setPartA("建造者A");
    }

    @Override
    public void buildPartB() {
        product.setPartA("建造者B");
    }

    @Override
    public void buildPartC() {
        product.setPartA("建造者C");
    }
}
