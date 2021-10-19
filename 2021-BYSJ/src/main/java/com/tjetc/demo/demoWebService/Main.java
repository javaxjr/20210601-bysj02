package com.tjetc.demo.demoWebService;


import javax.xml.ws.Endpoint;

/**
 * 3.endpoint发布服务
 */
public class Main {
    public static void main(String[] args) {
        //发布我们设计的天气预报
        Endpoint.publish("http://127.0.0.1:10086/SzWeather",new WeatherQueryImpl());
    }
}
