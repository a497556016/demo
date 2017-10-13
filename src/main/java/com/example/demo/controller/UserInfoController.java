package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.pojo.UserInfo;
import com.example.demo.service.UserInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
	@Autowired
	private UserInfoService userInfoService;
	
	@Value("${spring.datasource.driverClassName}")
	private String configValue;
	
	@RequestMapping("/findById")
	@ResponseBody
	public Object getUserInfoById(Integer id) {
		System.out.println(configValue);
		UserInfo userInfo = userInfoService.selectByPriKey(id);
		return userInfo;
	}
	
	@RequestMapping("/listUser")
	@ResponseBody
	public Object listUser() {
		return userInfoService.selectUser();
	}
	
	@Value("${server.port}")
	private String port;
	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		return port;
	}
 }
