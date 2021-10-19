package com.tjetc.demo.behaviorPattern.demo01;


/*
* Behavior pattern:行为模式
*
* 模板方法模式---事例
*
*
* */
public class TemplateMethodPattern {
    public static void main(String[] args) {

        AbstractClass rm  = new ConcreteClass();
        rm.TemplateMethod();

    }
}

//抽象类
abstract class AbstractClass{
    //模板方法
    public void TemplateMethod(){
        SpecificMethod();
        abstractMethod1();
        abstractMethod2();
    }

    //具体方法
    public void SpecificMethod(){
        System.out.println("抽象类中的具体方法被调用。。。");
    }

    //抽象方法
    public abstract void abstractMethod1();

    public abstract void abstractMethod2();
}

//具体子类
class ConcreteClass extends AbstractClass{

    @Override
    public void abstractMethod1() {

        System.out.println("抽象方法1的实现被调用");

    }

    @Override
    public void abstractMethod2() {

        System.out.println("抽象方法2的实现被调用");
    }
}