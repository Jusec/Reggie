package com.itheima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.dto.SetmealDto;
import com.itheima.entity.Setmeal;

import java.util.List;

/**
 * @ClassName FoodService
 * @description:
 * @Author: Mo
 * @Date: 2022/10/26 13:19
 * @Version 17.0.1
 **/

public interface SetmealService extends IService<Setmeal> {

    public void saveWithDish(SetmealDto setmealDto);

    public void removeWithDish(List<Long> ids);


}
