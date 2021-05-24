package com.tjetc.demo.designPattern.demo02;

import javax.swing.*;
import java.awt.*;

//原型类   -- 原型模式-------悟空复制多条记录
public class SunWuKong extends JPanel implements Cloneable {

    private static final long serialVersionUUID = 5543049531872119328L;

    public SunWuKong(){
        JLabel ll = new JLabel(new ImageIcon("C:\\demo\\20210601-bysj\\2021-BYSJ\\src\\main\\java\\com\\tjetc\\demo\\designPattern\\demo02\\sunwukong.jpg"));
        this.add(ll);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SunWuKong w = null;
        try {
            w=(SunWuKong) super.clone();
        }catch (CloneNotSupportedException e){
            System.out.println("拷贝失败");
        }
        return w;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        JFrame jf= new JFrame("原型模式测试");
        jf.setLayout(new GridLayout(1,2));
        Container contentPane = jf.getContentPane();
        SunWuKong sunWuKong = new SunWuKong();
        contentPane.add(sunWuKong);
        SunWuKong sunWuKong1= (SunWuKong) sunWuKong.clone();
        contentPane.add(sunWuKong1);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
