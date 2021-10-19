package com.tjetc.demo.behaviorPattern.demo02;

public class StrategyPattern {
    public static void main(String[] args) {

        Context context = new Context();
        Strategy s = new ConcreteStrategyA();
        context.setStrategy(s);
        s.strategyMethod();
        System.out.println("------------");
        Strategy s2 = new ConcreteStrategyB();
        context.setStrategy(s2);
        s2.strategyMethod();
    }
}

//抽象策略类
interface Strategy{
    public void strategyMethod();//策略方法
}

//具体策略类
class ConcreteStrategyA implements Strategy{
    @Override
    public void strategyMethod() {
        System.out.println("具体策略A的策略方法被访问！");
    }
}
class ConcreteStrategyB implements Strategy{
    @Override
    public void strategyMethod() {
        System.out.println("具体策略B的策略方法被访问！");
    }
}

//环境类
class Context{
    private Strategy strategy;

    public Strategy getStrategy(){
        return strategy;
    }

    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }

    public void setStrategyMethod(){
        strategy.strategyMethod();
    }
}