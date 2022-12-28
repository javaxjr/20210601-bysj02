package com.tjetc.config;

import com.tjetc.service.WxMpService;
import com.tjetc.service.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {

    @Value("${wx_appid}")
    public String appid;

    protected MainConfig(){}

    @Bean
    public WxMpService WxMpService(){
        WxMpService wxMpService=new WxMpServiceImpl();

        return wxMpService;
    }

}
