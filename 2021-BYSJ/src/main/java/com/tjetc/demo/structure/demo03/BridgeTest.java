package com.tjetc.demo.structure.demo03;


import org.springframework.http.HttpRequest;

//桥接模式
public class BridgeTest {
    public static void main(String[] args) {

        Implementor implementor = new ConcreteImplTorA();
        Abstraction abstraction = new RefineAbstraction(implementor);
        abstraction.Operation();

    }
}

//实现化角色
interface Implementor{
    public void OperationImpl();
}

//具体实现化角色
class ConcreteImplTorA implements Implementor{

    @Override
    public void OperationImpl() {
        System.out.println("具体实现化(Concrete Implementor)角色被访问");
    }
}

//抽象化角色
abstract class Abstraction{
    protected  Implementor imple;
    protected Abstraction(Implementor imple){
        this.imple = imple;
    }

    protected abstract void Operation();
}

//扩展抽象角色
class RefineAbstraction extends Abstraction{

    protected RefineAbstraction(Implementor imple) {
        super(imple);
    }

    @Override
    protected void Operation() {
        System.out.println("扩展抽象化(Refined Abstraction)角色被访问");
        imple.OperationImpl();
    }
}