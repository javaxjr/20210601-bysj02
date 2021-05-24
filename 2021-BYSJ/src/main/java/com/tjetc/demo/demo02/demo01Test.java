package com.tjetc.demo.demo02;

import com.tjetc.demo.domain.Animal;
import com.tjetc.demo.domain.Bird;
import com.tjetc.demo.domain.BrownKiwi;
import com.tjetc.demo.domain.Swallow;

/*
* Design pattern设计模式   里式替换原则  测试类
* */
public class demo01Test {
    public static void main(String[] args) {
        Bird bird1 = new Swallow();
        Animal bird2 = new BrownKiwi();

        bird1.setFlySpeed(120);
        bird2.setRunSpeed(60);

        System.out.println("如果飞行300公里");
        try {
            System.out.println("燕子飞行："+bird1.getFlySpeed(300)+"小时");
            System.out.println("几最鸟奔跑："+bird2.getRunTime(300)+"小时");
        }catch (Exception err){
            System.out.println("发生错误了");
        }
    }
}
