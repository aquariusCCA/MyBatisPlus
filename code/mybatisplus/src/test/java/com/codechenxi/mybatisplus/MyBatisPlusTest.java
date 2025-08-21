package com.codechenxi.mybatisplus;

import com.codechenxi.mybatisplus.mapper.UserMapper;
import com.codechenxi.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author codechenxi
 * @since 2025-07-15 10:19
 */

@SpringBootTest
public class MyBatisPlusTest {


    @Autowired
    private UserMapper userMapper;



    @Test
    public void testSelectList() {
        //通过条件构造器查询一个list集合，若没有条件，则可以设置null为参数
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }


    @Test
    public void testInsert(){
        //  实现新增用户信息
        //  INSERT INTO user (name, age,email) VALUES (?,?,?)
        User user = new User();
//        user.setId(100L);
        user.setName("zhangsan");
        user.setAge(20);
        user.setEmail("zhangsan@codechenxi.com");
        int result = userMapper.insert(user);
        System.out.println("result:" + result);
        System.out.println("id:" + user.getId());
    }


    @Test
    public void testDelete(){
        //  通过 id 删除用户信息
        //  DELETE FROM user WHERE id=?
      /*  int result = userMapper.deleteById(1945006836468342786L);
        System.out.println("result:" + result);*/

        //  根据 map 集合中所设置的条件删除用户信息
        //  DELETE FROM user WHERE name = ? AND age = ?
        /*Map<String,Object> map = new HashMap<>();
        map.put("name","zhangsan");
        map.put("age",20);
        int result = userMapper.deleteByMap(map);
        System.out.println("result:" + result);*/

        //  通过多个 id 实现批量删除
        //  DELETE FROM user WHERE id IN ( ? , ? , ? )
        List<Long> idList = Arrays.asList(1L, 2L, 3L);
        int result = userMapper.deleteBatchIds(idList);
        System.out.println("result:" + result);
    }


    @Test
    public void testUpdate(){

        //  修改用户信息
        //  UPDATE user SET name=?, email=? WHERE id=?
        User user = new User();
        user.setId(4L);
        user.setName("lisi");
        user.setEmail("lisi@codechenxi.com");
        int result = userMapper.updateById(user);
        System.out.println("result:" + result);
    }


    @Test
    public void testSelect(){
        //  通过 id 查询用户信息
        //  SELECT id,name,age,email FROM user WHERE id=?
       /* User user = userMapper.selectById(5L);
        System.out.println(user);*/

        //  根据多个 id 查询多个用户信息
        //  SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
       /* List<Long> idList = Arrays.asList(1L, 2L, 3L);
        List<User> users = userMapper.selectByIds(idList);
        users.forEach(System.out::println);*/

        //  根据 map 集合中的条件查询用户信息
        //  SELECT id,name,age,email FROM user WHERE (name = ? AND age = ?)
        /*Map<String,Object> map = new HashMap<>();
        map.put("name","Jack");
        map.put("age",20);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);*/

        //  查询所有数据
        //  SELECT id,name,age,email FROM user
        /*List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);*/

        //  自定义功能通过 id 查询用户信息
        //  select id,name,age,email from user where id = ?
        Map<String, Object> map = userMapper.selectMapById(1L);
        System.out.println(map);

    }


}
