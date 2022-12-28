package com.tjetc.service.impl;

import com.tjetc.service.FruitService;
import com.tjetc.springAnotation.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FruitServiceImpl implements FruitService {

    //Fruit有2个实例子类，因为梨子用@Primary，那么会使用Pear注入
    @Autowired
    private Fruit fruit;

    @Override
    public String hello(){
        return fruit.hello();
    }

}
