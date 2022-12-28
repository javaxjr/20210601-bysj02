package com.tjetc.test;

import com.tjetc.config.AdminConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AdminTest {

    @Test
    public void getAdmin(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AdminConfig.class);
    }

}
