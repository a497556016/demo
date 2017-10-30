package com.weishop;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.weishop.config.CorsConfigurerAdapter;
import com.weishop.filter.HttpAuthFilter;
import com.google.common.collect.Lists;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties
@EnableTransactionManagement
@EnableDiscoveryClient
public class WeiShopApplication {
	@Value("${http-auth.url-patterns}")
	private String urlPatternsStr;

	public static void main(String[] args) {
		SpringApplication.run(WeiShopApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean getFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new HttpAuthFilter());
		
		if(StringUtils.isNotEmpty(urlPatternsStr)) {
			List<String> urlPatterns = Lists.newArrayList(urlPatternsStr.split(","));
			registrationBean.setUrlPatterns(urlPatterns);
		}
		
		return registrationBean;
	}
	
	/*@Bean
	public CorsConfigurerAdapter getCorsConfigurerAdapter() {
		return new CorsConfigurerAdapter();
	}*/
//	
//	@Autowired
//	public void setEnviroment(Environment env) {
//	    System.out.println("my url-patterns from env: " 
//	        + env.getProperty("http-auth.url-patterns"));
//	}
}
