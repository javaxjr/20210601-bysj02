package com.tjetc.demo.designPattern.demo05;

import javax.swing.*;
import java.awt.*;

/*
* 抽象工厂模式
* */
public class FarmTest {
    public static void main(String[] args) {

        Farm f;
        Animal a ;
        Plant p;
        f = (Farm) ReadXML.getObject();
        a =f.newAnimal();
        p = f.newPlant();
        a.show();
        p.show();

    }
//抽象产品动物类
interface Animal{
    public void show();
}
//具体产品：马类
class Horse implements Animal{

    JScrollPane sp;
    JFrame jf = new JFrame("抽象工厂模式测试");

    public Horse(){
        Container container = jf.getContentPane();
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1,1));
        p1.setBorder(BorderFactory.createTitledBorder("动物:马"));

        sp = new JScrollPane(p1);
        container.add(sp,BorderLayout.CENTER);
        JLabel ll = new JLabel(new ImageIcon("C:\\demo\\20210601-bysj\\2021-BYSJ\\src\\main\\java\\com\\tjetc\\demo\\designPattern\\demo05\\sourceMaterial\\A_Cattle.jpg"));

        p1.add(ll);
        jf.pack();
        jf.setVisible(false);
        //用户点击窗口关闭
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void show() {
        jf.setVisible(true);
    }
}

//具体产品：牛类
class Cattle implements Animal{

    JScrollPane sp;
    JFrame jf = new JFrame("抽象工厂模式测试");

    public Cattle(){
        Container container = jf.getContentPane();
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1,1));
        p1.setBorder(BorderFactory.createTitledBorder("动物:马"));

        sp = new JScrollPane(p1);
        container.add(sp,BorderLayout.CENTER);
        JLabel ll = new JLabel(new ImageIcon("C:\\demo\\20210601-bysj\\2021-BYSJ\\src\\main\\java\\com\\tjetc\\demo\\designPattern\\demo05\\sourceMaterial\\A_Cattle.jpg"));

        p1.add(ll);
        jf.pack();
        jf.setVisible(false);
        //用户点击窗口关闭
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void show() {
        jf.setVisible(true);
    }
}

//抽象产品 ：植物类
interface Plant{
    public void show();
}

//具体产品：水果类
class Fruitage implements Plant{

    JScrollPane sp;
    JFrame jf = new JFrame("抽象工厂测试");

    public Fruitage(){
        Container container = jf.getContentPane();
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1,1));
        p1.setBorder(BorderFactory.createTitledBorder("植物：水果"));

        sp = new JScrollPane(p1);
        container.add(sp,BorderLayout.CENTER);
        JLabel ll = new JLabel(new ImageIcon("C:\\demo\\20210601-bysj\\2021-BYSJ\\src\\main\\java\\com\\tjetc\\demo\\designPattern\\demo05\\sourceMaterial\\P_Vegetables.jpg"));

        p1.add(ll);
        jf.pack();
        jf.setVisible(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void show() {
        jf.setVisible(true);
    }
}

//具体产品：蔬菜类
class Vegetables implements Plant {
    JScrollPane sp;
    JFrame jf = new JFrame("抽象工厂模式测试");
    public Vegetables() {
        Container contentPane = jf.getContentPane();
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));
        p1.setBorder(BorderFactory.createTitledBorder("植物：蔬菜"));
        sp = new JScrollPane(p1);
        contentPane.add(sp, BorderLayout.CENTER);
        JLabel l1 = new JLabel(new ImageIcon("C:\\demo\\20210601-bysj\\2021-BYSJ\\src\\main\\java\\com\\tjetc\\demo\\designPattern\\demo05\\sourceMaterial\\P_Vegetables.jpg"));
        p1.add(l1);
        jf.pack();
        jf.setVisible(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//用户点击窗口关闭
    }
    public void show() {
        jf.setVisible(true);
    }
}

//抽象工厂：农场类
interface Farm{
    public Animal newAnimal();
    public Plant newPlant();
}

//具体工厂：韶关农场
class SGfarm implements Farm{

    @Override
    public Animal newAnimal() {
        System.out.println("新牛出生！");
        return new Cattle();
    }

    @Override
    public Plant newPlant() {
        System.out.println("蔬菜长成！");
        return new Vegetables();
    }
}

//具体工厂：上尧农场类
class SPfarm implements Farm{
    @Override
    public Animal newAnimal() {
        return new Horse();
    }

    @Override
    public Plant newPlant() {
        return new Fruitage();
    }
}

}