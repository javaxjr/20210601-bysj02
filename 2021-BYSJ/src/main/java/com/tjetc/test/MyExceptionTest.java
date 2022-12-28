package com.tjetc.test;

import com.tjetc.exception.MyException;

public class MyExceptionTest {

    public static void execute(String a) {
        System.out.println("execute.....");
        if ("true".equals(a)) {
            //System.exit(0);
            throw new MyException("参数不能为true");

        }

    }

    public static void main(String[] args) {
        execute("true");
    }
}
