package com.zitai.mall.service.impl;

import com.zitai.mall.dao.UserMapper;
import com.zitai.mall.enums.ResponseEnum;
import com.zitai.mall.enums.RoleEnum;
import com.zitai.mall.pojo.User;
import com.zitai.mall.service.IUserService;
import com.zitai.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 *
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 注册
	 *
	 * @param user
	 */
	@Override
	public ResponseVo<User> register(User user) {
		//username不能重复
		int countByUsername = userMapper.countByUsername(user.getUsername());
		if (countByUsername > 0) {
			return ResponseVo.error(ResponseEnum.USERNAME_EXIST);
		}

		//email不能重复
		int countByEmail = userMapper.countByEmail(user.getEmail());
		if (countByEmail > 0) {
			return ResponseVo.error(ResponseEnum.USEREMAIL_EXIST);
		}
		//设置用户角色
		user.setRole(RoleEnum.CUSTOMER.getCode());
		//MD5摘要算法(Spring自带)
		user.setPassword(DigestUtils.md5DigestAsHex(
				user.getPassword().getBytes(StandardCharsets.UTF_8)
		));

		//写入数据库
		int resultCount = userMapper.insertSelective(user);
		if (resultCount == 0) {
			return ResponseVo.error(ResponseEnum.ERROR);
		}

		return ResponseVo.success();
	}

	/**
	 * 登录
	 *
	 * @param username
	 * @param password
	 */
	@Override
	public ResponseVo<User> login(String username, String password) {
		User user = userMapper.selectByUsername(username);
		if(user == null){
			return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
			//用户不存在
		}

		if(!(user.getPassword().equalsIgnoreCase(
				DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8))))){
			//用户名或密码错误
			return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
		}

		user.setPassword("");
		return ResponseVo.success(user);
	}
}
