package com.codechenxi.mybatisx.mapper;
import java.util.List;

import com.codechenxi.mybatisx.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author xiaoshilin
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2025-08-22 17:21:56
* @Entity com.codechenxi.mybatisx.entity.User
*/
public interface UserMapper extends BaseMapper<User> {
    List<User> selectAge();
}




