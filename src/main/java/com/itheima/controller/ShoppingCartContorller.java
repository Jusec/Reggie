package com.itheima.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.common.BaseContext;
import com.itheima.common.R;
import com.itheima.entity.ShoppingCart;
import com.itheima.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName DishContorller
 * @description:
 * @Author: Mo
 * @Date: 2022/10/26 13:22
 * @Version 17.0.1
 **/
@Slf4j
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartContorller {

    @Autowired
    private ShoppingCartService shoppingCartService;


    /**
     * 添加购物车
     *
     * @param shoppingCart
     * @return
     */
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart) {
        log.info("购物车数据：", shoppingCart);

        Long currentId = BaseContext.getCurrentId();

        shoppingCart.setUserId(currentId);

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(ShoppingCart::getUserId, currentId);
        queryWrapper.eq(shoppingCart.getDishId() != null, ShoppingCart::getDishId, shoppingCart.getDishId());
        queryWrapper.eq(shoppingCart.getSetmealId() != null, ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        ShoppingCart one = shoppingCartService.getOne(queryWrapper);

        if (one != null) {
            Integer number = one.getNumber() + 1;
            one.setNumber(number);
            shoppingCartService.updateById(one);
        } else {
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.save(shoppingCart);
            one = shoppingCart;
        }
        return R.success(one);
    }

    /**
     * 减少购物车菜品或套餐
     * @param shoppingCart
     * @return ShoppingCart
     * @Author: Mo
     */
    @PostMapping("/sub")
    public R<ShoppingCart> sub(@RequestBody ShoppingCart shoppingCart) {

        Long currentId = BaseContext.getCurrentId();
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, currentId);


        queryWrapper.eq(shoppingCart.getSetmealId() != null, ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        queryWrapper.eq(shoppingCart.getDishId() != null, ShoppingCart::getDishId, shoppingCart.getDishId());
        ShoppingCart cart = shoppingCartService.getOne(queryWrapper);

        Integer number = cart.getNumber();
        if (number > 1){
            // 修改
            cart.setNumber(number - 1);
            shoppingCartService.updateById(cart);
        }else{
            shoppingCartService.removeById(cart.getId());
        }
        return R.success(cart);
    }


    /**
     * 减少购物车菜品或套餐
     * @return
     */
//    @PostMapping("/sub")
//    public R<ShoppingCart> sub(@RequestBody ShoppingCart shoppingCart){
//        Long currentId = BaseContext.getCurrentId();
//        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(ShoppingCart::getUserId, currentId);
//        ShoppingCart cart = null;
//
//        // 获取要减少的菜品或套餐信息
//        Long dishId = shoppingCart.getDishId();
//        if(dishId == null){
//            // 减少的是套餐数量
//            Long setmealId = shoppingCart.getSetmealId();
//            queryWrapper.eq(ShoppingCart::getSetmealId, setmealId);
//            cart = shoppingCartService.getOne(queryWrapper);
//        } else {
//            // 减少的是菜品数量
//            queryWrapper.eq(ShoppingCart::getDishId, dishId);
//            cart = shoppingCartService.getOne(queryWrapper);
//        }
//
//        Integer number = cart.getNumber();
//        if(number > 1){
//            cart.setNumber(number - 1);
//            shoppingCartService.updateById(cart);
//        } else {
//            shoppingCartService.removeById(cart.getId());
//        }
//
//        return R.success(cart);
//    }




    /**
     * 查看购物车
     *
     * @return
     */
    @GetMapping("/list")
    public R<List<ShoppingCart>> list() {
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
        queryWrapper.orderByAsc(ShoppingCart::getCreateTime);
        List<ShoppingCart> list = shoppingCartService.list(queryWrapper);

        return R.success(list);
    }


    /**
     * 清空购物车
     *
     * @return
     */
    @DeleteMapping("/clean")
    public R<String> clean() {
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
        shoppingCartService.remove(queryWrapper);

        return R.success("清空成功！");
    }


}
