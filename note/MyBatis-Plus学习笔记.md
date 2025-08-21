# 一、MyBatis-Plus 简介

---

## 1. 简介

**<font style="color:red">MyBatis-Plus</font>（简称 MP）是一个 <font style="color:red">MyBatis 的增强工具</font>，在 MyBatis 的基础上<font style="color:red">只做增强不做改变</font>，为<font style="color:red">简化开发、提高效率而生。</font>**

> **愿景**
>
> 我们的愿景是成为 MyBatis 最好的搭档，就像魂斗罗中的 1P、2P，基友搭配，效率翻倍。



![image-20230727210843308](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230727210843308.png)

## 2. 特性

- **<font style="color:red">无侵入</font>：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑。**
- **<font style="color:red">损耗小</font>：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作。**
- **<font style="color:red">强大的 CRUD 操作</font>：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求。**
- **<font style="color:red">支持 Lambda 形式调用</font>：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错。**
- **<font style="color:red">支持主键自动生成</font>：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题。**
- **<font style="color:red">支持 ActiveRecord 模式</font>：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作。**
- **<font style="color:red">支持自定义全局通用操作</font>：支持全局通用方法注入（ Write once, use anywhere ）。**
- **<font style="color:red">内置代码生成器</font>：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用。**
- **<font style="color:red">内置分页插件</font>：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List 查询。**
- **<font style="color:red">分页插件支持多种数据库</font>：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer 等多种数据库。**
- **<font style="color:red">内置性能分析插件</font>：可输出 SQL 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询。**
- **<font style="color:red">内置全局拦截插件</font>：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作。**

## 3. 支持数据库

> 任何能使用 `MyBatis` 进行 CRUD, 并且支持标准 SQL 的数据库，具体支持情况如下

- **MySQL，Oracle，DB2，H2，HSQL，SQLite，PostgreSQL，SQLServer，Phoenix，Gauss ，ClickHouse，Sybase，OceanBase，Firebird，Cubrid，Goldilocks，csiidb，informix，TDengine，redshift**
- **达梦数据库，虚谷数据库，人大金仓数据库，南大通用(华库)数据库，南大通用数据库，神通数据库，瀚高数据库，优炫数据库**

## 4. 框架结构

![image-20230728084528834](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728084528834.png)

## 5. 文档及代码托管

**MyBatis-Plus 官方地址：https://baomidou.com/**

> [Gitee](https://gitee.com/baomidou/mybatis-plus) | [GitHub](https://github.com/baomidou/mybatis-plus)



# 二、入门案例

---

## 1. 开发环境

- **IDE：IntelliJ IDEA 2022.3 (Ultimate Edition)**
- **JDK：JDK8+**
- **构建工具：Apache Maven 3.9.3**
- **MySQL版本：MySQL 8.0.25**
- **Spring Boot ：2.7.14**
- **MyBatis-Plus：3.5.3**

## 2. 创建数据库及表

### 1. 创建表

```mysql
CREATE DATABASE mybatis_plus /*!40100 DEFAULT CHARACTER SET utf8mb4 */; 
use mybatis_plus;
CREATE TABLE user
(
    id BIGINT(20) NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age INT(11) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### 2. 添加数据

```mysql
INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');
```

### 3. 表结构

![image-20230728092208993](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728092208993.png)

## 3. 创建 Spring Boot 工程

### 1. 初始化工程

**使用 Spring Initializr 快速初始化一个 Spring Boot 工程。**

![image-20230728095406012](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728095406012.png)



![image-20230728092855073](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728092855073.png)



![image-20230728095556734](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728095556734.png)

### 2. 引入依赖

```xml
<dependencies>
    
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!--  mybatis-plus 启动器      -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.5.3</version>
        </dependency>

        <!--  lombok 用于简化实体类开发      -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!--   mysql 驱动     -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.25</version>
            <scope>runtime</scope>
        </dependency>
    
    </dependencies>
```

### 3. idea 中安装 lombok 插件

![image-20230728093442263](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728093442263.png)



## 4. 编写代码

### 1. 配置 application.yml

```yml
spring:
  # 配置数据源信息
  datasource:
    # 配置数据源类型
    type: com.zaxxer.hikari.HikariDataSource
    # 配置连接数据库信息
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false
    username: root
    password: 123456
```

**注意：**

- **驱动类 driver-class-name**
  - **spring boot 2.0（内置 jdbc 5驱动），驱动类使用：driver-class-name: com.mysql.jdbc.Driver**
  - **spring boot 2.1及以上（内置 jdbc 8驱动），驱动类使用：driver-class-name: com.mysql.cj.jdbc.Driver**
  - **否则运行测试用例的时候会有 WARN 信息**
- **连接地址 url**
  - **MySQL5.7版本的 url：jdbc:mysql://localhost:3306/mybatis_plus?characterEncoding=utf-8&useSSL=false**
  - **MySQL8.0版本的 url：jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false**
  - **否则运行测试用例报告如下错误：<font style="color:red">java.sql.SQLException: The server time zone value 'ÖÐ¹ú±ê×¼Ê±¼ä' is unrecognized or represents more</font>**

### 2. 启动类

> 在Spring Boot 启动类中添加 @MapperScan 注解，扫描 mapper 包

```java
package com.codechenxi.mybatis_plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 扫描 mapper 接口所在的包
@MapperScan("com.codechenxi.mybatis_plus.mapper")
public class MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }

}

```

### 3. 添加实体类

```java
package com.codechenxi.mybatis_plus.entity;


import lombok.Data;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/28 9:57
 */


@Data
public class User {

    private Long id;
    private String name;
    private Long age;
    private String email;


}
```

**User 类编译之后的结果**

![image-20230728132509249](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728132509249.png)

### 4. 添加 mapper

> BaseMapper 是 MyBatis-Plus 提供的模板 mapper，其中包含了基本的 CRUD 方法，泛型为操作的实体类型

```java
package com.codechenxi.mybatis_plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codechenxi.mybatis_plus.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/28 9:59
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
```

### 5. 测试

```java
package com.codechenxi.mybatis_plus;

import com.codechenxi.mybatis_plus.entity.User;
import com.codechenxi.mybatis_plus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/28 10:01
 */
@SpringBootTest
public class MyBatisPlusTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        //selectList()根据MP内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }
}
```

**运行结果**

![image-20230728101041813](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728101041813.png)

**注意：**

**IDEA 在 userMapper 处报错，因为找不到注入的对象，因为类是动态创建的，但是程序可以正确 的执行。**

**为了避免报错，可以在 mapper 接口上添加 @Repository 注解。**

### 6. 添加日志

**在 application.yml 中配置日志输出。**

```yml
spring:
  # 配置数据源信息
  datasource:
    # 配置数据源类型
    type: com.zaxxer.hikari.HikariDataSource
    # 配置连接数据库信息
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false
    username: root
    password: 123456
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

**运行结果**

![image-20230728101337157](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728101337157.png)



# 三、基本CRUD

---

## 1. BaseMapper

**MyBatis-Plus 中的基本 CRUD 在内置的 BaseMapper 中都已得到了实现，我们可以直接使用，接口如下：**

```java
package com.baomidou.mybatisplus.core.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Mapper 继承该接口后，无需编写 mapper.xml 文件，即可获得CRUD功能
 * <p>这个 Mapper 支持 id 泛型</p>
 *
 * @author hubin
 * @since 2016-01-23
 */
public interface BaseMapper<T> extends Mapper<T> {
    

    /**
     * 插入一条记录
     *
     * @param entity 实体对象
     */
    int insert(T entity);

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
    int deleteById(Serializable id);

    /**
     * 根据实体(ID)删除
     *
     * @param entity 实体对象
     * @since 3.4.4
     */
    int deleteById(T entity);

    /**
     * 根据 columnMap 条件，删除记录
     *
     * @param columnMap 表字段 map 对象
     */
    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    /**
     * 根据 entity 条件，删除记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     */
    int delete(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 删除（根据ID或实体 批量删除）
     *
     * @param idList 主键ID列表或实体列表(不能为 null 以及 empty)
     */
    int deleteBatchIds(@Param(Constants.COLL) Collection<?> idList);

    /**
     * 根据 ID 修改
     *
     * @param entity 实体对象
     */
    int updateById(@Param(Constants.ENTITY) T entity);

    /**
     * 根据 whereEntity 条件，更新记录
     *
     * @param entity        实体对象 (set 条件值,可以为 null)
     * @param updateWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     */
    int update(@Param(Constants.ENTITY) T entity, @Param(Constants.WRAPPER) Wrapper<T> updateWrapper);

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    T selectById(Serializable id);

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    List<T> selectBatchIds(@Param(Constants.COLL) Collection<? extends Serializable> idList);

    /**
     * 查询（根据 columnMap 条件）
     *
     * @param columnMap 表字段 map 对象
     */
    List<T> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    /**
     * 根据 entity 条件，查询一条记录
     * <p>查询一条记录，例如 qw.last("limit 1") 限制取一条记录, 注意：多条数据会报异常</p>
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    default T selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        List<T> list = this.selectList(queryWrapper);
        // 抄自 DefaultSqlSession#selectOne
        if (list.size() == 1) {
            return list.get(0);
        } else if (list.size() > 1) {
            throw new TooManyResultsException("Expected one result (or null) to be returned by selectOne(), but found: " + list.size());
        } else {
            return null;
        }
    }

    /**
     * 根据 Wrapper 条件，判断是否存在记录
     *
     * @param queryWrapper 实体对象封装操作类
     * @return 是否存在记录
     */
    default boolean exists(Wrapper<T> queryWrapper) {
        Long count = this.selectCount(queryWrapper);
        return null != count && count > 0;
    }

    /**
     * 根据 Wrapper 条件，查询总记录数
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    Long selectCount(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 entity 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    List<T> selectList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询全部记录
     * <p>注意： 只返回第一个字段的值</p>
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 entity 条件，查询全部记录（并翻页）
     *
     * @param page         分页查询条件（可以为 RowBounds.DEFAULT）
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    <P extends IPage<T>> P selectPage(P page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询全部记录（并翻页）
     *
     * @param page         分页查询条件
     * @param queryWrapper 实体对象封装操作类
     */
    <P extends IPage<Map<String, Object>>> P selectMapsPage(P page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}
```

## 2. 插入

```java
    @Test
    public void testInsert(){
        // 实现新增用户信息
        // INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        User user = new User();
        user.setName("张三");
        user.setAge(23L);
        user.setEmail("zhangsan@codechenxi.com");
        int result = userMapper.insert(user);
        System.out.println("受影响的行数" + result);
        System.out.println("id：" + user.getId());
    }
```

**运行结果**

![image-20230728131541929](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728131541929.png)



![image-20230728131632519](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728131632519.png)

## 3. 删除

### 1. 通过 id 删除记录

```java
  @Test
    public void testDelete(){
        // 通过 id 删除用户信息
        // DELETE FROM user WHERE id = ?
        int result = userMapper.deleteById(1684794476528246786L);
        System.out.println("受影响的行数" + result);

    }
```

**运行结果**

![image-20230728132036476](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728132036476.png)



![image-20230728132118295](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728132118295.png)

### 2. 通过 id 批量删除记录

```java
 @Test
    public void testDelete(){
        // 通过多个 id 实现批量删除
        // DELETE FROM user WHERE id IN ( ? , ? , ? )
//        List<Long> idList = Arrays.asList(1L, 2L, 3L);
        Collection<Long> idList = new ArrayList<>();
        idList.add(1L);
        idList.add(2L);
        idList.add(3L);
        int result = userMapper.deleteBatchIds(idList);
        System.out.println("受影响的行数: " + result);

    }
```

