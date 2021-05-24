package com.tjetc.domain;

import java.util.Date;


/**
 * 订单表
 */
public class Order {
    private Integer id;//
    private Date OrderDate;//订单提交时间
    private Integer uid;//用户编号
    private Integer pid;//商品id
    private Integer pCount;//商品购买单件的数量数量
    private Double sumPCount;//单件商品数量的总价格  sumPCount=pCount*price
    private Double subtotal;//总计  用户购买总价格
    private String state;//订单支付情况
    //这里，使用在购物车中的商品详情，通过购物车id来获取
    private Integer myCart_id;//购物车的id

    private String flag_bit;//标志位，用于标志 用户是否给已提交的订单添加收货地址

    /*订单中商品的地址*/
    private String user_name;//收货人名称
    private String provinces_cities;//省市
    private String city_county;//市县
    private String tTown;//城镇，或者是所在的区
    private String detail;//详细地址
    private String user_phone;//用户联系方式
    private Date paymentDate;//订单支付的时间  payment：支付

    private Product product;
    private User user;
    private MyCat myCat;
    /*订单的状态*/
    private String order_status;
    //已经完成收货的订单是否评价
    private String order_evaluate;
    private String size;//尺码
    private String patten;//颜色
    private String brand;//商品品牌
    //Reasons for refund:退款原因
    private String reasonsRefund;//退款原因
    private String cargoStatus;//货物状态

    public Order() {
    }

    public Order(Integer id, Date orderDate, Integer uid, Integer pid, Integer pCount, Double sumPCount, Double subtotal, String state, Integer myCart_id, String flag_bit, String user_name, String provinces_cities, String city_county, String tTown, String detail, String user_phone, Date paymentDate, Product product, User user, MyCat myCat, String order_status, String order_evaluate, String size, String patten, String brand, String reasonsRefund, String cargoStatus) {
        this.id = id;
        OrderDate = orderDate;
        this.uid = uid;
        this.pid = pid;
        this.pCount = pCount;
        this.sumPCount = sumPCount;
        this.subtotal = subtotal;
        this.state = state;
        this.myCart_id = myCart_id;
        this.flag_bit = flag_bit;
        this.user_name = user_name;
        this.provinces_cities = provinces_cities;
        this.city_county = city_county;
        this.tTown = tTown;
        this.detail = detail;
        this.user_phone = user_phone;
        this.paymentDate = paymentDate;
        this.product = product;
        this.user = user;
        this.myCat = myCat;
        this.order_status = order_status;
        this.order_evaluate = order_evaluate;
        this.size = size;
        this.patten = patten;
        this.brand = brand;
        this.reasonsRefund = reasonsRefund;
        this.cargoStatus = cargoStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", OrderDate=" + OrderDate +
                ", uid=" + uid +
                ", pid=" + pid +
                ", pCount=" + pCount +
                ", sumPCount=" + sumPCount +
                ", subtotal=" + subtotal +
                ", state='" + state + '\'' +
                ", myCart_id=" + myCart_id +
                ", flag_bit='" + flag_bit + '\'' +
                ", user_name='" + user_name + '\'' +
                ", provinces_cities='" + provinces_cities + '\'' +
                ", city_county='" + city_county + '\'' +
                ", tTown='" + tTown + '\'' +
                ", detail='" + detail + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", paymentDate=" + paymentDate +
                ", product=" + product +
                ", user=" + user +
                ", myCat=" + myCat +
                ", order_status='" + order_status + '\'' +
                ", order_evaluate='" + order_evaluate + '\'' +
                ", size='" + size + '\'' +
                ", patten='" + patten + '\'' +
                ", brand='" + brand + '\'' +
                ", reasonsRefund='" + reasonsRefund + '\'' +
                ", cargoStatus='" + cargoStatus + '\'' +
                '}';
    }

    public String getReasonsRefund() {
        return reasonsRefund;
    }

    public void setReasonsRefund(String reasonsRefund) {
        this.reasonsRefund = reasonsRefund;
    }

    public String getCargoStatus() {
        return cargoStatus;
    }

    public void setCargoStatus(String cargoStatus) {
        this.cargoStatus = cargoStatus;
    }

    public String getOrder_evaluate() {
        return order_evaluate;
    }

    public void setOrder_evaluate(String order_evaluate) {
        this.order_evaluate = order_evaluate;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPatten() {
        return patten;
    }

    public void setPatten(String patten) {
        this.patten = patten;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public MyCat getMyCat() {
        return myCat;
    }

    public void setMyCat(MyCat myCat) {
        this.myCat = myCat;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFlag_bit() {
        return flag_bit;
    }

    public void setFlag_bit(String flag_bit) {
        this.flag_bit = flag_bit;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Integer getMyCart_id() {
        return myCart_id;
    }

    public void setMyCart_id(Integer myCart_id) {
        this.myCart_id = myCart_id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getProvinces_cities() {
        return provinces_cities;
    }

    public void setProvinces_cities(String provinces_cities) {
        this.provinces_cities = provinces_cities;
    }

    public String getCity_county() {
        return city_county;
    }

    public void setCity_county(String city_county) {
        this.city_county = city_county;
    }

    public String gettTown() {
        return tTown;
    }

    public void settTown(String tTown) {
        this.tTown = tTown;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getSumPCount() {
        return sumPCount;
    }

    public void setSumPCount(double sumPCount) {
        this.sumPCount = sumPCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }

    public void setSumPCount(Double sumPCount) {
        this.sumPCount = sumPCount;
    }

    public Integer getpCount() {
        return pCount;
    }

    public void setpCount(Integer pCount) {
        this.pCount = pCount;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
