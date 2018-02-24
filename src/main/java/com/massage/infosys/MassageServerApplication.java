package com.massage.infosys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.massage.infosys.constant.Constant;
import com.yunpian.sdk.YunpianClient;

@SpringBootApplication
@MapperScan("com.massage.infosys.dao")
@EnableConfigurationProperties(Constant.class)
public class MassageServerApplication {

	//初始化clnt,使用单例方式
	private static YunpianClient clnt = new YunpianClient("2681f7072c6ade49ced5f1d573c7e184").init();
	
	public static void main(String[] args) {
		SpringApplication.run(MassageServerApplication.class, args);
	}

	public static YunpianClient getClnt() {
		return clnt;
	}	
}