**运行结果**

![image-20230728133237394](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728133237394.png)



![image-20230728133434114](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728133434114.png)

### 3. 通过 map 条件删除记录

```java
@Test
    public void testDelete(){

        // 根据 map 集合中所设置的条件来删除用户信息
        // DELETE FROM user WHERE name = ? AND age = ?

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","sandy");
        map.put("age",21L);
        int result = userMapper.deleteByMap(map);
        System.out.println("受影响的行数: " + result);
    }
```

**运行结果**

![image-20230728133653406](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728133653406.png)



![image-20230728133732008](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728133732008.png)

## 4. 修改

```java
@Test
    public void testUpdateById(){
        // 修改用户信息
        // UPDATE user SET name=?, email=? WHERE id=?
        User user = new User();
        user.setId(5L);
        user.setName("李四");
        user.setEmail("lisi@codechenxi.com");
        int result = userMapper.updateById(user);
        System.out.println("受影响的行数: " + result);

    }
```

**运行结果**

![image-20230728134032033](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728134032033.png)



![image-20230728134123777](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728134123777.png)

## 5. 查询

### 1. 根据 id 查询用户信息

```java
@Test
    public void testSelect(){
        // 通过 id 查询用户信息
        // SELECT id,name,age,email FROM user WHERE id=?
        User user = userMapper.selectById(5L);
        System.out.println(user);

    }
```

**运行结果**

![image-20230728134313578](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728134313578.png)



### 2. 根据多个 id 查询多个用户信息

```java
    @Test
    public void testSelect(){
        // 根据多个 id 查询多个用户信息
        // SELECT id,name,age,email FROM user WHERE id IN ( ? , ? )
//        List<Long> idList = Arrays.asList(1L, 5L);
        Collection<Long> idList  = new ArrayList<>();
        idList.add(1L);
        idList.add(2L);
        idList.add(3L);
        List<User> list = userMapper.selectBatchIds(idList);
        list.forEach(System.out::println);

    }
```

**运行结果**

![image-20230728134639135](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728134639135.png)



### 3. 通过 map 条件查询用户信息

```java
 @Test
    public void testSelect(){

        // 根据 map 集合中的条件查询用户信息
        // SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","Jack");
        map.put("age",20);
        List<User> list = userMapper.selectByMap(map);
        list.forEach(System.out::println);


    }
```

**运行结果**

![image-20230728134803929](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728134803929.png)



### 4. 查询所有数据

```java
@Test
    public void testSelect(){


        // 查询所有数据
        // SELECT id,name,age,email FROM user
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);


    }
```

**运行结果**

![image-20230728134918951](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728134918951.png)



## 6. 测试自定义功能

> MyBatis-Plus 在 MyBatis 的基础上只做增强不做改变
>
> 这样既可以使用自定义的方法，又可以使用 MyBatis-Plus 中自带的方法

### 1. 配置 application.yml

```yml
spring:
  # 配置数据源信息
  datasource:
    # 配置数据源类型
    type: com.zaxxer.hikari.HikariDataSource
    # 配置连接数据库信息
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false
    username: root
    password: 123456
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  mybatis-plus.config-location: classpath:mapper/UserMapper.xml
```

### 2. 在 UserMapper 中新增方法

```java
  /**
     * @param id:
     * @return Map<String,Object>
     * 根据 id 查询用户信息为map集合
     */
    Map<String,Object> selectMapById(Long id);
```

### 3. 新建 UseMapper.xml 映射文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.codechenxi.mybatis_plus.mapper.UserMapper">


    <!--    Map<String,Object> selectMapById(Long id);-->
    <select id="selectMapById" resultType="map">

        select id,name,age,email from user where id = #{id}
    </select>

</mapper>
```

### 4. 测试

```java
@Test
    public void testSelect(){

         // 根据 id 查询用户信息为map集合
        // select id,name,age,email from user where id = ?
        Map<String, Object> map = userMapper.selectMapById(1L);
        System.out.println(map);


    }
```

**运行结果：**

![image-20230728135508786](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728135508786.png)



## 7. 通用 Service

**说明：**

- **通用 Service CRUD 封装 IService 接口，进一步封装 CRUD 采用:**
  - **get 查询单行**
  - **remove 删除**
  - **list 查询集合**
  - **page 分页**
- **<font style="color:red">前缀命名方式区分 Mapper 层避免混淆。</font>**
- **泛型 T 为任意实体对象。**
- **<font style="color:red">建议如果存在自定义通用 Service 方法的可能，请创建自己的 IBaseService 继承 MyBatis-Plus 提供的基类。</font>**
- **官方地址：https://baomidou.com/pages/49cc81/#service-crud-%E6%8E%A5%E5%8F%A3**

### 1. IService

**MyBatis-Plus中有一个接口 IService 和其实现类 ServiceImpl，封装了常见的业务层逻辑。**

**详情查看源码 IService 和 ServiceImpl 。**

```java
package com.baomidou.mybatisplus.extension.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.ChainQuery;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.ChainUpdate;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtQueryChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 顶级 Service
 *
 * @author hubin
 * @since 2018-06-23
 */
public interface IService<T> {

    /**
     * 默认批次提交数量
     */
    int DEFAULT_BATCH_SIZE = 1000;

    /**
     * 插入一条记录（选择字段，策略插入）
     *
     * @param entity 实体对象
     */
    default boolean save(T entity) {
        return SqlHelper.retBool(getBaseMapper().insert(entity));
    }

    /**
     * 插入（批量）
     *
     * @param entityList 实体对象集合
     */
    @Transactional(rollbackFor = Exception.class)
    default boolean saveBatch(Collection<T> entityList) {
        return saveBatch(entityList, DEFAULT_BATCH_SIZE);
    }

    /**
     * 插入（批量）
     *
     * @param entityList 实体对象集合
     * @param batchSize  插入批次数量
     */
    boolean saveBatch(Collection<T> entityList, int batchSize);

    /**
     * 批量修改插入
     *
     * @param entityList 实体对象集合
     */
    @Transactional(rollbackFor = Exception.class)
    default boolean saveOrUpdateBatch(Collection<T> entityList) {
        return saveOrUpdateBatch(entityList, DEFAULT_BATCH_SIZE);
    }

