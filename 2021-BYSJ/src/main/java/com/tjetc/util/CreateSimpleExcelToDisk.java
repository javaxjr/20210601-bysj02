package com.tjetc.util;

import com.tjetc.domain.Product;

import java.util.ArrayList;
import java.util.List;

public  class  CreateSimpleExcelToDisk {

    public static List getProduct() throws Exception{
        List list = new ArrayList();
        Product product1 = new Product(1,"张三",0.3,5,"fdfdfd","fdfd","dfdfd","鞋子");
        Product product2 = new Product(2,"李四",0.3,5,"fdfdfd","fdfd","dfdfd","吃的");
        Product product3 = new Product(3,"王五",0.3,5,"fdfdfd","fdfd","dfdfd","穿的");
        Product product4 = new Product(4,"赵柳",0.3,5,"fdfdfd","fdfd","dfdfd","住的");

        list.add(product1);
        list.add(product2);
        list.add(product3);
        list.add(product4);
        return list;
    }
}
