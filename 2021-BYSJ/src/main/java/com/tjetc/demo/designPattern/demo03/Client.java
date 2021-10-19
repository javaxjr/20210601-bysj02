package com.tjetc.demo.designPattern.demo03;

//简单工厂模式
public class Client {
    public static void main(String[] args) {
        Product product = SimpleFactory.makeProduct(1);
        System.out.println("product = " + product);
        product.show();
    }

    //抽象产品
    public interface  Product{
        void show();
    }

    //具体商品A ProductA
    static class CreateProduct1 implements Product{
        @Override
        public void show() {
            System.out.println("具体产品1。。。。");
        }
    }

    //具体产品 ProductB
    static class CreateProduct2 implements Product{

        @Override
        public void show() {
            System.out.println("具体产品2展示.....");
        }
    }

    final class Const{
        static final int PRODUCT_A = 0;
        static final int PRODUCT_B = 1;
        static final int PRODUCT_C = 2;
    }

    static class SimpleFactory{
        public static Product makeProduct(int kind){
            switch (kind){
                case Const.PRODUCT_A:
                    return new CreateProduct1();
                case Const.PRODUCT_B:
                    return new CreateProduct2();
            }
            return null;
        }
    }

}
