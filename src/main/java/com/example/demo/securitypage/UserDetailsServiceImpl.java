package com.example.demo.securitypage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.MenuMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.LoginUser;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    //这个是替换最下面的查询

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //利用页面传过来的userName 去仓库中查询出来账号和密码返回去
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        User user = userMapper.selectOne(queryWrapper);

        if (Objects.isNull(user)) {
            throw new RuntimeException("没有这个用户数据");
        }
        List<String> list = menuMapper.selectPermsByUserId(user.getId());
        //TODO 根据用户查询权限信息 添加到LoginUser中
//        List<String> list = new ArrayList<>(Arrays.asList("test1", "one","test10"));
//        LoginUser loginUser = new LoginUser(user, list);
        LoginUser loginUser = new LoginUser(user,list);

        return loginUser;
    }
}
