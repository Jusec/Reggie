package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName EmployeeMapper
 * @description:
 * @Author: Mo
 * @Date: 2022/10/25 16:13
 * @Version 17.0.1
 **/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
