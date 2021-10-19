package com.tjetc.demo.structure.demo01;

import javax.swing.*;
import java.awt.*;

public class WySpecialtyProxy {
    public static void main(String[] args) {
        SgProxy sgProxy = new SgProxy();
        sgProxy.display();
    }
}

//抽象主题:特产
interface Specialty{
    void display();
}

//真实主题：婺源特产
class WySpecialty extends JFrame implements Specialty{

    private static final long serialVersionID = 1L;

    public WySpecialty(){
        super("韶关代理婺源特产测试。。。");
        this.setLayout(new GridLayout(1,1));
        JLabel ll = new JLabel(new ImageIcon("C:\\demo\\20210601-bysj\\2021-BYSJ\\src\\main\\java\\com\\tjetc\\demo\\structure\\demo01\\WuyuanSpecialty.jpg"));
        this.add(ll);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void display() {
        this.setVisible(true);
        System.out.println("访问被代理的类之后。。。。");
    }
}

//代理类  通过去类去代理 实现访问被代理的类中的方法
class SgProxy implements Specialty{

    private WySpecialty realSuject = new WySpecialty();

    @Override
    public void display() {

        PreRequest();
        realSuject.display();
        postRequest();
    }

    public void PreRequest(){
        System.out.println("韶关代理婺源特产开始。。");
    }
    public void postRequest(){
        System.out.println("韶关代理婺源特产结束。。");
    }

}