package com.tjetc.demo.designPattern.demo02;

import java.util.Scanner;

public class Circle implements Shape {
    @Override
    public Object clone() {
        Circle w =null;
        try {
            w = (Circle) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("失败");
            e.printStackTrace();
        }
        return w;
    }

    @Override
    public void countArea() {
        int r =0;
        System.out.println("这是一个圆，请输入圆的半径：");
        Scanner s =new Scanner(System.in);
        r = s.nextInt();
        System.out.println("圆的面积="+3.1415926*r*r+"\n");
    }
}
