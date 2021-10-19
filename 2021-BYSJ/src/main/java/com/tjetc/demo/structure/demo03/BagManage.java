package com.tjetc.demo.structure.demo03;

import javax.swing.*;
import java.awt.*;

public class BagManage {
    public static void main(String[] args) {

        Color color;
        Bag bag;
        color = (Color) ReadXML.getObject("color");
        bag = (Bag) ReadXML.getObject("bag");

        bag.setColor(color);
        String name = bag.getName();
        show(name);

    }

    public static void show(String name) {
        JFrame jf = new JFrame("桥接模式测试");
        Container contentPane = jf.getContentPane();

        JPanel p = new JPanel();
        JLabel l = new JLabel(new ImageIcon("C:\\demo\\20210601-bysj\\2021-BYSJ\\src\\main\\java\\com\\tjetc\\demo\\structure\\demo03\\image\\yellowHandBag.jpg"));
        JLabel l2 = new JLabel(new ImageIcon("C:\\demo\\20210601-bysj\\2021-BYSJ\\src\\main\\java\\com\\tjetc\\demo\\structure\\demo03\\image\\yellowHandBag.jpg"));
        p.setLayout(new GridLayout(1, 1));
        p.setBorder(BorderFactory.createTitledBorder("女士皮包"));
        p.add(l);
        p.add(l2);

        contentPane.add(p, BorderLayout.CENTER);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

//实现化角色：颜色
interface Color{
    String getColor();
}

//具体实现化角色：黄色
class Yellow implements Color{
    @Override
    public String getColor() {
        return "yellow";
    }
}
//具体实现化角色：红色色
class Red implements Color{
    @Override
    public String getColor() {
        return "red";
    }
}

//抽象化角色：包
abstract class Bag{
    protected Color color;
    public void setColor(Color color){
        this.color=color;
    }

    public abstract String getName();
}

//扩展抽象化：挎包
class HandBag extends Bag{
    @Override
    public String getName() {

        return color.getColor()+"HandBag";
    }
}
//扩展抽象化：钱包
class Wallet extends Bag{
    @Override
    public String getName() {

        return color.getColor()+"Wallet";
    }
}

