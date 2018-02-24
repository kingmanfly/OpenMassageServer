package com.massage.infosys.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	@Autowired
	OperationInterceptor operationInterceptor;
	
	public WebConfig() {
		super();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("11");
		this.applicationContext = applicationContext;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦
		
		// 拦截规则：除了login，其他都拦截判断
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
			.excludePathPatterns("/admin/login",
					"/admin/check_login",
					"/admin/auditing-pass",
					"/admin/auditing-reject");
		registry.addInterceptor(operationInterceptor)
			.addPathPatterns("/admin/auditing-pass",
					"/admin/auditing-reject");
		super.addInterceptors(registry);
	}

}
