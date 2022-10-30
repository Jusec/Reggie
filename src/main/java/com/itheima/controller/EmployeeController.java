package com.itheima.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.common.R;
import com.itheima.entity.Employee;
import com.itheima.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @ClassName EmployeeController
 * @description:
 * @Author: Mo
 * @Date: 2022/10/25 16:17
 * @Version 17.0.1
 **/
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        // 1.将页面提交的密码就行md5加密处理
        String userName = employee.getUsername();
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // 2.根据页面提交的用户名查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, userName);

        Employee one = employeeService.getOne(queryWrapper);

        // 3.如果没有查询到则返回失败
        if (one == null) {
            return R.error("登录失败！");
        }

        if (!one.getPassword().equals(password)) {
            return R.error("密码错误！");
        }

        if (one.getStatus() == 0) {
            return R.error("账号已被禁用！");
        }

        request.getSession().setAttribute("employee", one.getId());
        return R.success(one);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return R.success("退出成功！");
    }


    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        Page pageInof = new Page(page, pageSize);
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Employee::getName, name);

        queryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInof, queryWrapper);
        return R.success(pageInof);
    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee){
        String password = DigestUtils.md5DigestAsHex("123456".getBytes());
        employee.setPassword(password);
        employeeService.save(employee);
        return R.success("添加成功！");
    }


    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee){

        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser((long)request.getSession().getAttribute("employee"));

        employeeService.updateById(employee);

        return R.success("修改成功！");
    }

    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable long id) {
        Employee byId = employeeService.getById(id);
        if (byId != null){
            return R.success(byId);
        }
        return R.error("查找失败！");
    }

}
