package com.tjetc.domain;
/*
* 商品表
* */
public class Product {

    private Integer id;//编号
    private String name;//名称
    private Double price;//单价
    private Integer count;//库存量
    private String photopath;//商品图片
    private String briefly;//商品简介
    private String details;//商品详情
    private String type;//商品类型

    public Product() {
    }

    public Product(String name, Double price, Integer count, String photopath, String briefly, String details, String type) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.photopath = photopath;
        this.briefly = briefly;
        this.details = details;
        this.type = type;
    }

    public Product(Integer id, String name, Double price, Integer count, String photopath, String briefly, String details, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.photopath = photopath;
        this.briefly = briefly;
        this.details = details;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", photopath='" + photopath + '\'' +
                ", briefly='" + briefly + '\'' +
                ", details='" + details + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public Product(String name, Double price, Integer count, String briefly, String details, String type) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.briefly = briefly;
        this.details = details;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }

    public String getBriefly() {
        return briefly;
    }

    public void setBriefly(String briefly) {
        this.briefly = briefly;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
