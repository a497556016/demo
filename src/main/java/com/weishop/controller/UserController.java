package com.weishop.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.google.common.collect.Maps;
import com.weishop.base.BaseController;
import com.weishop.base.BaseResponse;
import com.weishop.base.PageResponse;
import com.weishop.global.Const;
import com.weishop.pojo.User;
import com.weishop.service.IUserService;
import com.weishop.utils.PageUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
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
public class UserController extends BaseController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/test")
	@ResponseBody
	public PageResponse<User> test(int current,int size) {
		String s = session.getServletContext().getRealPath("");
		System.out.println(s);
//		User user = new User();
//		user.setUserAccount("admin");
//		user.setUserName("何少伟");
//		user.insert();
//		user.delete(new EntityWrapper<>().eq("user_account", "admin"));
		Page<User> page = new Page<>(current, size);
		userService.selectPage(page);
		return PageResponse.result(page);
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping("/loginOrRegister")
	@ResponseBody
	public BaseResponse<?> loginOrRegister(User user,String msgCode){
		EntityWrapper<User> wrapper = new EntityWrapper<>();
		
		//如果是手机登陆
		if(StringUtils.isNotEmpty(user.getPhone())) {
			//获取验证码
			Object objCode = session.getAttribute(Const.MESSAGE_CODE);
			if(null!=objCode) {
				String sysMsgCode = String.valueOf(objCode);
				if(!sysMsgCode.equals(msgCode.trim())) {
					return BaseResponse.error("短信验证码错误，请重试！",2);
				}
			}else {
				return BaseResponse.error("获取短信验证码失败，请重新获取！",3);
			}
			wrapper.eq(user.PHONE, user.getPhone());
			List<User> users = userService.selectList(wrapper);
			if(CollectionUtils.isEmpty(users)) {
				return BaseResponse.error("该手机不存在！");
				//手机注册
				/*userService.insert(user);
				return BaseResponse.result("注册成功！",4);*/
			}
		}else if(StringUtils.isNotEmpty(user.getUserAccount())&&StringUtils.isNotEmpty(user.getPassword())) {
			Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
			//查询用户
			wrapper.eq(user.USER_ACCOUNT, user.getUserAccount());
			List<User> users = userService.selectList(wrapper);
			if(CollectionUtils.isEmpty(users)) {
				/*return BaseResponse.error("该用户不存在！");*/
				//注册用户
				user.setSalt(UUID.randomUUID().toString());
				String password = md5PasswordEncoder.encodePassword(user.getPassword(), user.getSalt());
				user.setPassword(password);
				user.setUserName("游客登录");
				userService.insert(user);
			}else {
				User sysUser = users.get(0);
				if(!md5PasswordEncoder.isPasswordValid(sysUser.getPassword(), user.getPassword(), sysUser.getSalt())) {
					return BaseResponse.error("登陆失败，密码不匹配！");
				}
			}
		}else {
			return BaseResponse.error("请输入手机号码或者用户名、密码！");
		}
		
		return BaseResponse.result("登陆成功！",user);
	}
}
