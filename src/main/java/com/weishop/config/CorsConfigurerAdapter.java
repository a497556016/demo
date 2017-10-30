package com.weishop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 跨域请求配置
 * HeShaowei create at 2017年10月26日
 *
 */
@Configuration
public class CorsConfigurerAdapter extends WebMvcConfigurerAdapter {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")  
        .allowedOrigins("*")  
        .allowCredentials(true)  
        .allowedMethods("GET", "POST", "DELETE", "PUT")  
        .maxAge(3600);  
	}
}
