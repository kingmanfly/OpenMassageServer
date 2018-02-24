package com.massage.infosys.dao.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisDao {

	 private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 private final JedisPool jedisPool;
	 
	 
	 public RedisDao(JedisPoolConfig config,String ip, int port) {
	        jedisPool = new JedisPool(config,ip, port);
	    }
	 
	 public String set(String key ,String value){
		 Jedis jedis = null;
		 String result = "";
		 try{
			 jedis = jedisPool.getResource();
			 result  =jedis.set(key, value);
		 }catch(Exception e){
			 logger.error(e.getMessage(), e);
		 }finally{
			 jedis.close();
		 }
		 
		 return result;
	 }
	 
	 public String get(String key){
		 Jedis jedis = null;
		 String value ="";
		 try{
			 jedis = jedisPool.getResource();
			 value = jedis.get(key);
		 }catch(Exception e){
			 logger.error(e.getMessage(), e);
		 }finally{
			 jedis.close();
		 }
		 
		 return value;
	 }
	
	
}
