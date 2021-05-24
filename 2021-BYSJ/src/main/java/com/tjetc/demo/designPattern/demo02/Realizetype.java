package com.tjetc.demo.designPattern.demo02;

//具体原型类
public class Realizetype implements Cloneable {
    public Realizetype() {
        System.out.println("具体原型类创建成功");
    }

    //重写克隆方法 实现深度克隆
    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("具体原型复制成功");
        return (Realizetype)super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Realizetype obj1 = new Realizetype();
        Object clone = obj1.clone();
        System.out.println(obj1==clone);
    }
}


