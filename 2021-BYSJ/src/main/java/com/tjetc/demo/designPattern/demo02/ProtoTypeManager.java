package com.tjetc.demo.designPattern.demo02;

import java.util.HashMap;

public class ProtoTypeManager {
    private HashMap<String,Shape> ht = new HashMap<>();

    public ProtoTypeManager(){
        ht.put("Circle",new Circle());
        ht.put("Square",new Square());
    }

    public void addShape(String key,Shape obj){
        ht.put(key,obj);
    }
    public Shape getShape(String key){
        Shape shape = ht.get(key);
        return shape;
    }

    public static void main(String[] args) {
        ProtoTypeManager pm = new ProtoTypeManager();
        Shape circle = (Circle)pm.getShape("Circle");
        circle.countArea();
        Shape square = (Square)pm.getShape("Square");
        square.countArea();
    }
}
