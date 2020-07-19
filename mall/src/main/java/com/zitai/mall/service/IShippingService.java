package com.zitai.mall.service;

import com.github.pagehelper.PageInfo;
import com.zitai.mall.form.ShippingForm;
import com.zitai.mall.vo.ResponseVo;

import java.util.Map;

/**
 * @data 2020/3/30 - 上午9:57
 * 描述：
 */
public interface IShippingService {
    ResponseVo<Map<String, Integer>> add(Integer uid, ShippingForm form);

    ResponseVo delete(Integer uid, Integer ShippingId);

    ResponseVo update(Integer uid, Integer shippingId, ShippingForm form);

    ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize);

}
