package com.itheima.common;

/**
 * @ClassName CustomException
 * @description:
 * @Author: Mo
 * @Date: 2022/10/26 16:43
 * @Version 17.0.1
 **/

public class CustomException extends RuntimeException{
    public CustomException (String msg){
        super(msg);
    }
}
