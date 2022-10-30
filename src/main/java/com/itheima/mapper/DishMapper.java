package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName DishMapper
 * @description:
 * @Author: Mo
 * @Date: 2022/10/26 13:21
 * @Version 17.0.1
 **/
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
