package com.tjetc.demo.demo03;

/*
* 顾客  同时在不同的网店中购买商品
* */
public class Customer {
    public void shoping(Shop shop){
        //购买  了什么商品
        System.out.println(shop.sell());
    }
}
