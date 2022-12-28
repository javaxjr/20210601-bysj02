package com.tjetc.springAnotation.impl;

import com.tjetc.springAnotation.Fruit;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Pear implements Fruit {
    @Override
    public String hello() {
        return "梨子";
    }
}
