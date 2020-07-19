package com.zitai.mall.dao;

import com.zitai.mall.MallApplicationTests;
import com.zitai.mall.pojo.Order;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 廖师兄
 */
public class OrderMapperTest extends MallApplicationTests {

	@Autowired
	private OrderMapper orderMapper;

	@Test
	public void deleteByPrimaryKey() {
	}

	@Test
	public void insert() {
	}

	@Test
	public void insertSelective() {
	}

	@Test
	public void selectByPrimaryKey() {
		Order order = orderMapper.selectByPrimaryKey(1);
		System.out.println(order.toString());
	}

	@Test
	public void updateByPrimaryKeySelective() {
	}

	@Test
	public void updateByPrimaryKey() {
	}
}