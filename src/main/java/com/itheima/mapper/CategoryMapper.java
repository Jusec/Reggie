package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName CategoryMapper
 * @description:
 * @Author: Mo
 * @Date: 2022/10/26 13:45
 * @Version 17.0.1
 **/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
