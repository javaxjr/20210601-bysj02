package com.tjetc.demo.自定义异常;

public class TestException {
    public static void main(String[] args){
        UseMyException ex = new UseMyException("admin","123456");

        try {
            ex.throwException("111","4");
        } catch (MyException e) {
            e.printStackTrace();
            System.out.println("e.getMessage() = " + e.getMessage());
        }

        try {
            ex.throwException("12");

        } catch (MyException e) {
            e.printStackTrace();
            System.out.println("e.getMessage() = " + e.getMessage());
        }finally {
            System.out.println("ex. = ");
        }



    }
}
