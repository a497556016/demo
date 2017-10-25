package com.weishop.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.weishop.pojo.User;
import com.weishop.service.IUserService;
import com.weishop.utils.PageUtils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author HeShaowei
 * @since 2017-10-17
 */
@Controller
@RequestMapping("//user")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/test")
	@ResponseBody
	public Object test(int current,int size) {
//		User user = new User();
//		user.setUserAccount("admin");
//		user.setUserName("何少伟");
//		user.insert();
//		user.delete(new EntityWrapper<>().eq("user_account", "admin"));
		Page<User> page = new Page<>(current, size);
		userService.selectPage(page);
		return page;
	}
}
