package com.zitai.mall.service;

import com.zitai.mall.pojo.User;
import com.zitai.mall.vo.ResponseVo;

/**
 *
 */
public interface IUserService {

	/**
	 * 注册
	 */
	ResponseVo<User> register(User user);

	/**
	 * 登录
	 */
	ResponseVo<User> login(String username, String password);
}
