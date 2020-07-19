package com.zitai.mall.service;

import com.zitai.mall.form.CartAddForm;
import com.zitai.mall.form.CartUpdateForm;
import com.zitai.mall.pojo.Cart;
import com.zitai.mall.vo.CartVo;
import com.zitai.mall.vo.ResponseVo;

import java.util.List;

/**
 * @data 2020/3/29 - 下午3:58
 * 描述：
 */
public interface ICartService {
    ResponseVo<CartVo> add(Integer uid, CartAddForm form);

    ResponseVo<CartVo> list(Integer uid);

    ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateForm from);

    ResponseVo<CartVo> delete(Integer uid, Integer productId);

    ResponseVo<CartVo> selectAll(Integer uid);

    ResponseVo<CartVo> unSelectAll(Integer uid);

    ResponseVo<Integer> sum(Integer uid);

    List<Cart> listForCart(Integer uid);
}
