package com.tjetc.demo.structure.demo04;

import javax.swing.*;
import java.awt.*;

public class MorriganAensland {
    public static void main(String[] args) {

        Morrigan m0 = new Original();
        m0.display();
        Morrigan m1 = new Succubus(m0);
        m1.display();
        Morrigan m2 = new Girl(m0);
        m2.display();

    }
}

//抽象构建角色 :茉莉卡
interface Morrigan{
    public void display();
}

//具体构件角色：原身
class Original extends JFrame implements Morrigan{

    private static final long serialVersionUID = 1L;
    private String t= "Morrigan0.jpg";


    public Original(){
        super("《恶魔战士》中的莫莉卡·安斯兰");
    }

    public void setImage(String t){
        this.t = t;
    }

    @Override
    public void display() {

        this.setLayout(new FlowLayout());
        JLabel ll = new JLabel(new ImageIcon("C:\\demo\\20210601-bysj\\2021-BYSJ\\src\\main\\java\\com\\tjetc\\demo\\structure\\demo04\\"+t));

        this.add(ll);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

}

//抽象装饰角色：变形
class Change implements Morrigan{

    Morrigan m;

    public Change(Morrigan m) {
        this.m= m;
    }

    @Override
    public void display() {
       m.display();
    }
}

//具体装饰角色：女妖
class Succubus extends Change {
    public Succubus(Morrigan m) {
        super(m);
    }

    public void display(){
        setChanger();
        super.display();
    }

    public  void setChanger(){
        ((Original)super.m).setImage("Morrigan1.jpg");
    }
}

class Girl extends Change{

    public Girl(Morrigan m) {
        super(m);
    }

    public void display(){
        setChange();
        super.display();
    }
    public void setChange(){
        ((Original)super.m).setImage("Morrigan2.jpg");
    }
}