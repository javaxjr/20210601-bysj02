package com.tjetc.config;

import com.tjetc.domain.Admin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@ComponentScan("com.tjetc.domain.*")
public class AdminConfig {

    /*
    * @Lazy(value = false) 或者 @Lazy(false) 那么对象会在初始化的时候被创建

@Lazy注解注解的作用主要是减少springIOC容器启动的加载时间
    * */
    @Lazy//懒加载
    @Bean(name = "adminDemo")
    public Admin getAdmin() {
        Admin admin = new Admin();
        admin.setUsername("123456");
        admin.setPassword("654321");
        return admin;
    }

}
