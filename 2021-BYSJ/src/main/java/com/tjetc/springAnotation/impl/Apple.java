package com.tjetc.springAnotation.impl;

import com.tjetc.springAnotation.Fruit;
import org.springframework.stereotype.Component;

@Component
public class Apple implements Fruit {

    @Override
    public String hello() {
        return "我是苹果";
    }
}
