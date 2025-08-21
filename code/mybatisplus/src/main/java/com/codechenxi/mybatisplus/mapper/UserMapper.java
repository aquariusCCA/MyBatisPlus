package com.codechenxi.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codechenxi.mybatisplus.pojo.User;

import java.util.Map;

/**
 * @author codechenxi
 * @since 2025-07-15 10:16
 */

public interface UserMapper  extends BaseMapper<User> {


    /**
     * 根据id查询用户信息为map集合
     * @param id
     * @return
     */
    Map<String,Object> selectMapById(Long id);
}
