package com.zitai.mall.service;

import com.github.pagehelper.PageInfo;
import com.zitai.mall.vo.OrderVo;
import com.zitai.mall.vo.ResponseVo;

/**
 * @data 2020/3/30 - 下午4:47
 * 描述：
 */
public interface IOrderService {

    ResponseVo<OrderVo> create(Integer uid, Integer shippingId);

    ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize);

    ResponseVo<OrderVo> detail(Integer uid, Long orderNo);

    ResponseVo cancel(Integer uid, Long orderNo);

    void paid(Long orderNo);
}
