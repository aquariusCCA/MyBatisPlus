package com.codechenxi.mybatisx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codechenxi.mybatisx.entity.User;
import com.codechenxi.mybatisx.service.UserService;
import com.codechenxi.mybatisx.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author xiaoshilin
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2025-08-22 17:21:56
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




