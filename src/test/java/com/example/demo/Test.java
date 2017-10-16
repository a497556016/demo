package com.example.demo;

import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;

public class Test {
	private static RestTemplate template = new RestTemplate();
	public static void main(String[] args) {
		template.postForObject("http://localhost:8888/bus/refresh", Maps.newHashMap(), String.class);
	}
}
