package com.tjetc.domain;

/**
 * 购物车表
 */
public class MyCat {
    private Integer id;
    private Integer user_id;//用户id
    private Integer product_id;//商品id
    private String product_name;//商品名称
    private String product_photopath;//商品图片
    private Double price;//商品单价
    private Integer count;//商品数量
    private Double subtotal;//总计
}
