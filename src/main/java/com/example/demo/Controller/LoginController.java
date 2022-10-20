package com.example.demo.Controller;

import com.example.demo.common.ResponseResult;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        return userService.login(user);
    }


    @GetMapping("/user/logout")
    public ResponseResult exit(){
        return userService.logout();
    }

}
