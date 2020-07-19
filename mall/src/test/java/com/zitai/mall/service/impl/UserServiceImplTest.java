package com.zitai.mall.service.impl;

import com.zitai.mall.MallApplicationTests;
import com.zitai.mall.enums.ResponseEnum;
import com.zitai.mall.enums.RoleEnum;
import com.zitai.mall.pojo.User;
import com.zitai.mall.service.IUserService;
import com.zitai.mall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 */
@Transactional
public class UserServiceImplTest extends MallApplicationTests {

	public static final String USERNAME="jack";
	public static final String PASSWORD="123456";

	@Autowired
	private IUserService userService;

	@Test
	public void register() {
		User user = new User(USERNAME, PASSWORD , "jack@qq.com", RoleEnum.CUSTOMER.getCode());
		userService.register(user);
	}

	@Test
	public void login(){
		register();
		ResponseVo<User> login = userService.login(USERNAME, PASSWORD);
		Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),login.getStatus());
	}
}