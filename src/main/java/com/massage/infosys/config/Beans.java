package com.massage.infosys.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPoolConfig;

import com.massage.infosys.dao.cache.RedisDao;

@Configuration
public class Beans {

    @Bean
    public RedisDao redisDao(@Value("${spring.redis.host}") String ip, @Value("${spring.redis.port}") Integer port,
    		@Value("${spring.redis.max-active}")int total,@Value("${spring.redis.max-idle}") int del){
    	JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    	jedisPoolConfig.setMaxTotal(total);
    	jedisPoolConfig.setMaxIdle(del);
    	
        return new RedisDao(jedisPoolConfig,ip,port);
    }
}
