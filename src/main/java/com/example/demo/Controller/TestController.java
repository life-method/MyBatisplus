package com.example.demo.Controller;


import com.example.demo.common.ResponseResult;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
     @Autowired
     private UserService userService ;

    @RequestMapping("/abc")
    @PreAuthorize("hasAuthority('system:dept:list')")
    public ResponseResult testOne(){
        User one = userService.getOne(null);
        return new ResponseResult<User>(200,"测试成功",one);
    }


    /**
     * 自定义方法进行验证这个权限
     * @return
     */
    @RequestMapping("/hello")
    @PreAuthorize("@ex.hasAuthority('system:dept:list')")
    public String hello(){
        return "hello";
    }
}