    /**
     * 批量修改插入
     *
     * @param entityList 实体对象集合
     * @param batchSize  每次的数量
     */
    boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize);

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
    default boolean removeById(Serializable id) {
        return SqlHelper.retBool(getBaseMapper().deleteById(id));
    }

    /**
     * 根据 ID 删除
     *
     * @param id      主键(类型必须与实体类型字段保持一致)
     * @param useFill 是否启用填充(为true的情况,会将入参转换实体进行delete删除)
     * @return 删除结果
     * @since 3.5.0
     */
    default boolean removeById(Serializable id, boolean useFill) {
        throw new UnsupportedOperationException("不支持的方法!");
    }

    /**
     * 根据实体(ID)删除
     *
     * @param entity 实体
     * @since 3.4.4
     */
    default boolean removeById(T entity) {
        return SqlHelper.retBool(getBaseMapper().deleteById(entity));
    }

    /**
     * 根据 columnMap 条件，删除记录
     *
     * @param columnMap 表字段 map 对象
     */
    default boolean removeByMap(Map<String, Object> columnMap) {
        Assert.notEmpty(columnMap, "error: columnMap must not be empty");
        return SqlHelper.retBool(getBaseMapper().deleteByMap(columnMap));
    }

    /**
     * 根据 entity 条件，删除记录
     *
     * @param queryWrapper 实体包装类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    default boolean remove(Wrapper<T> queryWrapper) {
        return SqlHelper.retBool(getBaseMapper().delete(queryWrapper));
    }

    /**
     * 删除（根据ID 批量删除）
     *
     * @param list 主键ID或实体列表
     */
    default boolean removeByIds(Collection<?> list) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        return SqlHelper.retBool(getBaseMapper().deleteBatchIds(list));
    }

    /**
     * 批量删除
     *
     * @param list    主键ID或实体列表
     * @param useFill 是否填充(为true的情况,会将入参转换实体进行delete删除)
     * @return 删除结果
     * @since 3.5.0
     */
    @Transactional(rollbackFor = Exception.class)
    default boolean removeByIds(Collection<?> list, boolean useFill) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        if (useFill) {
            return removeBatchByIds(list, true);
        }
        return SqlHelper.retBool(getBaseMapper().deleteBatchIds(list));
    }

    /**
     * 批量删除(jdbc批量提交)
     *
     * @param list 主键ID或实体列表(主键ID类型必须与实体类型字段保持一致)
     * @return 删除结果
     * @since 3.5.0
     */
    @Transactional(rollbackFor = Exception.class)
    default boolean removeBatchByIds(Collection<?> list) {
        return removeBatchByIds(list, DEFAULT_BATCH_SIZE);
    }

    /**
     * 批量删除(jdbc批量提交)
     *
     * @param list    主键ID或实体列表(主键ID类型必须与实体类型字段保持一致)
     * @param useFill 是否启用填充(为true的情况,会将入参转换实体进行delete删除)
     * @return 删除结果
     * @since 3.5.0
     */
    @Transactional(rollbackFor = Exception.class)
    default boolean removeBatchByIds(Collection<?> list, boolean useFill) {
        return removeBatchByIds(list, DEFAULT_BATCH_SIZE, useFill);
    }

    /**
     * 批量删除(jdbc批量提交)
     *
     * @param list      主键ID或实体列表
     * @param batchSize 批次大小
     * @return 删除结果
     * @since 3.5.0
     */
    default boolean removeBatchByIds(Collection<?> list, int batchSize) {
        throw new UnsupportedOperationException("不支持的方法!");
    }

    /**
     * 批量删除(jdbc批量提交)
     *
     * @param list      主键ID或实体列表
     * @param batchSize 批次大小
     * @param useFill   是否启用填充(为true的情况,会将入参转换实体进行delete删除)
     * @return 删除结果
     * @since 3.5.0
     */
    default boolean removeBatchByIds(Collection<?> list, int batchSize, boolean useFill) {
        throw new UnsupportedOperationException("不支持的方法!");
    }

    /**
     * 根据 ID 选择修改
     *
     * @param entity 实体对象
     */
    default boolean updateById(T entity) {
        return SqlHelper.retBool(getBaseMapper().updateById(entity));
    }

    /**
     * 根据 UpdateWrapper 条件，更新记录 需要设置sqlset
     *
     * @param updateWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper}
     */
    default boolean update(Wrapper<T> updateWrapper) {
        return update(null, updateWrapper);
    }

    /**
     * 根据 whereEntity 条件，更新记录
     *
     * @param entity        实体对象
     * @param updateWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper}
     */
    default boolean update(T entity, Wrapper<T> updateWrapper) {
        return SqlHelper.retBool(getBaseMapper().update(entity, updateWrapper));
    }

    /**
     * 根据ID 批量更新
     *
     * @param entityList 实体对象集合
     */
    @Transactional(rollbackFor = Exception.class)
    default boolean updateBatchById(Collection<T> entityList) {
        return updateBatchById(entityList, DEFAULT_BATCH_SIZE);
    }

    /**
     * 根据ID 批量更新
     *
     * @param entityList 实体对象集合
     * @param batchSize  更新批次数量
     */
    boolean updateBatchById(Collection<T> entityList, int batchSize);

    /**
     * TableId 注解存在更新记录，否插入一条记录
     *
     * @param entity 实体对象
     */
    boolean saveOrUpdate(T entity);

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    default T getById(Serializable id) {
        return getBaseMapper().selectById(id);
    }

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表
     */
    default List<T> listByIds(Collection<? extends Serializable> idList) {
        return getBaseMapper().selectBatchIds(idList);
    }

    /**
     * 查询（根据 columnMap 条件）
     *
     * @param columnMap 表字段 map 对象
     */
    default List<T> listByMap(Map<String, Object> columnMap) {
        return getBaseMapper().selectByMap(columnMap);
    }

    /**
     * 根据 Wrapper，查询一条记录 <br/>
     * <p>结果集，如果是多个会抛出异常，随机取一条加上限制条件 wrapper.last("LIMIT 1")</p>
     *
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    default T getOne(Wrapper<T> queryWrapper) {
        return getOne(queryWrapper, true);
    }

    /**
     * 根据 Wrapper，查询一条记录
     *
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     * @param throwEx      有多个 result 是否抛出异常
     */
    T getOne(Wrapper<T> queryWrapper, boolean throwEx);

    /**
     * 根据 Wrapper，查询一条记录
     *
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    Map<String, Object> getMap(Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper，查询一条记录
     *
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     * @param mapper       转换函数
     */
    <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper);

    /**
     * 查询总记录数
     *
     * @see Wrappers#emptyWrapper()
     */
    default long count() {
        return count(Wrappers.emptyWrapper());
    }

    /**
     * 根据 Wrapper 条件，查询总记录数
     *
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    default long count(Wrapper<T> queryWrapper) {
        return SqlHelper.retCount(getBaseMapper().selectCount(queryWrapper));
    }

    /**
     * 查询列表
     *
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    default List<T> list(Wrapper<T> queryWrapper) {
        return getBaseMapper().selectList(queryWrapper);
    }

    /**
     * 查询所有
     *
     * @see Wrappers#emptyWrapper()
     */
    default List<T> list() {
        return list(Wrappers.emptyWrapper());
    }

    /**
     * 翻页查询
     *
     * @param page         翻页对象
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    default <E extends IPage<T>> E page(E page, Wrapper<T> queryWrapper) {
        return getBaseMapper().selectPage(page, queryWrapper);
    }

    /**
     * 无条件翻页查询
     *
     * @param page 翻页对象
     * @see Wrappers#emptyWrapper()
     */
    default <E extends IPage<T>> E page(E page) {
        return page(page, Wrappers.emptyWrapper());
    }

    /**
     * 查询列表
     *
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    default List<Map<String, Object>> listMaps(Wrapper<T> queryWrapper) {
        return getBaseMapper().selectMaps(queryWrapper);
    }

    /**
     * 查询所有列表
     *
     * @see Wrappers#emptyWrapper()
     */
    default List<Map<String, Object>> listMaps() {
        return listMaps(Wrappers.emptyWrapper());
    }

    /**
     * 查询全部记录
     */
    default List<Object> listObjs() {
        return listObjs(Function.identity());
    }

    /**
     * 查询全部记录
     *
     * @param mapper 转换函数
     */
    default <V> List<V> listObjs(Function<? super Object, V> mapper) {
        return listObjs(Wrappers.emptyWrapper(), mapper);
    }

    /**
     * 根据 Wrapper 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    default List<Object> listObjs(Wrapper<T> queryWrapper) {
        return listObjs(queryWrapper, Function.identity());
    }

    /**
     * 根据 Wrapper 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     * @param mapper       转换函数
     */
    default <V> List<V> listObjs(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {
        return getBaseMapper().selectObjs(queryWrapper).stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toList());
    }

    /**
     * 翻页查询
     *
     * @param page         翻页对象
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    default <E extends IPage<Map<String, Object>>> E pageMaps(E page, Wrapper<T> queryWrapper) {
        return getBaseMapper().selectMapsPage(page, queryWrapper);
    }

    /**
     * 无条件翻页查询
     *
     * @param page 翻页对象
     * @see Wrappers#emptyWrapper()
     */
    default <E extends IPage<Map<String, Object>>> E pageMaps(E page) {
        return pageMaps(page, Wrappers.emptyWrapper());
    }

    /**
     * 获取对应 entity 的 BaseMapper
     *
     * @return BaseMapper
     */
    BaseMapper<T> getBaseMapper();

    /**
     * 获取 entity 的 class
     *
     * @return {@link Class<T>}
     */
    Class<T> getEntityClass();

    /**
     * 以下的方法使用介绍:
     *
     * 一. 名称介绍
     * 1. 方法名带有 query 的为对数据的查询操作, 方法名带有 update 的为对数据的修改操作
     * 2. 方法名带有 lambda 的为内部方法入参 column 支持函数式的
     * 二. 支持介绍
     *
     * 1. 方法名带有 query 的支持以 {@link ChainQuery} 内部的方法名结尾进行数据查询操作
     * 2. 方法名带有 update 的支持以 {@link ChainUpdate} 内部的方法名为结尾进行数据修改操作
     *
     * 三. 使用示例,只用不带 lambda 的方法各展示一个例子,其他类推
     * 1. 根据条件获取一条数据: `query().eq("column", value).one()`
     * 2. 根据条件删除一条数据: `update().eq("column", value).remove()`
     *
     */

    /**
     * 链式查询 普通
     *
     * @return QueryWrapper 的包装类
     */
    default QueryChainWrapper<T> query() {
        return ChainWrappers.queryChain(getBaseMapper());
    }

    /**
     * 链式查询 lambda 式
     * <p>注意：不支持 Kotlin </p>
     *
     * @return LambdaQueryWrapper 的包装类
     */
    default LambdaQueryChainWrapper<T> lambdaQuery() {
        return ChainWrappers.lambdaQueryChain(getBaseMapper(), getEntityClass());
    }

    /**
     * 链式查询 lambda 式
     * <p>注意：不支持 Kotlin </p>
     *
     * @param entity 实体对象
     * @return LambdaQueryWrapper 的包装类
     */
    default LambdaQueryChainWrapper<T> lambdaQuery(T entity) {
        return ChainWrappers.lambdaQueryChain(getBaseMapper(), entity);
    }

    /**
     * 链式查询 lambda 式
     * kotlin 使用
     *
     * @return KtQueryWrapper 的包装类
     */
    default KtQueryChainWrapper<T> ktQuery() {
        return ChainWrappers.ktQueryChain(getBaseMapper(), getEntityClass());
    }

    /**
     * 链式查询 lambda 式
     * kotlin 使用
     *
     * @return KtQueryWrapper 的包装类
     */
    default KtUpdateChainWrapper<T> ktUpdate() {
        return ChainWrappers.ktUpdateChain(getBaseMapper(), getEntityClass());
    }

    /**
     * 链式更改 普通
     *
     * @return UpdateWrapper 的包装类
     */
    default UpdateChainWrapper<T> update() {
        return ChainWrappers.updateChain(getBaseMapper());
    }

    /**
     * 链式更改 lambda 式
     * <p>注意：不支持 Kotlin </p>
     *
     * @return LambdaUpdateWrapper 的包装类
     */
    default LambdaUpdateChainWrapper<T> lambdaUpdate() {
        return ChainWrappers.lambdaUpdateChain(getBaseMapper());
    }

    /**
     * <p>
     * 根据updateWrapper尝试更新，否继续执行saveOrUpdate(T)方法
     * 此次修改主要是减少了此项业务代码的代码量（存在性验证之后的saveOrUpdate操作）
     * </p>
     *
     * @param entity 实体对象
     */
    default boolean saveOrUpdate(T entity, Wrapper<T> updateWrapper) {
        return update(entity, updateWrapper) || saveOrUpdate(entity);
    }
}
```

```java
package com.baomidou.mybatisplus.extension.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.*;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * IService 实现类（ 泛型：M 是 mapper 对象，T 是实体 ）
 *
 * @author hubin
 * @since 2018-06-23
 */
@SuppressWarnings("unchecked")
public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T> {

    protected Log log = LogFactory.getLog(getClass());

    @Autowired
    protected M baseMapper;

    @Override
    public M getBaseMapper() {
        return baseMapper;
    }

    protected Class<T> entityClass = currentModelClass();

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }

    protected Class<M> mapperClass = currentMapperClass();

    /**
     * 判断数据库操作是否成功
     *
     * @param result 数据库操作返回影响条数
     * @return boolean
     * @deprecated 3.3.1
     */
    @Deprecated
    protected boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

    protected Class<M> currentMapperClass() {
        return (Class<M>) ReflectionKit.getSuperClassGenericType(this.getClass(), ServiceImpl.class, 0);
    }

    protected Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), ServiceImpl.class, 1);
    }


    /**
     * 批量操作 SqlSession
     *
     * @deprecated 3.3.0
     */
    @Deprecated
    protected SqlSession sqlSessionBatch() {
        return SqlHelper.sqlSessionBatch(entityClass);
    }

    /**
     * 释放sqlSession
     *
     * @param sqlSession session
     * @deprecated 3.3.0
     */
    @Deprecated
    protected void closeSqlSession(SqlSession sqlSession) {
        SqlSessionUtils.closeSqlSession(sqlSession, GlobalConfigUtils.currentSessionFactory(entityClass));
    }

    /**
     * 获取 SqlStatement
     *
     * @param sqlMethod ignore
     * @return ignore
     * @see #getSqlStatement(SqlMethod)
     * @deprecated 3.4.0
     */
    @Deprecated
    protected String sqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.table(entityClass).getSqlStatement(sqlMethod.getMethod());
    }

    /**
     * 批量插入
     *
     * @param entityList ignore
     * @param batchSize  ignore
     * @return ignore
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        String sqlStatement = getSqlStatement(SqlMethod.INSERT_ONE);
        return executeBatch(entityList, batchSize, (sqlSession, entity) -> sqlSession.insert(sqlStatement, entity));
    }

    /**
     * 获取mapperStatementId
     *
     * @param sqlMethod 方法名
     * @return 命名id
     * @since 3.4.0
     */
    protected String getSqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.getSqlStatement(mapperClass, sqlMethod);
    }

    /**
     * TableId 注解存在更新记录，否插入一条记录
     *
     * @param entity 实体对象
     * @return boolean
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdate(T entity) {
        if (null != entity) {
            TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
            Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
            String keyProperty = tableInfo.getKeyProperty();
            Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
            Object idVal = tableInfo.getPropertyValue(entity, tableInfo.getKeyProperty());
            return StringUtils.checkValNull(idVal) || Objects.isNull(getById((Serializable) idVal)) ? save(entity) : updateById(entity);
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
        return SqlHelper.saveOrUpdateBatch(this.entityClass, this.mapperClass, this.log, entityList, batchSize, (sqlSession, entity) -> {
            Object idVal = tableInfo.getPropertyValue(entity, keyProperty);
            return StringUtils.checkValNull(idVal)
                || CollectionUtils.isEmpty(sqlSession.selectList(getSqlStatement(SqlMethod.SELECT_BY_ID), entity));
        }, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            sqlSession.update(getSqlStatement(SqlMethod.UPDATE_BY_ID), param);
        });
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        String sqlStatement = getSqlStatement(SqlMethod.UPDATE_BY_ID);
        return executeBatch(entityList, batchSize, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            sqlSession.update(sqlStatement, param);
        });
    }

    @Override
    public T getOne(Wrapper<T> queryWrapper, boolean throwEx) {
        if (throwEx) {
            return baseMapper.selectOne(queryWrapper);
        }
        return SqlHelper.getObject(log, baseMapper.selectList(queryWrapper));
    }

    @Override
    public Map<String, Object> getMap(Wrapper<T> queryWrapper) {
        return SqlHelper.getObject(log, baseMapper.selectMaps(queryWrapper));
    }

    @Override
    public <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {
        return SqlHelper.getObject(log, listObjs(queryWrapper, mapper));
    }

    /**
     * 执行批量操作
     *
     * @param consumer consumer
     * @since 3.3.0
     * @deprecated 3.3.1 后面我打算移除掉 {@link #executeBatch(Collection, int, BiConsumer)} }.
     */
    @Deprecated
    protected boolean executeBatch(Consumer<SqlSession> consumer) {
        return SqlHelper.executeBatch(this.entityClass, this.log, consumer);
    }

    /**
     * 执行批量操作
     *
     * @param list      数据集合
     * @param batchSize 批量大小
     * @param consumer  执行方法
     * @param <E>       泛型
     * @return 操作结果
     * @since 3.3.1
     */
    protected <E> boolean executeBatch(Collection<E> list, int batchSize, BiConsumer<SqlSession, E> consumer) {
        return SqlHelper.executeBatch(this.entityClass, this.log, list, batchSize, consumer);
    }

    /**
     * 执行批量操作（默认批次提交数量{@link IService#DEFAULT_BATCH_SIZE}）
     *
     * @param list     数据集合
     * @param consumer 执行方法
     * @param <E>      泛型
     * @return 操作结果
     * @since 3.3.1
     */
    protected <E> boolean executeBatch(Collection<E> list, BiConsumer<SqlSession, E> consumer) {
        return executeBatch(list, DEFAULT_BATCH_SIZE, consumer);
    }

    @Override
    public boolean removeById(Serializable id) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(getEntityClass());
        if (tableInfo.isWithLogicDelete() && tableInfo.isWithUpdateFill()) {
            return removeById(id, true);
        }
        return SqlHelper.retBool(getBaseMapper().deleteById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<?> list) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        TableInfo tableInfo = TableInfoHelper.getTableInfo(getEntityClass());
        if (tableInfo.isWithLogicDelete() && tableInfo.isWithUpdateFill()) {
            return removeBatchByIds(list, true);
        }
        return SqlHelper.retBool(getBaseMapper().deleteBatchIds(list));
    }

    @Override
    public boolean removeById(Serializable id, boolean useFill) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
        if (useFill && tableInfo.isWithLogicDelete()) {
            if (!entityClass.isAssignableFrom(id.getClass())) {
                T instance = tableInfo.newInstance();
                tableInfo.setPropertyValue(instance, tableInfo.getKeyProperty(), id);
                return removeById(instance);
            }
        }
        return SqlHelper.retBool(getBaseMapper().deleteById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeBatchByIds(Collection<?> list, int batchSize) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
        return removeBatchByIds(list, batchSize, tableInfo.isWithLogicDelete() && tableInfo.isWithUpdateFill());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeBatchByIds(Collection<?> list, int batchSize, boolean useFill) {
        String sqlStatement = getSqlStatement(SqlMethod.DELETE_BY_ID);
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
        return executeBatch(list, batchSize, (sqlSession, e) -> {
            if (useFill && tableInfo.isWithLogicDelete()) {
                if (entityClass.isAssignableFrom(e.getClass())) {
                    sqlSession.update(sqlStatement, e);
                } else {
                    T instance = tableInfo.newInstance();
                    tableInfo.setPropertyValue(instance, tableInfo.getKeyProperty(), e);
                    sqlSession.update(sqlStatement, instance);
                }
            } else {
                sqlSession.update(sqlStatement, e);
            }
        });
    }

}
```



### 2. 创建 Service 接口和实现类

```java
/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/28 13:59
 */

