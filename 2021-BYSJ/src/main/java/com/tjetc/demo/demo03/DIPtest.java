package com.tjetc.demo.demo03;

//测试类   向下造型
/*
 * 设计模式 --依赖倒置原则
 * */
public class DIPtest {
    public static void main(String[] args) {
        Customer wang = new Customer();
        System.out.println("顾客购买以下商品：");
        wang.shoping(new ShaoguanShop());
        wang.shoping(new WuyuanShop());
    }
}
