package com.tjetc.demo.designPattern.demo04;

/*
* 工厂方法模式
* */
public class AbstractFactoryTest {
    public static void main(String[] args) {

        Product a;
        AbstractFactory af;
        af = (AbstractFactory) RedXML1.getObject();
        a = af.newProduct();

        a.show();
    }

    //抽象产品：提供了产品的接口
    interface Product{
        public void show();
    }

    //具体产品1：实现抽象产品中的抽象方法
    class ConcreateProduct1 implements Product{

        @Override
        public void show() {
            System.out.println("具体产品1：实现抽象产品中的抽象方法");
        }

    }

    //具体产品2：实现抽象产品中的抽象方法
    static class ConcreateProduct2 implements Product{
        @Override
        public void show() {
            System.out.println("//具体产品2：实现抽象产品中的抽象方法");
        }
    }
    //抽象工厂：提供了厂品的生成方法
    interface AbstractFactory{
        public Product newProduct();
    }

    //具体工厂1：实现了厂品的生成方法
    class ConcreteFactory1 implements AbstractFactory{
        @Override
        public Product newProduct() {
            System.out.println("具体工厂1生成-->具体产品1...");
            return new ConcreateProduct1();
        }
    }

    static class ConcreteFactory2 implements AbstractFactory{
        @Override
        public Product newProduct() {
            System.out.println("具体工厂2生成-->具体产品2...");
            return new ConcreateProduct2();
        }
    }

}