/**
* UserService继承IService模板提供的基础功能 
*/
public interface UserService extends IService<User> {
    
}
```

```java
package com.codechenxi.mybatis_plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codechenxi.mybatis_plus.entity.User;
import com.codechenxi.mybatis_plus.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/28 14:00
 */
/**
* ServiceImpl实现了IService，提供了IService中基础功能的实现
* 若ServiceImpl无法满足业务需求，则可以使用自定的UserService定义方法，并在实现类中实现 
*/
@Service
public class UserServiceImpl  extends ServiceImpl<UserMapper, User> implements UserService{
}
```



### 3. 测试查询记录数

```java
  @Test
    public void testGetCount(){
        // 查询总记录数
        // SELECT COUNT( * ) AS total FROM user
        long count = userService.count();
        System.out.println("总记录数: " + count);

    }
```

**运行结果**

![image-20230728140553826](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728140553826.png)



### 4. 测试批量插入

```java
@Test
    public void testSaveBatch(){

        // 批量添加
        // INSERT INTO user ( id, name, age ) VALUES ( ?, ?, ? )
        ArrayList<User> list = new ArrayList<>();
        for (int i = 1; i <= 10;i++){
            User user = new User();
            user.setName("lcx"+ i);
            user.setAge((long) (20+i));
            list.add(user);
        }


        boolean b = userService.saveBatch(list);
        System.out.println(b);
    }
```

**运行结果**

![image-20230728140747991](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728140747991.png)



![image-20230728140812858](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728140812858.png)



# 四、常用注解

---

## 1. @TableName

> 经过以上的测试，在使用 MyBatis-Plus 实现基本的 CRUD 时，并没有指定要操作的表，只是在 Mapper 接口继承 BaseMapper时，设置了泛型 User，而操作的表为 user 表。
>
> 由此得出结论，MyBatis-Plus 在确定操作的表时，由 BaseMapper 的泛型决定，即实体类型决定，且默认操作的表名和实体类型的类名一致。

### 1. 问题

**若实体类类型的类名和要操作的表的表名不一致，会出现什么问题？**

**步骤：**

- **将表 user 更名为 t_user，测试查询功能。**
- **程序抛出异常：Table 'mybatis_plus.user' doesn't exist，因为现在的表名为t_user，而默认操作的表名和实体类型的类名一致，即 user 表。**

![image-20230211201258230](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/MyBatisPlus/chapter04/image-20230211201258230.png)



### 2. 通过 @TableName 解决问题

> 在实体类类型上添加 @TableName("t_user")，标识实体类对应的表，即可成功执行 SQL 语句。

```java
package com.codechenxi.mybatis_plus.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/28 9:57
 */


@Data
// 设置实体类对应的表名
@TableName("t_user")
public class User {

    private Long id;
    private String name;
    private Long age;
    private String email;


}
```

**运行结果**

![image-20230728144245537](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728144245537.png)



### 3. 通过全局配置解决问题

> 在开发的过程中，经常遇到以上的问题，即实体类所对应的表都有固定的前缀，例如t\_或tbl\_
>
> 此时，可以使用 MyBatis-Plus 提供的全局配置，为实体类所对应的表名设置默认的前缀，那么就不需要在每个实体类上通过 @TableName 标识实体类对应的表。

```yml
spring:
  # 配置数据源信息
  datasource:
    # 配置数据源类型
    type: com.zaxxer.hikari.HikariDataSource
    # 配置连接数据库信息
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false
    username: root
    password: 123456
mybatis-plus:
  configuration:
    # 配置MyBatis日志
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  mybatis-plus.config-location: classpath:mapper/UserMapper.xml
  global-config:
    db-config:
      # 配置MyBatis-Plus操作表的默认前缀
      table-prefix: t_
```

**运行结果**

![image-20230728144245537](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728144245537.png)



## 2. @TableId

> 经过以上的测试，MyBatis-Plus 在实现 CRUD 时，会默认将 id 作为主键列，并在插入数据时，默认基于雪花算法的策略生成 id。

### 1. 问题

**若实体类和表中表示主键的不是 id，而是其他字段，例如 uid，MyBatis-Plus 会自动识别 uid 为主键列吗？**

**步骤：**

- **把实体类中的属性 id 改为 uid，将表中的字段 id 也改为 uid，测试添加功能。**
- **程序抛出异常：Field 'uid' doesn't have a default value ，说明 MyBatis-Plus 没有将 uid 作为主键赋值。**

```java
package com.codechenxi.mybatis_plus.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/28 9:57
 */


@Data
// 设置实体类对应的表名
//@TableName("t_user")
public class User {

    private Long uid;
    private String name;
    private Long age;
    private String email;

}
```



![image-20230728201225935](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728201225935.png)

```java
    @Test
    public void testInsert(){
        // 实现新增用户信息
        // INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        User user = new User();
        user.setName("张三");
        user.setAge(23L);
        user.setEmail("zhangsan@codechenxi.com");
        int result = userMapper.insert(user);
        System.out.println("受影响的行数" + result);
        System.out.println("id：" + user.getUid());
    }
```



![image-20230728201448622](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728201448622.png)



### 2. 通过 @TableId 解决问题

> 在实体类中 uid 属性上通过 @Tabled 将其标识为主键，即可成功执行 SQL 语句。

```java
package com.codechenxi.mybatis_plus.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/28 9:57
 */


@Data
// 设置实体类对应的表名
//@TableName("t_user")
public class User {

    @TableId
    private Long uid;
    private String name;
    private Long age;
    private String email;


}
```

**运行结果**

![image-20230728201818177](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728201818177.png)



![image-20230728201851643](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728201851643.png)

### 3. @Tabled 的 value 属性

> 若实体类中主键对应的属性为 id，而表中标识主键的字段为 uid，此时若只在属性 id 上添加注解 @TableId，而表中标识主键的是字段 uid
>
> 此时需要通过 @TableId 注解的 value 属性，指定表中的主键字段， @TableId("uid") 或 @TableId(value="uid")

**步骤：**

- **将实体类中主键对应的属性改为 id，测试查询功能。**
- **程序抛出异常：Unknown column 'id' in 'field list'，说明 MyBatis-Plus 没找到实体类属性对应表中的字段。**

- **此时需要通过 @TableId 注解的 value 属性，指定表中的主键字段**

![image-20230728202118931](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728202118931.png)

```java
package com.codechenxi.mybatis_plus.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/28 9:57
 */


@Data
// 设置实体类对应的表名
//@TableName("t_user")
public class User {

    @TableId(value = "uid")
    private Long id;
    private String name;
    private Long age;
    private String email;


}
```

```java
@Test
    public void testSelect(){


        // 查询所有数据
        // SELECT id,name,age,email FROM user
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);

    }
```

**运行结果**

![image-20230728202331607](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728202331607.png)



### 4. @TableId 的 type 属性

> type 属性用来定义主键策略

#### ①常见的主键策略

| **值**                       | **描述**                                                     |
| ---------------------------- | ------------------------------------------------------------ |
| **IdType.ASSIGN_ID（默认）** | **基于雪花算法的策略生成数据id，与数据库id是否设置自增无关** |
| **IdType.AUTO**              | **使用数据库的自增策略，==注意，该类型请确保数据库设置了id自增，否则无效==** |

#### ②通过 @Tabled 

```java
package com.codechenxi.mybatis_plus.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/28 9:57
 */


@Data
// 设置实体类对应的表名
//@TableName("t_user")
public class User {

    // @TableId注解的 value 属性用于指定主键的字段
//    @TableId(value = "uid")
    // @TableId注解的 type 属性设置主键生成策略
    @TableId(value = "uid",type = IdType.AUTO)
    private Long id;
    private String name;
    private Long age;
    private String email;


}
```

#### ③通过配置全局主键策略

```yml
spring:
  # 配置数据源信息
  datasource:
    # 配置数据源类型
    type: com.zaxxer.hikari.HikariDataSource
    # 配置连接数据库信息
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false
    username: root
    password: 123456
