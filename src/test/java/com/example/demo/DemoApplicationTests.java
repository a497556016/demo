package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
public class DemoApplicationTests {
	private static RestTemplate template = new RestTemplate();
	@Test
	public void contextLoads() {
//		template.postForObject("http://localhost:8888/bus/refresh", Maps.newHashMap(), String.class);
	}

}
