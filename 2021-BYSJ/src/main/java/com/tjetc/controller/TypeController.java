package com.tjetc.controller;

import com.tjetc.domain.Type;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/type")
public class TypeController {

    @RequestMapping("/add")
    public boolean add(Type type){
        System.out.println("type = " + type);
        return true;
    }

}
