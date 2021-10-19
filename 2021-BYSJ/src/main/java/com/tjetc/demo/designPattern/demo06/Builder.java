package com.tjetc.demo.designPattern.demo06;

//(2) 抽象建造者：包含创建产品各个子部件的抽象方法。
public abstract class Builder {

    //创建产品
    protected Product product = new Product();

    public abstract void buildPartA();
    public abstract void buildPartB();
    public abstract void buildPartC();

    //返回产品对象
    public Product getResult(){
        return product;
    }
}
