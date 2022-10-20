package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.JwtUtil;
import com.example.demo.common.RedisCache;
import com.example.demo.common.ResponseResult;
import com.example.demo.pojo.LoginUser;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author xiao
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 * @createDate 2022-10-15 17:14:29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("改账号密码错误");
        }

        //在mp返回来的对象封装在这里，然后强转成为LoginUser对象
        //Principal 封装了mp的LoginUser对象
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        String userId = loginUser.getUser().getId().toString();

        String jwt = JwtUtil.createJWT(userId);
        redisCache.setCacheObject("login:" + userId,loginUser);


        return new ResponseResult(200, "登录成功",jwt);
    }

    @Override
    public ResponseResult logout() {
        //说白了就是通过验证的话会在这个线程里面存东西
        //现在拿出来用
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser principal = (LoginUser) authentication.getPrincipal();
        Long id = principal.getUser().getId();
        redisCache.deleteObject("login:"+id);
        return new ResponseResult(200,"退出成功了");
    }
}




