package com.itheima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.entity.Category;

/**
 * @ClassName FoodService
 * @description:
 * @Author: Mo
 * @Date: 2022/10/26 13:19
 * @Version 17.0.1
 **/

public interface CategoryService extends IService<Category> {
    public void remove(Long ids);
}
