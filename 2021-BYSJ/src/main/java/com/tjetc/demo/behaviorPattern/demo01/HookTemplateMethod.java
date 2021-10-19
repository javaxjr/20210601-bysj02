package com.tjetc.demo.behaviorPattern.demo01;


/*
* 钩子方法的使用--事例
* */
public class HookTemplateMethod {

    public static void main(String[] args) {

        HookAbstractClass tm = new HookConcreteClass();
        tm.TemplateMethod();

    }
}

//含钩子方法的抽象类
abstract class HookAbstractClass{
    //模板方法
    public void TemplateMethod(){
        abstractMethod1();
        Hookmethod1();

        if (Hookmethod2()){
            SpecificMethod();
        }
        abstractMethod2();
    }

    //具体方法
    public void SpecificMethod(){
        System.out.println("抽象类中的具体方法被调用，，，");
    }

    //钩子方法1
    public void Hookmethod1(){};
    public boolean Hookmethod2(){
        return true;
    }

    public abstract void abstractMethod1();
    public abstract void abstractMethod2();
}

//含钩子方法的具体类
class HookConcreteClass extends HookAbstractClass{

    @Override
    public void abstractMethod1() {
        System.out.println("抽象方法1的实现被调用。。。");
    }

    @Override
    public void abstractMethod2() {
        System.out.println("抽象方法1的实现被调用。。。");
    }

    @Override
    public void Hookmethod1() {
        System.out.println("钩子方法1被重写。。。");
    }

    @Override
    public boolean Hookmethod2() {
        return false;
    }
}
