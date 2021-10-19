package com.tjetc.demo.structure.demo04;

public class DecoratorPattern {

    public static void main(String[] args) {

        Component p   = new ConcreteComponent();
        p.operation();
        System.out.println("------------------");
        Component d= new ConcreteDecorator(p);
        d.operation();

    }
}

//抽象构建
interface Component{
    public void operation();
}
//具体构建角色
class ConcreteComponent implements Component{

    public void ConcreteComponent() {
        System.out.println("创建具体构件角色。。");
    }

    public void operation(){
        System.out.println("调用具体构件角色的方法operation()");
    }
}

//抽象装饰角色
class Decorator implements Component{

    private Component component;

    public Decorator(Component component){
        this.component = component;
    }

    @Override
    public void operation() {
       component.operation();
    }

}

//具体装饰角色
class ConcreteDecorator extends Decorator{
    public ConcreteDecorator(Component component) {
        super(component);
    }

    public void operation(){
        super.operation();
        addFunction();
    }

    public void addFunction(){
        System.out.println("为具体构件角色增加而外的功能。。。");
    }
}
