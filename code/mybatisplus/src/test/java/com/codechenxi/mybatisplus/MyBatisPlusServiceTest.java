package com.codechenxi.mybatisplus;

import com.codechenxi.mybatisplus.pojo.User;
import com.codechenxi.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author codechenxi
 * @since 2025-07-15 15:53
 */

@SpringBootTest
public class MyBatisPlusServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void testGetCount(){

        // 查询总记录数
        // SELECT COUNT( * ) AS total FROM user
        long count = userService.count();
        System.out.println("总记录数:" + count);
    }

    @Test
    public void testInsertMore(){

        // 批量添加
        //  INSERT INTO user ( id, name, age ) VALUES ( ?, ?, ? )
        List<User> users = new ArrayList<>();
        for (int i =1;i <= 10;i++){
            User user = new User();
            user.setName("codechenxi" + i);
            user.setAge(20 + i);

            users.add(user);
        }
        boolean b = userService.saveBatch(users);
        System.out.println(b);
    }
}
