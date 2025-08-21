package com.codechenxi.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.codechenxi.mybatisplus.mapper.UserMapper;
import com.codechenxi.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author codechenxi
 * @description
 * @since 2025-07-16 15:42
 */

@SpringBootTest
public class MyBatisPlusWrapperTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void test01() {

        //  查询用户名包含 a，年龄 20 到 30 之间，邮箱信息不为 null 的用户信息
        //  SELECT uid AS id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (username LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("username", "a")
                .between("age", 20, 30)
                .isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }


    @Test
    public void test02(){

        //  查询用户信息，按照年龄的降序排序，若年龄相同，则按照 id 升序排序
        //  SELECT uid AS id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 ORDER BY age DESC,uid ASC
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("age")
                .orderByAsc("uid");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }


    @Test
    public void test03(){

        //  删除邮箱地址为 null 的用户信息
        //  UPDATE t_user SET is_deleted=1 WHERE is_deleted=0 AND (email IS NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .isNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println("result:" + result);

    }


    @Test
    public void test04(){

        //  将（年龄大于20并且用户名中包含有a）或邮箱为null的用户信息修改
        //  UPDATE t_user SET username=?, email=? WHERE is_deleted=0 AND (age > ? AND username LIKE ? OR email IS NULL)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.and(wrapper -> wrapper.gt("age", 20).like("username", "a"))
//                .or()
//                .isNull("email");
        queryWrapper
                .gt("age", 20)
                .like("username", "a")
                .or()
                .isNull("email");

        User user = new User();
        user.setName("小明");
        user.setEmail("test@codechenxi.com");

        int result = userMapper.update(user,queryWrapper);
        System.out.println("result:" + result);

    }


    @Test
    public void test05(){

        //  将用户名中包含有 a 并且（年龄大于 20 或邮箱为 null）的用户信息修改
        //  UPDATE t_user SET username=?, email=? WHERE is_deleted=0 AND (username LIKE ? AND (age > ? OR email IS NULL))
        //  lambda 中的条件优先执行
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
               .like("username", "a")
               .and(wrapper -> wrapper.gt("age", 20).or().isNull("email"));

        User user = new User();
        user.setName("小红");
        user.setEmail("test@codechenxi.com");

        int result = userMapper.update(user, queryWrapper);
        System.out.println("result:" + result);

    }


    @Test
    public void test06(){

        //  查询用户的用户名、年龄、邮箱信息
        //  SELECT username,age,email FROM t_user WHERE is_deleted=0
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username", "age", "email");

        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);

    }

    @Test
    public void test07(){

        //  查询 id 小于等于 100 的用户信息
        //  SELECT uid AS id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (uid IN (select uid from t_user where uid <= 100))
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .inSql("uid","select uid from t_user where uid <= 100");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }


    @Test
    public void test08(){

        //  将用户名中包含有 a 并且（年龄大于 20 或邮箱为 null）的用户信息修改
        //  UPDATE t_user SET username=?,email=? WHERE is_deleted=0 AND (username LIKE ? AND (age > ? OR email IS NULL))
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .like("username", "a")
                .and(wrapper -> wrapper.gt("age", 20).or().isNull("email"))
                .set("username","小黑")
                .set("email","abc@codechenxi.com");

        int result = userMapper.update(null,updateWrapper);
        System.out.println("result:" + result);

    }


    @Test
    public void test09(){

        //  SELECT uid AS id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (age >= ? AND age <= ?)
        String username = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            // isNotBlank 判断某个字符串是否不为空字符串、不为 null、不为空白符
            queryWrapper.like("username",username);
        }
        if(ageBegin != null){
            queryWrapper.ge("age",ageBegin);
        }
        if(ageEnd!= null){
            queryWrapper.le("age",ageEnd);
        }

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }

    @Test
    public void test10(){

        //  SELECT uid AS id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (username LIKE ? AND age >= ? AND age <= ?)
        String username = "a";
        Integer ageBegin = 20;
        Integer ageEnd = 30;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username),"username",username)
               .ge(ageBegin != null,"age",ageBegin)
               .le(ageEnd != null,"age",ageEnd);

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }


    @Test
    public void test11(){

        //  SELECT uid AS id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (username LIKE ? AND age >= ? AND age <= ?)
        String username = "a";
        Integer ageBegin = 20;
        Integer ageEnd = 30;

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(username),User::getName,username)
              .ge(ageBegin!= null,User::getAge,ageBegin)
              .le(ageEnd!= null,User::getAge,ageEnd);

        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        users.forEach(System.out::println);
    }


    @Test
    public void test12(){

        //  UPDATE t_user SET username=?,age=?,email=? WHERE is_deleted=0 AND (username LIKE ? AND (age > ? OR email IS NULL))
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper
               .like(User::getName,"a")
               .and(wrapper -> wrapper.gt(User::getAge, 20).or().isNull(User::getEmail))
               .set(User::getName,"晨曦")
               .set(User::getAge,25)
               .set(User::getEmail,"chenxi@codechenxi.com");

        int result = userMapper.update(null,lambdaUpdateWrapper);
        System.out.println("result:" + result);
    }
}
