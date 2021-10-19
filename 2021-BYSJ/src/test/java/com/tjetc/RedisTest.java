package com.tjetc;

import com.tjetc.demo.redis.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RedisTest {

    @Test
    public void testJedisClient() throws Exception {
        //初始化Spring容器
        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("F:\\demo\\20210601-bysj\\2021-BYSJ\\src\\main\\java\\com\\tjetc\\demo\\redis\\applicationContext-redis.xml");
        //从容器中获得JedisClient对象
       /* JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
        jedisClient.set("first", "100");
        String result = jedisClient.get("first");
        System.out.println(result);*/
    }

}
