package com.tjetc.demo.structure.demo08;


import javax.swing.*;
import java.awt.*;

/*
* 结构型模式应用实验 ----使用代理模式实现 房产中介代理
* */
public class SgHouseProxy {
    public static void main(String[] args) {

        HouseOwner houseOwner = new HouseProxy();
        houseOwner.display();

    }
}

//抽象主题：房子
interface HouseOwner{
    void display();
}

//真实主题：韶关碧桂园
class SgBiguiyuan extends JFrame implements HouseOwner{

    public SgBiguiyuan(){
        super("房产中介代售韶关碧桂园房子");
    }

    @Override
    public void display() {

        this.setLayout(new GridLayout(1,1));
        JLabel ll = new JLabel(new ImageIcon("C:\\demo\\20210601-bysj\\2021-BYSJ\\src\\main\\java\\com\\tjetc\\demo\\structure\\demo08\\Sgbiguiyuan.jpg"));
        this.add(ll);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

//代理：房产中介
class HouseProxy implements HouseOwner{

    private SgBiguiyuan sgBiguiyuan = new SgBiguiyuan();
    @Override
    public void display() {

        preRequest();
        sgBiguiyuan.display();
        postRequest();

    }

    public void preRequest(){
        System.out.println("房产中介介绍韶关碧桂园。。。");
    }

    public void postRequest(){
        System.out.println("房产中介登记购房信息。。。");
    }
}