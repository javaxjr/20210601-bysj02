package com.tjetc.demo.designPattern.demo06;


//客户端类
public class Client {

    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        Product product = director.construct();
        product.show();
    }


}
