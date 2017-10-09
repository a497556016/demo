package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.pojo.UserInfo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class UserInfoService {
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Transactional
	public Page<UserInfo> selectUser(){
		Page<UserInfo> page = PageHelper.startPage(1, 5);
		userInfoMapper.selectAll();
		return page;
	}

	public UserInfo selectByPriKey(Integer id) {
		return userInfoMapper.selectByPriKey(id);
	}
}