mybatis-plus:
  configuration:
    # 配置MyBatis日志
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  mybatis-plus.config-location: classpath:mapper/UserMapper.xml
  global-config:
    db-config:
      # 配置MyBatis-Plus操作表的默认前缀
      table-prefix: t_
      # 配置MyBatis-Plus的主键策略
      id-type: auto
```

#### ④测试添加功能

```java
@Test
    public void testInsert(){
        // 实现新增用户信息
        // INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        User user = new User();
        user.setName("张三");
        user.setAge(23L);
        user.setEmail("zhangsan@codechenxi.com");
        int result = userMapper.insert(user);
        System.out.println("受影响的行数: " + result);
        System.out.println("id：" + user.getId());
//        System.out.println("id：" + user.getUid());
    }
```

#### ⑤将数据表截断，然后重新添加数据

> 记得将 uid 设为自增

```mysql
USE mybatis_plus;
INSERT INTO t_user (uid, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');
```

> 如果不把数据表截断，再添加数据，那么设置完 id 还是雪花算法，但是效果就是雪花算法自增1的 id

#### ⑥运行结果

![image-20230728203524455](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728203524455.png)



![image-20230728203546341](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728203546341.png)

**注意：如果在添加数据的时候指明了具体的 id，则即使不采用以上的方式来配置主键策略，新插入的数据 id 值也不是雪花算法。即当插入数据时 id 让其自增的默认主键策略为雪花算法。**

```java
  @Test
    public void testInsert(){
        // 实现新增用户信息
        // INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        User user = new User();
        user.setId(100L);
        user.setName("张三");
        user.setAge(23L);
        user.setEmail("zhangsan@codechenxi.com");
        int result = userMapper.insert(user);
        System.out.println("受影响的行数: " + result);
        System.out.println("id：" + user.getId());
//        System.out.println("id：" + user.getUid());
    }
```

**运行结果**

![image-20230728203720265](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728203720265.png)



![image-20230728203738761](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728203738761.png)

### 5. 雪花算法

#### ①背景

**需要选择合适的方案去应对数据规模的增长，以应对逐渐增长的访问压力和数据量。**

**数据库的扩展方式主要包括：业务分库、主从复制、数据库分表。**

#### ②数据库分表

**将不同业务数据分散到不同的数据库服务器，能够支撑百万甚至千万用户规模的业务，但如果业务继续发展，同一业务的单表数据也会达到单台数据库服务器的处理瓶颈。例如，淘宝的几亿用户数据，如果全部存放在一台数据库服务器的一张表中，肯定是无法满足性能要求的，此时就需要对单表数据进行拆分。**

**单表数据拆分有两种方式：垂直分表和水平分表。示意图如下：**

![image-20230212141945282](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/MyBatisPlus/chapter04/image-20230212141945282.png)

#### ③垂直分表

**<font style="color:red">垂直分表适合将表中某些不常用且占了大量空间的列拆分出去。</font>**

**例如，前面示意图中的 nickname 和 description 字段，假设是一个婚恋网站，用户在筛选其他用户的时候，主要是用 age 和 sex 两个字段进行查询，而 nickname 和 description 两个字段主要用于展示，一般不会在业务查询中用到。description 本身又比较长，因此可以将这两个字段独立到另外一张表中，这样在查询 age 和 sex 时，就能带来一定的性能提升。**

#### ④水平分表

**<font style="color:red">水平分表适合表行数特别大的表</font>，有的公司要求单表行数超过 5000 万就必须进行分表，这个数字可以作为参考，但并不是绝对标准，关键还是要看表的访问性能。对于一些比较复杂的表，可能超过1000 万就要分表了；而对于一些简单的表，即使存储数据超过 1 亿行，也可以不分表。**

**但不管怎样，当看到表的数据量达到千万级别时，作为架构师就要警觉起来，因为这很可能是架构的性能瓶颈或者隐患。**

**水平分表相比垂直分表，会引入更多的复杂性，例如要求全局唯一的数据id该如何处理。**

> 主键自增

**①以最常见的用户 ID 为例，可以按照 1000000 的范围大小进行分段，1 ~ 999999 放到表 1中， 1000000 ~ 1999999 放到表2中，以此类推。**

**②复杂点：分段大小的选取。分段太小会导致切分后子表数量过多，增加维护复杂度；分段太大可能会导致单表依然存在性能问题，一般建议分段大小在 100 万至 2000 万之间，具体需要根据业务选取合适的分段大小。**

**③优点：<font style="color:red">可以随着数据的增加平滑地扩充新的表。</font>例如，现在的用户是 100 万，如果增加到 1000 万， 只需要增加新的表就可以了，原有的数据不需要动。**

**④缺点：<font style="color:red">分布不均匀。</font>假如按照 1000 万来进行分表，有可能某个分段实际存储的数据量只有 1 条，而另外一个分段实际存储的数据量有 1000 万条。**

> 取模

**①同样以用户 ID 为例，假如我们一开始就规划了 10 个数据库表，可以简单地用 user_id % 10 的值来表示数据所属的数据库表编号，ID 为 985 的用户放到编号为 5 的子表中，ID 为 10086 的用户放到编号为 6 的子表中。**

**②复杂点：初始表数量的确定。表数量太多维护比较麻烦，表数量太少又可能导致单表性能存在问题。**

**③优点：<font style="color:red">表分布比较均匀。</font>**

**④缺点：<font style="color:red">扩充新的表很麻烦，所有数据都要重分布。</font>**

> 雪花算法

**雪花算法是由 Twitter 公布的分布式主键生成算法，它能够保证不同表的主键的不重复性，以及相同表的主键的有序性。**

**①核心思想：**

**长度共64bit（一个long型）。**

**首先是一个符号位，1 bit标识，由于 long 基本类型在 Java 中是带符号的，最高位是符号位，正数是 0，负数是 1，所以 id 一般是正数，最高位是 0。**

**41 bit 时间截(毫秒级)，存储的是时间截的差值（当前时间截 - 开始时间截)，结果约等于69.73年。**

**10 bit 作为机器的 ID（5个 bit 是数据中心，5个 bit 的机器ID，可以部署在 1024 个节点）。**

**12 bit 作为毫秒内的流水号（意味着每个节点在每毫秒可以产生 4096 个 ID）。**

![image-20230212154007734](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/MyBatisPlus/chapter04/image-20230212154007734.png)

**②优点：<font style="color:red">整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞，并且效率较高。</font>**

## 3. @TableField

**经过以上的测试，可以发现，MyBatis-Plus 在执行 SQL 语句时，要保证实体类中的属性名和表中的字段名一致。**

**如果实体类中的属性名和字段名不一致的情况，会出现什么问题呢？**

### 1. 情况1

**若实体类中的属性使用的是驼峰命名风格，而表中的字段使用的是下划线命名风格。**

**步骤：**

- **将实体类属性设置成 userName，表中字段设置成 user_name。**

- **此时 MyBatis-Plus 会自动将下划线命名风格转化为驼峰命名风格。**

- **相当于在 MyBatis 中配置**

```xml
<settings>
    <!--  将下划线映射为驼峰      -->
    <setting name="mapUnderscoreToCamelCase" value="true"/>
</settings>
```



#### ①实体类

```java
package com.codechenxi.mybatis_plus.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/28 9:57
 */


@Data
// 设置实体类对应的表名
//@TableName("t_user")
public class User {

    // @TableId注解的 value 属性用于指定主键的字段
    @TableId(value = "uid")
    // @TableId注解的 type 属性设置主键生成策略
    // @TableId(value = "uid",type = IdType.AUTO)
    private Long id;
    private String userName;
    private Long age;
    private String email;


}
```



#### ②表结构

![image-20230728204303660](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728204303660.png)



#### ③测试

```java
   @Test
    public void testSelect(){

        // 查询所有数据
        // SELECT id,name,age,email FROM user
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);

    }
```

#### ④运行结果

![image-20230728204439846](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728204439846.png)



### 2. 情况2

**若实体类中的属性和表中的字段不满足情况1。**

**步骤：**

- **将实体类属性设置为 name，表中字段设置为 username。**

- **此时需要在实体类属性上使用 @TableField("username") 设置属性对应的字段名。**

#### ①实体类

```java
package com.codechenxi.mybatis_plus.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/28 9:57
 */


@Data
// 设置实体类对应的表名
//@TableName("t_user")
public class User {

    // @TableId注解的 value 属性用于指定主键的字段
    @TableId(value = "uid")
    // @TableId注解的 type 属性设置主键生成策略
    // @TableId(value = "uid",type = IdType.AUTO)
    private Long id;
    
    @TableField("username")
    private String name;
    private Long age;
    private String email;


}
```

#### ②表结构

![image-20230728204641920](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728204641920.png)



#### ③测试

```java
@Test
    public void testSelectList() {
        //selectList()根据MP内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
        //SELECT uid AS id,username AS name,age,email FROM t_user
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);

    }
```

#### ④运行结果

![image-20230728204733388](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728204733388.png)



## 4. @TableLogic

### 1. 逻辑删除

- **物理删除：真实删除，将对应数据从数据库中删除，之后查询不到此条被删除的数据。**
- **逻辑删除：假删除，将对应数据中代表是否被删除字段的状态修改为“被删除状态”，之后在数据库中仍旧能看到此条数据记录。**
- **使用场景：可以进行数据恢复。**

### 2. 实现逻辑删除

> step1：数据库中创建逻辑删除状态列，设置默认值为0

![image-20230728211038267](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728211038267.png)



> step2：实体类中添加逻辑删除属性

```java
package com.codechenxi.mybatis_plus.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/28 9:57
 */


@Data
// 设置实体类对应的表名
//@TableName("t_user")
public class User {

    // @TableId注解的 value 属性用于指定主键的字段
    @TableId(value = "uid")
    // @TableId注解的 type 属性设置主键生成策略
    // @TableId(value = "uid",type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String name;
    private Long age;
    private String email;

    @TableLogic
    private Integer isDeleted;


}
```

> step3：测试
>
> 测试删除功能，真正执行的是修改
>
> 测试查询功能，被逻辑删除的数据默认不会被查询

#### ①测试删除功能

```java
@Test
    public void testDelete() {
        // 通过 id 删除用户信息
        // UPDATE t_user SET is_deleted=1 WHERE uid=? AND is_deleted=0
        int result = userMapper.deleteById(100L);
        System.out.println("result: " + result);
    }
```

**运行结果**

![image-20230728211503026](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728211503026.png)



![image-20230728211524318](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728211524318.png)

#### ②测试查询功能

```java
@Test
    public void testSelectList() {
        //selectList()根据MP内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
        //SELECT uid AS id,username AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);

    }
```

**运行结果**

![image-20230728213805547](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230728213805547.png)



# 五、条件构造器和常用接口

---

## 1. Wrapper 介绍

![image-20230212165553594](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/MyBatisPlus/chapter05/image-20230212165553594.png)

- **Wrapper：条件构造抽象类，最顶端父类**
  - **AbstractWrapper ：  用于查询条件封装，生成 sql 的 where 条件**
    - **QueryWrapper ：  查询条件封装**
    - **UpdateWrapper ： Update 条件封装**
    - **AbstractLambdaWrapper ：  使用 Lambda 语法**
      - **LambdaQueryWrapper ：用于 Lambda语法使用的查询 Wrapper**
      - **LambdaUpdateWrapper ： Lambda 更新封装 Wrapper**

## 2. QueryWrapper

> QueryWrapper 两两条件之间默认是 and 的关系

### 例1：组装查询条件

```java
@Test
    public void test01(){

        /**
         *查询用户名包含a，年龄在20到30之间，并且邮箱不为null的用户信息
         *
         * SELECT
         *   uid AS id,username AS name,age,email,is_deleted
         * FROM
         *   t_user
         * WHERE
         *   is_deleted=0 AND (username LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
         */

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("username","a")
                .between("age",20,30)
                .isNotNull("email");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);

    }
