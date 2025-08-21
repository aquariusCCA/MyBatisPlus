package com.codechenxi.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codechenxi.mybatisplus.mapper.UserMapper;
import com.codechenxi.mybatisplus.pojo.User;
import com.codechenxi.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author codechenxi
 * @since 2025-07-15 15:39
 */

@Service
public class UserServiceImpl   extends ServiceImpl<UserMapper, User> implements UserService {
}
