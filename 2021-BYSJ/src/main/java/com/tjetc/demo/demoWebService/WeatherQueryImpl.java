package com.tjetc.demo.demoWebService;


import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * 2.编写SEI实现类，此类作为webservice提供服务的类
 * 气温查询实现类
 * 注意：SEI实现类中至少要有一个非静态的公开方法作为webservice的服务方法。
 * public class 上边要加上@WebService
 */
@WebService
public class WeatherQueryImpl implements WeatherQuery {
    @Override
    public String queryNowTem() {
        System.out.println("外部系统访问到了--->WeatherQueryImpl.queryNowTem");
        return "45度";
    }

    @Override
    public List<String> query2Days() {
        System.out.println("外部系统访问到了--->WeatherQueryImpl.query2Days");
        List<String> days=new ArrayList<>();
        days.add("晴天");
        days.add("阴天");
        days.add("乌云密布");
        return days;
    }

    @Override
    public String select() {
        return null;
    }


}
