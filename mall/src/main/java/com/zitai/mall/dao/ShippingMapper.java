package com.zitai.mall.dao;

import com.zitai.mall.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    int deleteByIdAndUid(@Param("uid") Integer id, @Param("shippingId") Integer shippingId);

    List<Shipping> selectByUid(Integer id);

    Shipping selectByUidAndShippingId(@Param("uid") Integer uid, @Param("shippingId") Integer shippingId);

    List<Shipping> selectByIdSet(@Param("idSet") Set idSet);

}