```

**运行结果**

![image-20230729104911766](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729104911766.png)



### 例2：组装排序条件

```java
@Test
    public void test02(){


        /**
         *
         * 按年龄降序查询用户，如果年龄相同则按 id 升序排列
         * SELECT uid AS id,username AS name,age,email,is_deleted
         * FROM
         *  t_user
         * WHERE
         *  is_deleted=0 ORDER BY age DESC,id ASC
         */

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("id");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }
```

**运行结果**

![image-20230729105408651](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729105408651.png)



### 例3：组装删除条件

```java
@Test
    public void test03(){

        /**
         *
         *
         * 删除 email 为 null 的用户
         * UPDATE
         * t_user
         * SET
         *  is_deleted=1
         * WHERE
         *  is_deleted=0 AND (email IS NULL)
         */

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println("影响行数：" + result);


    }
```

**运行结果**

![image-20230729105731845](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729105731845.png)



### 例4：使用 QueryWrapper 实现修改功能

```java
@Test
    public void test04(){


        /**
         *
         *
         * 将（年龄大于20并且用户名中包含有a）或邮箱为null的用户信息修改
         * UPDATE
         * t_user
         *  SET age=?, email=?
         * WHERE
         *  is_deleted=0 AND (username LIKE ? AND age > ? OR email IS NULL)
         */

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username","a").gt("age",20).or().isNull("email");

        User user = new User();
        user.setAge(18L);
        user.setEmail("user@codechenxi.com");

        int result = userMapper.update(user,queryWrapper);
        System.out.println("受影响的行数：" + result);
    }
```

**运行结果**

![image-20230729110344339](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729110344339.png)



![image-20230729110412539](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729110412539.png)



### 例5：条件的优先级

```java
 @Test
    public void test05(){


        /**
         *
         *
         * 将用户名中包含有 a 并且（年龄大于 20 或邮箱为 null）的用户信息修改
         * lambda 中的条件优先执行
         * UPDATE
         * t_user
         *  SET age=?, email=?
         * WHERE
         *  is_deleted=0 AND (username LIKE ? AND (age > ? OR email IS NULL))
         */

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username","a").and(i -> i .gt("age",20).or().isNull("email"));

        User user = new User();
        user.setAge(23L);
        user.setEmail("lcx@codechenxi.com");

        int result = userMapper.update(user, queryWrapper);
        System.out.println("受影响的行数：" + result);
    }
```

**运行结果**

![image-20230729111306278](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729111306278.png)



![image-20230729111339060](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729111339060.png)



### 例6：组装 select 子句

```java
@Test
    public void test06(){

        /**
         *
         *
         * 查询用户信息的username和age字段
         * SELECT username,age
         * FROM
         *  t_user
         * WHERE
         *  is_deleted=0
         * selectMaps()返回Map集合列表，通常配合select()使用，避免User对象中没有被查询到的列值为null
         */

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        QueryWrapper<User> userQueryWrapper = queryWrapper.select("username", "age");


        List<Map<String, Object>> mapList = userMapper.selectMaps(userQueryWrapper);
        mapList.forEach(System.out::println);


    }
```

**运行结果**

![image-20230729111834645](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729111834645.png)



### 例7：实现子查询

```java
@Test
    public void test07(){

        /**
         *
         *
         * 查询id小于等于3的用户信息
         *
         * SELECT uid AS id,username AS name,age,email,is_deleted
         * FROM
         *  t_user
         * WHERE
         *  is_deleted=0 AND (uid IN (select uid from t_user where uid <= 3))
         *
         */

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("uid","select uid from t_user where uid <= 3");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);


    }
```

**运行结果**

![image-20230729112316152](C:\Users\codechenxi\AppData\Roaming\Typora\typora-user-images\image-20230729112316152.png)



## 3. UpdateWrapper

```java
@Test
    public void test08(){

        /**
         *
         *
         * 将（年龄大于20或邮箱为null）并且用户名中包含有a的用户信息修改
         * 组装set子句以及修改条件
         *  lambda 中的条件优先执行
         * UPDATE
         *  t_user
         *  SET username=?,email=?,age=? WHERE is_deleted=0 AND (username LIKE ? AND (age > ? OR email IS NULL))
         *
         */

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .set("username","lcx")
                .set("email","codechenxi@126.com")
                .set("age",23)
                .like("username","a")
                .and(i ->i .gt("age",20).or().isNull("email"));

        // User user = new User();
        // user.setName("lcx");
        // user.setAge(25);
        // user.setEmail("lcx@linchenxi.com");
        // 这里必须要创建User对象，否则无法应用自动填充。如果没有自动填充，可以设置为null
        int result = userMapper.update(null, updateWrapper);
        System.out.println("受影响的行数：" + result);

    }
```

**运行结果**

![image-20230729155635692](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729155635692.png)



![image-20230729155722121](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729155722121.png)

## 4. condition

> 在真正开发的过程中，组装条件是常见的功能，而这些条件数据来源于用户输入，是可选的，因此我们在组装这些条件时，必须先判断用户是否选择了这些条件，若选择则需要组装该条件，若没有选择则一定不能组装，以免影响 SQL 执行的结果

### 1. 思路一

> 模拟开发中组装条件的情况

```java
 @Test
    public void test09(){

        /**
         *
         *         String username = "";
         *         Integer ageBegin = 20;
         * SELECT
         *  uid AS id,username AS name,age,email,is_deleted
         * FROM
         *  t_user
         * WHERE
         *  is_deleted=0 AND (age >= ? AND age <= ?)
         *
         *         String username = "a";
         *         Integer ageBegin = null;
         * SELECT
         *  uid AS id,username AS name,age,email,is_deleted
         * FROM
         *  t_user
         * WHERE
         *  is_deleted=0 AND (username LIKE ? AND age <= ?)
         */

//        String username = "";
//        Integer ageBegin = 20;
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();


        if(StringUtils.isNotBlank(username)){
            // isNotBlank 判断某个字符串是否不为空字符串，不为 null，不为空白符
            queryWrapper.like("username",username);

        }

        if(ageBegin != null){
            queryWrapper.ge("age",ageBegin);
        }

        if (ageEnd != null){
            queryWrapper.le("age",ageEnd);
        }

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);


    }
```

**运行结果**

![image-20230729160143294](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729160143294.png)



![image-20230729160532629](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729160532629.png)

### 2. 思路二

> 上面的实现方案没有问题，但是代码比较复杂，我们可以使用带 condition 参数的重载方法构建查询条件，简化代码的编写

```java
  @Test
    public void test10(){

        /**
         *
         * 定义查询条件，有可能为null（用户未输入或未选择）
         *
         *         String username = "";
         *         Integer ageBegin = 20;
         * SELECT
         *  uid AS id,username AS name,age,email,is_deleted
         * FROM
         *  t_user
         * WHERE
         *  is_deleted=0 AND (age >= ? AND age <= ?)
         *
         *          String username = "a";
         *         Integer ageBegin = null;
         *
         * SELECT
         *  uid AS id,username AS name,age,email,is_deleted
         * FROM
         *  t_user
         * WHERE
         *  is_deleted=0 AND (username LIKE ? AND age <= ?)
         */

//        String username = "";
//        Integer ageBegin = 20;
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // isNotBlank 判断某个字符串是否不为空字符串，不为 null，不为空白符
        queryWrapper.like(StringUtils.isNotBlank(username),"username",username)
                .gt(ageBegin != null,"age",ageBegin)
                .lt(ageEnd != null,"age",ageEnd);

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);


    }
```

**运行结果**

![image-20230729161008980](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729161008980.png)



![image-20230729161152770](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729161152770.png)



## 5. LambdaQueryWrapper

```java
   @Test
    public void test11(){

        /**
         *
         * 定义查询条件，有可能为null（用户未输入）
         *
         *          String username = "";
         *          Integer ageBegin = 20;
         *
         * SELECT
         *  uid AS id,username AS name,age,email,is_deleted
         * FROM
         *  t_user
         * WHERE
         *  is_deleted=0 AND (age >= ? AND age <= ?)
         *
         *          String username = "a";
         *         Integer ageBegin = null;
         *
         * SELECT
         *  uid AS id,username AS name,age,email,is_deleted
         * FROM
         *  t_user
         * WHERE
         *  is_deleted=0 AND (age >= ? AND age <= ?)
         */


//        String username = "";
//        Integer ageBegin = 20;
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //避免使用字符串表示字段，防止运行时错误
        lambdaQueryWrapper
                // isNotBlank 判断某个字符串是否不为空字符串，不为 null，不为空白符
                .like(StringUtils.isNotBlank(username),User::getName,username)
                .gt(ageBegin != null,User::getAge,ageBegin)
                .lt(ageEnd != null,User::getAge,ageEnd);

        List<User> userList = userMapper.selectList(lambdaQueryWrapper);
        userList.forEach(System.out::println);

    }
```

**运行结果**

![image-20230729161625911](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729161625911.png)



![image-20230729161724976](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729161724976.png)



## 6. LambdaUpdateWrapper

```java
@Test
    public void test12(){

        /**
         *
         *
         * 将用户名中包含有 a 并且（年龄大于 20 或邮箱为 null）的用户信息修改
         * UPDATE
         *  t_user
         * SET username=?,email=?,age=? WHERE is_deleted=0 AND (username LIKE ? AND (age > ? OR email IS NULL))
         *
         */

        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper
                .like(User::getName,"l")
                .and(i -> i .gt(User::getAge,20).or().isNull(User::getEmail));


        lambdaUpdateWrapper
                .set(User::getName,"lcx123")
                .set(User::getEmail,"lcx123@linchenxi123.com")
                .set(User::getAge,25);

        int result = userMapper.update(null, lambdaUpdateWrapper);
        System.out.println("影响行数：" + result);
    }
```

**运行结果**

![image-20230729162739762](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729162739762.png)



![image-20230729162811592](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729162811592.png)



# 六、插件

---

## 1. 分页插件

> MyBatis Plus 自带分页插件，只要简单的配置即可实现分页功能

### 1. 添加配置类

```java
package com.codechenxi.mybatis_plus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/29 16:36
 */
@Configuration
@MapperScan("com.codechenxi.mybatis_plus.mapper") //可以将主类中的注解移到此处
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();

        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        return mybatisPlusInterceptor;

    }
}
```

### 2. 测试

```java
@Test
    public void testPage(){
	//SELECT COUNT(*) AS total FROM t_user WHERE is_deleted = 0
        
        
        // 设置分页参数
        Page<User> page = new Page<>(1,5);
        userMapper.selectPage(page,null);

        // 获取分页数据
        List<User> list = page.getRecords();
        list.forEach(System.out::println);

        System.out.println("当前页: " + page.getCurrent());
        System.out.println("每页显示的条数: " + page.getSize());
        System.out.println("总记录数: " + page.getTotal());
        System.out.println("总页数: " + page.getPages());
        System.out.println("是否有上一页: " + page.hasPrevious());
        System.out.println("是否有下一页: " + page.hasNext());

    }
```

### 3. 运行结果

![image-20230729165517531](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729165517531.png)



## 2. xml 自定义分页

### 1. UserMapper 中定义接口方法

```java
/**
     * @param page: MyBatis-Plus 提供的分页对象,xml中可以从里面进行取值,传递参数 Page即自动分页,必须放在第一位
     * @param age:
     * @return Page<User>
     * TODO
     * 根据年龄查询用户列表，分页显示
     */
    Page<User> selectPageVo(@Param("page") Page<User> page, @Param("age") Long age);
