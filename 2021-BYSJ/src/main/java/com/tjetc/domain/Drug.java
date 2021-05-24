package com.tjetc.domain;

/**
 * 药品表
 */
public class Drug {
    private int id;//编号
    private String name;//名称
    private double price;//数量
    private String type;//规格
    private String indications;//适应症
    private String photopath;//图片

    public Drug() {
    }

    public Drug(String name, double price, String type, String indications, String photopath) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.indications = indications;
        this.photopath = photopath;
    }

    public Drug(int id, String name, double price, String type, String indications, String photopath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.indications = indications;
        this.photopath = photopath;
    }

    public Drug(String name, double price, String type, String indications) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.indications = indications;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", indications='" + indications + '\'' +
                ", photopath='" + photopath + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndications() {
        return indications;
    }

    public void setIndications(String indications) {
        this.indications = indications;
    }

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }
}
