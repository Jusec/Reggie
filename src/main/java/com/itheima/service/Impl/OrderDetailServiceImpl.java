package com.itheima.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.entity.OrderDetail;
import com.itheima.mapper.OrderDetailMapper;
import com.itheima.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * @ClassName DishServiceImpl
 * @description:
 * @Author: Mo
 * @Date: 2022/10/26 13:20
 * @Version 17.0.1
 **/
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
