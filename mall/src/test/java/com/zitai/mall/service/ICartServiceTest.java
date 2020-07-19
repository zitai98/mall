package com.zitai.mall.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zitai.mall.MallApplicationTests;
import com.zitai.mall.form.CartAddForm;
import com.zitai.mall.form.CartUpdateForm;
import com.zitai.mall.vo.CartVo;
import com.zitai.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.junit.Assert.*;

/**
 * @data 2020/3/29 - 下午4:40
 * 描述：
 */
@Slf4j
public class ICartServiceTest extends MallApplicationTests {

    @Autowired
    private ICartService cartService;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Test
    public void add() {
        CartAddForm form = new CartAddForm();
        form.setProductId(28);
        form.setSelected(true);
        cartService.add(1, form);

    }

    @Test
    public void list(){
        ResponseVo<CartVo> list = cartService.list(1);
        log.info("list{}", gson.toJson(list));
    }

    @Test
    public void update(){
        CartUpdateForm from = new CartUpdateForm();
        from.setQuantity(5);
        from.setSelected(false);
        ResponseVo<CartVo> responseVo = cartService.update(1, 26, from);
        log.info("list{}", gson.toJson(responseVo));

    }

    @Test
    public void delete(){
        ResponseVo<CartVo> responseVo = cartService.delete(1, 27);
        log.info("list{}", gson.toJson(responseVo));
    }

    @Test
    public void selectAll(){
        ResponseVo<CartVo> responseVo = cartService.selectAll(1);
        log.info("list{}", gson.toJson(responseVo));
    }

    @Test
    public void unSelectAll(){
        ResponseVo<CartVo> responseVo = cartService.unSelectAll(1);
        log.info("list{}", gson.toJson(responseVo));
    }

    @Test
    public void sum(){
        ResponseVo<Integer> responseVo = cartService.sum(1);
        log.info("list{}", gson.toJson(responseVo));
    }
}