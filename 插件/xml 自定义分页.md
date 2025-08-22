---
up:
  - "[[MyBatis Plus 課程描述]]"
---
# 1. UserMapper 中定义接口方法

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

---

# 2. 设置类名别名

```yml
spring:
  application:
    name: TestMyBatisPlus
  # 配置数据源信息
  datasource:
    # 配置数据源类型
    type: com.zaxxer.hikari.HikariDataSource
    # 配置连接数据库信息
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false
    username: root
    password: Lins860210SStar

mybatis-plus:
  configuration:
    # 配置MyBatis日志
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      # 配置MyBatis-Plus操作表的默认前缀
      table-prefix: t_
  mapper-locations: classpath*:/mapper/**/*.xml
  # 配置类型别名对应的包
  type-aliases-package: com.codechenxi.mybatis_plus.entity
```

--- 

# 3. UserMapper.xml 中编写 SQL

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.codechenxi.mybatis_plus.mapper.UserMapper">
    <!--SQL片段，记录基础字段-->
    <sql id="BaseColumns">uid,username,age,email</sql>

    <!--IPage<User> selectPageVo(@Param("page") Page<User> page,@Param("age") Integer age);-->
    <select id="selectPageVo" resultType="User">
        select <include refid="BaseColumns" ></include> from t_user where age > #{age}
    </select>
</mapper>
```

---

# 4. 测试

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

# 5. 运行结果

![[image-20230729170615382.png]]