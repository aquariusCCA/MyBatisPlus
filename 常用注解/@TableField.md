---
up:
  - "[[MyBatis Plus 課程描述]]"
---
经过以上的测试，可以发现，MyBatis-Plus 在执行 SQL 语句时，要保证实体类中的属性名和表中的字段名一致。

如果实体类中的属性名和字段名不一致的情况，会出现什么问题呢？

# 1. 情况1

**若实体类中的属性使用的是驼峰命名风格，而表中的字段使用的是下划线命名风格。**

**步骤：**

- **将实体类属性设置成 userName，表中字段设置成 user_name。**

- **此时 MyBatis-Plus 会自动将下划线命名风格转化为驼峰命名风格。**

- **相当于在 MyBatis 中配置**

```xml
<settings>
    <!--  将下划线映射为驼峰 -->
    <setting name="mapUnderscoreToCamelCase" value="true"/>
</settings>
```

## ①实体类

```java
package com.codechenxi.mybatis_plus.entity;  
  
import com.baomidou.mybatisplus.annotation.TableId;  
import lombok.Data;  
  
  
@Data  
public class User {  
    @TableId(value = "uid")  
    private Long id;  
    private String userName;  
    private Long age;  
    private String email;  
}
```

## ②表结构

![[image-20230728204303660.png]]

## ③测试

```java
@Test  
public void testSelect(){  
    // 查询所有数据  
    // SELECT id,user_name,age,email FROM user  
    List<User> list = userMapper.selectList(null);  
    list.forEach(System.out::println);  
}
```

## ④运行结果

![[image-20230728204439846.png]]

---

# 2. 情况2

**若实体类中的属性和表中的字段不满足情况1。**

**步骤：**

- **将实体类属性设置为 name，表中字段设置为 username。**

- **此时需要在实体类属性上使用 @TableField("username") 设置属性对应的字段名。**

## ①实体类

```java
package com.codechenxi.mybatis_plus.entity;  
  
import com.baomidou.mybatisplus.annotation.TableField;  
import com.baomidou.mybatisplus.annotation.TableId;  
import lombok.Data;  
  
@Data  
public class User {  
    @TableId(value = "uid")  
    private Long id;  
    @TableField("username")  
    private String name;  
    private Long age;  
    private String email;  
}
```

## ②表结构

![[image-20230728204641920.png]]

## ③测试

```java
@Test
public void testSelectList() {
	//selectList()根据MP内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
	//SELECT uid AS id,username AS name,age,email FROM t_user
	List<User> list = userMapper.selectList(null);
	list.forEach(System.out::println);
}
```

## ④运行结果

![[image-20230728204733388.png]]