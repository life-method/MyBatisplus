package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.pojo.Menu;
import com.example.demo.service.MenuService;
import com.example.demo.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
* @author xiao
* @description 针对表【sys_menu(菜单表)】的数据库操作Service实现
* @createDate 2022-10-19 16:11:38
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

}




