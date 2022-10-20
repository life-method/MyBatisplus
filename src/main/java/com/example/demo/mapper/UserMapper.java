package com.example.demo.mapper;

import com.example.demo.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author xiao
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2022-10-19 17:07:18
* @Entity com.example.demo.pojo.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {

}




