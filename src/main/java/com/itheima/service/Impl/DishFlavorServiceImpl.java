package com.itheima.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.entity.DishFlavor;
import com.itheima.mapper.DishFlavorMapper;
import com.itheima.service.DishFlavorService;
import org.springframework.stereotype.Service;

/**
 * @ClassName DishServiceImpl
 * @description:
 * @Author: Mo
 * @Date: 2022/10/26 13:20
 * @Version 17.0.1
 **/

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
