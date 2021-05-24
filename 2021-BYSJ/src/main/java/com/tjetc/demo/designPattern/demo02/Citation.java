package com.tjetc.demo.designPattern.demo02;

/*
* 奖状  同一个学校  三好学生奖
* */
public class Citation implements Cloneable{
    String name;
    String info;
    String college;

    public Citation(String name, String info, String college) {
        this.name = name;
        this.info = info;
        this.college = college;
        System.out.println("奖状创建成功");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void display(){
        System.out.println(name+info+college);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("奖状拷贝成功");
        return (Citation)super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Citation c1 = new Citation("张三","，获得了三好学生奖，","天津理工大学中环信息学院");
        Citation c2 = (Citation) c1.clone();
        c2.setName("李四");
        c1.display();
        c2.display();
    }
}
