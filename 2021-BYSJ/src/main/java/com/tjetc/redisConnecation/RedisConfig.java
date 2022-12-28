package com.tjetc.redisConnecation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@Component
//@Configuration
public class RedisConfig {
    @Autowired
    private RedisProperties redisProperties;

    //@Value("${spring.redis.cluster.nodes}")
    private String nodes = "192.168.58.128:7000,192.168.58.128:7001,192.168.58.128:7002,192.168.58.128:7003,192.168.58.128:7004,192.168.58.128:7005";

    //@Value("${spring.redis.cluster.command-timeout}")
    private String timeout = "101000";

    //@Value("${spring.redis.cluster.expire-seconds}")
    private String seconds = "120";


    @Bean
    public JedisCluster getJedisCluster() {

        System.out.println("nodes = " + nodes);

        //获取redis集群的ip及端口号等相关信息；
        String[] serverArray = nodes.split(",");
        Set<HostAndPort> nodes = new HashSet<>();

        //遍历add到HostAndPort中；
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            System.out.println("ipPort = " + ipPortPair[0].trim() + Integer.valueOf(ipPortPair[1].trim()));

            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }
        //构建对象并返回；
        System.out.println("nodes.toString() = " + nodes.toString());
        //return new JedisCluster(nodes, redisProperties.getCommandTimeout());
        return new JedisCluster(nodes, Integer.parseInt(timeout));
    }

}
