package com.example.demo.service;

import com.example.demo.common.ResponseResult;
import com.example.demo.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author xiao
 * @description 针对表【sys_user(用户表)】的数据库操作Service
 * @createDate 2022-10-19 17:07:18
 */
public interface UserService extends IService<User> {
    ResponseResult login(User user);

    ResponseResult logout();
}