```

### 2. 设置类名别名

```yml
spring:
  # 配置数据源信息
  datasource:
    # 配置数据源类型
    type: com.zaxxer.hikari.HikariDataSource
    # 配置连接数据库信息
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false
    username: root
    password: 123456
mybatis-plus:
  configuration:
    # 配置MyBatis日志
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  mybatis-plus.config-location: classpath:mapper/UserMapper.xml
  global-config:
    db-config:
      # 配置MyBatis-Plus操作表的默认前缀
      table-prefix: t_
      # 配置MyBatis-Plus的主键策略
      id-type: auto
  # 配置类型别名对应的包
  type-aliases-package: com.codechenxi.mybatis_plus.entity
```

### 3. UserMapper.xml 中编写 SQL

```xml
      <!--SQL片段，记录基础字段-->
    <sql id="BaseColumns">uid,username,age,email</sql>

<!--    IPage<User> selectPageVo(@Param("page") Page<User> page,@Param("age") Integer age);-->

    <select id="selectPageVo" resultType="User">

        select <include refid="BaseColumns" ></include> from t_user where age > #{age}
        
    </select>
```

### 4. 测试

```java
@Test
    public void testSelectPageVo(){
        // SELECT COUNT(*) AS total FROM t_user WHERE age > ?

        // 设置分页参数
        Page<User> page = new Page<>(1,5);
        userMapper.selectPageVo(page,20L);

        // 获取分页数据
        List<User> list = page.getRecords();
        list.forEach(System.out::println);


        System.out.println("当前页: " + page.getCurrent());
        System.out.println("每页显示的条数: " + page.getSize());
        System.out.println("总记录数: " + page.getTotal());
        System.out.println("总页数: " + page.getPages());
        System.out.println("是否有上一页: " + page.hasPrevious());
        System.out.println("是否有下一页: " + page.hasNext());

    }
```

### 5. 运行结果

![image-20230729170615382](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729170615382.png)



## 3. 乐观锁

### 1. 场景

> 一件商品，成本价是80元，售价是100元。老板先是通知小李，说你去把商品价格增加50元。小李正在玩游戏，耽搁了一个小时。正好一个小时后，老板觉得商品价格增加到150元，价格太高，可能会影响销量。又通知小王，你把商品价格降低30元。
> 
> 此时，小李和小王同时操作商品后台系统。小李操作的时候，系统先取出商品价格100元；小王也在操作，取出的商品价格也是100元。小李将价格加了50元，并将100+50=150元存入了数据库；小王将商品减了30元，并将100-30=70元存入了数据库。是的，如果没有锁，小李的操作就完全被小王的覆盖了。
>
> 现在商品价格是70元，比成本价低10元。几分钟后，这个商品很快出售了1千多件商品，老板亏1 万多。

### 2. 乐观锁与悲观锁

> 上面的故事，如果是乐观锁，小王保存价格前，会检查下价格是否被人修改过了。如果被修改过 
> 了，则重新取出的被修改后的价格，150元，这样他会将120元存入数据库。
>
> 如果是悲观锁，小李取出数据后，小王只能等小李操作完之后，才能对价格进行操作，也会保证 
> 最终的价格是120元。

### 3. 模拟修改冲突

#### ①数据库中增加商品表

```mysql
CREATE TABLE t_product 
(
id BIGINT(20) NOT NULL COMMENT '主键ID',
NAME VARCHAR(30) NULL DEFAULT NULL COMMENT '商品名称',
price INT(11) DEFAULT 0 COMMENT '价格',
VERSION INT(11) DEFAULT 0 COMMENT '乐观锁版本号',
PRIMARY KEY (id)
);
```

#### ②添加数据

```mysql
INSERT INTO t_product (id, NAME, price) VALUES (1, '外星人笔记本', 100);
```

#### ③表结构

![image-20230729173004901](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729173004901.png)



#### ④添加实体类

```java
package com.codechenxi.mybatis_plus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/29 17:30
 */
@Data
public class Product {

        private Long id;
        @TableField("NAME")
        private String name;
        private Integer price;
        @TableField("VERSION")
        private Integer version;
    }
```

#### ⑤添加 mapper

```java
package com.codechenxi.mybatis_plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codechenxi.mybatis_plus.entity.Product;
import org.springframework.stereotype.Repository;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/29 17:32
 */
@Repository
public interface ProductMapper  extends BaseMapper<Product> {
}
```

#### ⑥测试

```java
 @Test
    public void testProduct01(){

        //     小李查询商品价格
        //     SELECT id,NAME,price,VERSION FROM t_product WHERE id=?
        Product product1 = productMapper.selectById(1);
        System.out.println("小李取出的价格：" + product1.getPrice());

        //      小王查询商品价格
        //     SELECT id,NAME,price,VERSION FROM t_product WHERE id=?
        Product product2 = productMapper.selectById(1);
        System.out.println("小王取出的价格：" + product2.getPrice());

        //      小李将价格加了50元，存入了数据库
        //     UPDATE t_product SET NAME=?, price=?, VERSION=? WHERE id=?
        product1.setPrice(product1.getPrice() + 50);
        productMapper.updateById(product1);
        System.out.println("小李修改结果：" + product1.getPrice());

        //       小王将商品减了30元，存入了数据库
        //     UPDATE t_product SET NAME=?, price=?, VERSION=? WHERE id=?
        product2.setPrice(product2.getPrice() - 30);
        productMapper.updateById(product2);
        System.out.println("小王修改结果：" + product2.getPrice());


        //       最后的结果
        //     SELECT id,NAME,price,VERSION FROM t_product WHERE id=?
        Product product3 = productMapper.selectById(1);
        // 价格覆盖，最后的结果是：70
        System.out.println("最后的结果：" + product3.getPrice());

    }
```

#### ⑦运行结果

![image-20230729174034596](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729174034596.png)



![image-20230729174109492](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729174109492.png)



![image-20230729174131827](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729174131827.png)



![image-20230729174202056](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729174202056.png)



### 4. 乐观锁实现流程

- **数据库中添加 version 字段。**

- **取出记录时，获取当前 version。**

```mysql
select id,NAME,price,VERSION from t_product where id = 1;
```

- **更新时，version + 1，如果 where 语句中的 version 版本不对，则更新失败。**

```mysql
update product set price = price + 50,VERSION = VERSION + 1 where id = 1 AND VERSION = 1;
```

### 5. MyBatis-Plus实现乐观锁

#### ①修改实体类

```java
@Data
public class Product {

    private Long id;

    @TableField("NAME")
    private String name;

    private Integer price;

    @TableField("VERSION")
   @Version//标识乐观锁版本号字段
    private Integer version;
}
```

#### ②添加乐观锁插件配置

```java
package com.codechenxi.mybatis_plus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/29 16:36
 */
@Configuration
@MapperScan("com.codechenxi.mybatis_plus.mapper") //可以将主类中的注解移到此处
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();

        // 添加分页插件
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        // 添加乐观锁插件
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        return mybatisPlusInterceptor;

    }

}
```

#### ③测试修改冲突

```java
@Test
    public void testProduct02(){

        //     小李查询商品价格
        //     SELECT id,NAME,price,VERSION FROM t_product WHERE id=?
        Product product1 = productMapper.selectById(1);
        System.out.println("小李取出的价格：" + product1.getPrice());

        //      小王查询商品价格
        //     SELECT id,NAME,price,VERSION FROM t_product WHERE id=?
        Product product2 = productMapper.selectById(1);
        System.out.println("小王取出的价格：" + product2.getPrice());

        //      小李修改商品价格，自动将 version+1，存入数据库
        //     UPDATE t_product SET NAME=?, price=?, VERSION=? WHERE id=? AND VERSION=?
        product1.setPrice(product1.getPrice() + 50);
        productMapper.updateById(product1);
        System.out.println("小李修改结果：" + product1.getPrice());

        //      小王修改商品价格，此时version已更新，条件不成立，修改失败
        //     UPDATE t_product SET NAME=?, price=?, VERSION=? WHERE id=? AND VERSION=?
        product2.setPrice(product2.getPrice() - 30);
        productMapper.updateById(product2);
        System.out.println("小王修改结果：" + product2.getPrice());


        //       最终，小王修改失败，查询价格：120
        //     SELECT id,NAME,price,VERSION FROM t_product WHERE id=?
        Product product3 = productMapper.selectById(1);
        // 价格覆盖，最后的结果是：120
        System.out.println("最后的结果：" + product3.getPrice());

    }
```

**运行结果**

![image-20230729175550327](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729175550327.png)

![image-20230729175632297](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729175632297.png)



![image-20230729175709707](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729175709707.png)



![image-20230729175731459](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729175731459.png)



![image-20230729175745188](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729175745188.png)



#### ④优化流程

```java
 @Test
    public void testProduct03(){

        //     小李取数据
        //     SELECT id,NAME,price,VERSION FROM t_product WHERE id=?
        Product product1 = productMapper.selectById(1);

        //      小王取数据
        //     SELECT id,NAME,price,VERSION FROM t_product WHERE id=?
        Product product2 = productMapper.selectById(1);


        //      小李修改 + 50
        //     UPDATE t_product SET NAME=?, price=?, VERSION=? WHERE id=? AND VERSION=?
        product1.setPrice(product1.getPrice() + 50);
        int result1 = productMapper.updateById(product1);
        System.out.println("小李修改的结果：" + result1);

        //      小王修改 - 30
        //     UPDATE t_product SET NAME=?, price=?, VERSION=? WHERE id=? AND VERSION=?
        product2.setPrice(product2.getPrice() - 30);
        int result2 = productMapper.updateById(product2);
        System.out.println("小王修改结果：" + result2);


        if(result2 == 0){

        //      失败重试，重新获取 version 并更新
        //     UPDATE t_product SET NAME=?, price=?, VERSION=? WHERE id=? AND VERSION=?
        product2 = productMapper.selectById(1);
        product2.setPrice(product2.getPrice() - 30);
        result2 = productMapper.updateById(product2);
        }

        System.out.println("小王修改重试的结果：" + result2);

        //       老板看价格
        //     SELECT id,NAME,price,VERSION FROM t_product WHERE id=?
        Product product3 = productMapper.selectById(1);
        System.out.println("老板看价格：" + product3.getPrice());


    }
```

**运行结果**

![image-20230729180122134](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729180122134.png)

![image-20230729180211296](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729180211296.png)



![image-20230729180235952](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729180235952.png)



![image-20230729180250522](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729180250522.png)



# 七、通用枚举

---

> 表中的有些字段值是固定的，例如性别（男或女），此时我们可以使用 MyBatis -Plus 的通用枚举来实现

## 1. 数据库表添加字段 sex

![image-20230729180540539](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729180540539.png)



## 2. 创建通用枚举类型

```java
package com.codechenxi.mybatis_plus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/29 18:07
 */
public enum SexEnum {

    MALE(1,"男"),
    FEMALE(2,"女");


    @EnumValue//将注解所标识的属性的值存储到数据库中
    private Integer sex;
    private String sexName;

    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}

```

## 3. 补充实体类信息

```java
package com.codechenxi.mybatis_plus.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.codechenxi.mybatis_plus.enums.SexEnum;
import lombok.Data;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/28 9:57
 */


@Data
// 设置实体类对应的表名
//@TableName("t_user")
public class User {

