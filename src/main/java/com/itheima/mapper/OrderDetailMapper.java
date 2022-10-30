package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName OrdersMapper
 * @description:
 * @Author: Mo
 * @Date: 2022/10/26 13:46
 * @Version 17.0.1
 **/
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}
