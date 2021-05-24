package com.tjetc.mapper;

import com.tjetc.domain.Order;
import com.tjetc.domain.Product;
import com.tjetc.util.MapPageModel;

import java.util.List;
import java.util.Map;

public interface ProductMapper {
    List<Product> listByName(String name);

    List<Map<String, Object>> findMapList(Map<String, Object> map);

    List<Order> selectOrderByReasonsRefund();

    int add(Product product);

    List<Product> selectByProductJSON();



    // MapPageModel listByName(String name);
}
