package com.tjetc.demo.designPattern.demo01;


import javax.swing.*;
import java.awt.*;

/*
* 单例模式  饿汉式和懒汉式
* */
public class demo01 {
    public static void main(String[] args) {


        /*
         * 懒汉式 ----实例
         * */
        /*President zt1 = President.getInstance();
        zt1.getName();
        President zt2 = President.getInstance();
        zt2.getName();

        if (zt1==zt2){
            System.out.println("同一个");
        }else{
            System.out.println("不同");
        }*/


        /*
        * 饿汉式----用八戒的例子来演示  框架窗体 JFrame 组件
        * */
        JFrame jf = new JFrame("饿汉式单例模式演示");
        jf.setLayout(new GridLayout(1,2));
        Container container = jf.getContentPane();
        Bajie bajie = Bajie.getInstance();
        container.add(bajie);
        Bajie bajie2 = Bajie.getInstance();
        if (bajie==bajie2){
            System.out.println("同一个");
        }else{
            System.out.println("不同");
        }
        jf.pack();
        jf.setVisible(true);//可视
        //jf.setSize(400,500);   //设置窗口大小
        jf.setLocation(300,300);  //设置窗口位置
        jf.setVisible(true);  //窗口的可视
        //设置窗体的位置
        jf.setLocation(700, 300);
        //jf.setResizable(false);  //禁用调整大小
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
/*
* 懒汉式 ----实例
* */
class President{
    //保证instance在所有线程中同步
    private static volatile President instance = null;
    //私有化构造方法
    private President(){
        System.out.println("产生一个总统");
    }

    public static synchronized President getInstance(){
        //在getinstance方法上家同步
        if (instance ==null){
            instance=new President();
        }else {
            System.out.println("已经有一个总统了，不能再产生新总统");
        }
        return instance;
    }

    public void getName() {
        System.out.println("我是美国总统：特朗普。");
    }
}

/*
* 饿汉式实例
* 用到了框架窗体 JFrame 组件，这里的猪八戒类是单例类，可以将其定义成面板 JPanel 的子类
* */
class Bajie extends JPanel{
    private static Bajie instance = new Bajie();

    private Bajie(){
        //JLabel ll = new JLabel(new ImageIcon("imgs/Bajie.jpg"));
        JLabel ll = new JLabel(new ImageIcon("C:\\demo\\20210601-bysj\\2021-BYSJ\\src\\main\\java\\com\\tjetc\\demo\\designPattern\\demo01\\bajie.jpg"));
        this.add(ll);
    }
    public static Bajie getInstance(){
        return instance;
    }
}
