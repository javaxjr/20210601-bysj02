package com.tjetc.demo.designPattern.demo02;

import java.util.Scanner;

//长方形
public class Square implements Shape {
    @Override
    public Object clone() {
        Square s = null;
        try {
            s = (Square) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return s;
    }

    //计算面积的方法
    @Override
    public void countArea() {
        int i=0;
        System.out.println("这是一个正方形，请输入它的边长");
        Scanner scanner = new Scanner(System.in);
        i = scanner.nextInt();
        System.out.println("正方形的面积="+i*i);
    }
}
