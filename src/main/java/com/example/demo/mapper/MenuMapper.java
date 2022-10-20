package com.example.demo.mapper;

import com.example.demo.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author xiao
* @description 针对表【sys_menu(菜单表)】的数据库操作Mapper
* @createDate 2022-10-19 16:11:38
* @Entity com.example.demo.pojo.Menu
*/
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long id);

}




