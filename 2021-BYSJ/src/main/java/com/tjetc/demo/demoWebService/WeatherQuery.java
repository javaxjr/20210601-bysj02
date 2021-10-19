package com.tjetc.demo.demoWebService;


import java.util.List;


/**
 * 1.编写SEI(Service Endpoint Interface)，
 * SEI在webservice中称为portType，在java中称为接口。
 * 气温查询接口
 */
public interface WeatherQuery {

    //查询当前的气温
    public String queryNowTem();
    //2.查询某个城市未来两天的气温
    public List<String> query2Days();

    public String select();
}