    // @TableId注解的 value 属性用于指定主键的字段
    @TableId(value = "uid")
    // @TableId注解的 type 属性设置主键生成策略
    // @TableId(value = "uid",type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String name;
    private Long age;
    private String email;


    private SexEnum sex;
    
    @TableLogic
    private Integer isDeleted;


}
```

## 4. 配置扫描通用枚举

> 官方说不需要配置这个了

```yml
spring:
  # 配置数据源信息
  datasource:
    # 配置数据源类型
    type: com.zaxxer.hikari.HikariDataSource
    # 配置连接数据库信息
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false
    username: root
    password: 123456
mybatis-plus:
  configuration:
    # 配置MyBatis日志
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  mybatis-plus.config-location: classpath:mapper/UserMapper.xml
  global-config:
    db-config:
      # 配置MyBatis-Plus操作表的默认前缀
      table-prefix: t_
      # 配置MyBatis-Plus的主键策略
      id-type: auto
  # 配置类型别名对应的包
  type-aliases-package: com.codechenxi.mybatis_plus.entity
  #  配置扫描通用枚举
  type-enums-package: com.codechenxi.mybatis_plus.enums
```

## 5. 测试

```java
  @Test
    public void testSexEnum() {

        User user = new User();
        user.setName("Enum");
        user.setAge(20L);
        //设置性别信息为枚举项，会将@EnumValue注解所标识的属性值存储到数据库
        user.setSex(SexEnum.MALE);
        // INSERT INTO t_user ( username, age, sex ) VALUES ( ?, ?, ? )
        // Parameters: Enum(String), 20(Integer), 1(Integer)

        int result = userMapper.insert(user);
        System.out.println("影响行数：" + result);
    }
```

## 6. 运行结果

![image-20230729181453334](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729181453334.png)



![image-20230729181333324](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729181333324.png)



# 八、代码生成器(新)

---

## 1. 引入依赖

```xml
   		<dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>3.5.1</version>
        </dependency>
        
        <dependency>
            <groupId>org.freemarker</groupId>
            <artifactId>freemarker</artifactId>
            <version>2.3.31</version>
        </dependency>
```

## 2. 快速生成

```java
package com.codechenxi.mybatis_plus;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/29 18:16
 */
@SpringBootTest
public class FastAutoGeneratorTest {

    public static void main(String[] args) {

        FastAutoGenerator.create(
                        "jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false",
                        "root",
                        "123456")
                .globalConfig(builder -> {
                    builder.author("codechenxi@126.com") // 设置作者
                            // .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("F://Technology_Stack//mybatis-plus-learning//code//mybatis_plus_generator"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.codechenxi") // 设置父包名
                            .moduleName("mybatis_plus") // 设置父包模块名
                            // 设置mapperXml生成路径
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "F://Technology_Stack//mybatis-plus-learning//code//mybatis_plus_generator"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_user") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}

```

## 3. 运行结果

![image-20230729182341838](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729182341838.png)



![image-20230729182447291](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729182447291.png)



# 九、多数据源

---

> 适用于多种场景：纯粹多库、  读写分离、  一主多从、  混合模式等
>
> 目前我们就来模拟一个纯粹多库的一个场景，其他场景类似
>
> 场景说明：
>
> 我们创建两个库，分别为：mybatis_plus（以前的库不动）与mybatis_plus_1（新建），在mybatis_plus_1 库新建一张 product 表，这样每个库一张表，通过一个测试用例分别获取用户数据与商品数据，如果获取到说明多库模拟成功



## 1. 创建数据库及表

> 创建数据库 mybatis_plus_1和表 product

```mysql
CREATE DATABASE `mybatis_plus_1 /*!40100 DEFAULT CHARACTER SET utf8mb4 */; 
use mybatis_plus_1;

CREATE TABLE product 
(
id BIGINT(20) NOT NULL COMMENT '主键ID',
name VARCHAR(30) NULL DEFAULT NULL COMMENT '商品名称', 
price INT(11) DEFAULT 0 COMMENT '价格',
version INT(11) DEFAULT 0 COMMENT '乐观锁版本号', 
PRIMARY KEY (id)
);
```

> 添加测试数据

```mysql
INSERT INTO product (id, NAME, price) VALUES (1, '外星人笔记本', 100);
```

> 删除 mybatis_plus 库 t_product 表

```mysql
use mybatis_plus;
DROP TABLE IF EXISTS tb_product;
```



## 2. 新建工程

![image-20230729183557653](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729183557653.png)



![image-20230729183633161](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729183633161.png)



## 3. 引入依赖

```xml
<!--  mybatis-plus 启动器      -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.5.3</version>
        </dependency>

        <!--  lombok 用于简化实体类开发      -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!--   mysql 驱动     -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.25</version>
            <scope>runtime</scope>

        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
            <version>3.5.0</version>
        </dependency>
```



## 4. 配置多个数据源

> 说明：注释掉之前的数据库连接，添加新配置

```yml
spring:
  datasource:
    dynamic:
      primary: master #设置默认的数据源或者数据源组,默认值即为master
      strict: false #严格匹配数据源,默认false. true未匹配到指定数据源时抛异常,false使用默认数据源
      datasource:
        master:
          url: jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false
          username: root
          password: 123456
          driver-class-name: com.mysql.cj.jdbc.Driver # 3.2.0开始支持SPI可省略此配置
        slave_1:
          url: jdbc:mysql://localhost:3306/mybatis_plus_1?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false
          username: root
          password: 123456
          driver-class-name: com.mysql.cj.jdbc.Driver
#        slave_2:
#          url: ENC(xxxxx) # 内置加密,使用请查看详细文档
#          username: ENC(xxxxx)
#          password: ENC(xxxxx)
#          driver-class-name: com.mysql.jdbc.Driver
        #......省略
        #以上会配置一个默认库master，一个组slave下有两个子库slave_1,slave_2
```



## 5. 新建实体类

```java
package com.codechenxi.mybatis_plus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/29 18:39
 */
@Data
@TableName("t_user")
public class User {
    @TableId
    private Integer uid;
    @TableField("username")
    private String userName;
    private Integer age;
    private Integer sex;
    private String email;
    private Integer isDeleted;
}
```

```java
package com.codechenxi.mybatis_plus.entity;

import lombok.Data;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/29 18:39
 */
@Data
public class Product {


    private Integer id;
    private String name;
    private Integer price;
    private Integer version;
}

```



## 6. 新建 Mapper 接口

```java
/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/29 18:40
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
```

```java
package com.codechenxi.mybatis_plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codechenxi.mybatis_plus.entity.Product;
import org.springframework.stereotype.Repository;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/29 18:41
 */
@Repository
public interface ProductMapper  extends BaseMapper<Product> {
}
```



## 7. 配置启动类

```java
package com.codechenxi.mybatis_plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.codechenxi.mybatis_plus.mapper")
public class MybatisPlusDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusDatasourceApplication.class, args);
    }

}
```



## 8. 创建用户接口和接口实现类

```java
package com.codechenxi.mybatis_plus.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codechenxi.mybatis_plus.entity.User;
import com.codechenxi.mybatis_plus.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/29 18:42
 */
public interface UserService  extends IService<User> {
}
```

```java
package com.codechenxi.mybatis_plus.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codechenxi.mybatis_plus.mapper.UserMapper;
import com.codechenxi.mybatis_plus.service.UserService;
import org.springframework.stereotype.Service;
import com.codechenxi.mybatis_plus.entity.User;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/29 18:43
 */
@DS("master")
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
```



## 9. 创建商品接口和接口实现类

```java
package com.codechenxi.mybatis_plus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.codechenxi.mybatis_plus.entity.Product;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/29 18:45
 */
public interface ProductService  extends IService<Product> {
}
```

```java
package com.codechenxi.mybatis_plus.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codechenxi.mybatis_plus.entity.Product;
import com.codechenxi.mybatis_plus.service.ProductService;
import org.springframework.stereotype.Service;
import com.codechenxi.mybatis_plus.mapper.ProductMapper;
/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/29 18:46
 */
@DS("slave_1")
@Service
public class ProductServiceImpl  extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
```



## 10. 测试

```java
package com.codechenxi.mybatis_plus;

import com.codechenxi.mybatis_plus.service.ProductService;
import com.codechenxi.mybatis_plus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author codechenxi@126.com
 * @version 1.0
 * @date 2023/7/29 18:49
 */
@SpringBootTest
public class MyBatisDataSourceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Test
    public void testDynamicDataSource(){
        System.out.println(userService.getById(1L));
        System.out.println(productService.getById(1L));
    }
}
```

**运行结果**

![image-20230729185224198](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729185224198.png)



> 结果：
> 1、都能顺利获取对象，则测试成功
> 2、如果我们实现读写分离，将写操作方法加上主库数据源，读操作方法加上从库数据源，自动切换，是不是就能实现读写分离？



# 十、MyBatisX 插件

---

> MyBatis-Plus为我们提供了强大的mapper和service模板，能够大大的提高开发效率，但是在真正开发过程中，MyBatis-Plus并不能为我们解决所有问题，例如一些复杂的SQL，多表联查，我们就需要自己去编写代码和 SQL 语句，我们该如何快速的解决这个问题呢，这个时候可以使用 MyBatisX 插件 MyBatisX 一款基于 IDEA 的快速开发插件，为效率而生。

**MyBatisX插件用法：https://baomidou.com/pages/ba5b24/**

## 1. 步骤

### 1. 安装插件

![image-20230729185903669](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729185903669.png)



### 2. 重启 IDEA

## 2. 定位操作

> 配置一个映射文件

![image-20230729190129309](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729190129309.png)



![image-20230729190148854](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729190148854.png)



## 3. 代码快速生成

### 1. 新建工程

![image-20230729190302396](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729190302396.png)



![image-20230729190322669](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729190322669.png)



### 2. 引入依赖

```xml
 <!--  mybatis-plus 启动器      -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.5.3</version>
        </dependency>

        <!--  lombok 用于简化实体类开发      -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!--   mysql 驱动     -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.25</version>
            <scope>runtime</scope>

        </dependency>


        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
            <version>3.5.0</version>
        </dependency>
```

### 3. 配置 yml 文件

```yml
spring:
  # 配置数据源信息
  datasource:
    # 配置数据源类型
    type: com.zaxxer.hikari.HikariDataSource
    # 配置连接数据库信息
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false
    username: root
    password: 123456
```

### 4. 连接数据库

![image-20230729190601558](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729190601558.png)



![image-20230729190638523](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729190638523.png)



### 5. 配置 MybatisX-Generator

![image-20230729190723977](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729190723977.png)



![image-20230729190853036](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729190853036.png)



![image-20230729190936077](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729190936077.png)



## 4. 快速生成CRUD

> 既补全了方法，又在映射文件生成对应的 SQL 语句

### 1. 增加

![image-20230729191106051](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729191106051.png)

> 按住 alt + 回车

![image-20230729191146688](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729191146688.png)



![image-20230729191213478](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729191213478.png)



![image-20230729191240403](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729191240403.png)



### 2. 删除



![image-20230729191435681](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729191435681.png)



![image-20230729191453898](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729191453898.png)



![image-20230729191514687](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729191514687.png)



### 3. 修改

![image-20230729191601192](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729191601192.png)



![image-20230729191619297](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729191619297.png)



![image-20230729191636486](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729191636486.png)



### 4. 查找

![image-20230729191737615](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729191737615.png)



![image-20230729191753928](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729191753928.png)



![image-20230729191808521](https://learning-develop-note.oss-cn-fuzhou.aliyuncs.com/MyBatis-Plus/image-20230729191808521.png)

