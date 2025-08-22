---
up:
  - "[[MyBatis Plus 課程描述]]"
---
# 1. 逻辑删除

- **物理删除：真实删除，将对应数据从数据库中删除，之后查询不到此条被删除的数据。**

- **逻辑删除：假删除，将对应数据中代表是否被删除字段的状态修改为“被删除状态”，之后在数据库中仍旧能看到此条数据记录。**

- **使用场景：可以进行数据恢复。

---

# 2. 实现逻辑删除

> **Step1：** 数据库中创建逻辑删除状态列，设置默认值为0

![[image-20230728211038267.png]]

> **Step2：** 实体类中添加逻辑删除属性

```java
package com.codechenxi.mybatis_plus.entity;  
  
import com.baomidou.mybatisplus.annotation.TableField;  
import com.baomidou.mybatisplus.annotation.TableId;  
import com.baomidou.mybatisplus.annotation.TableLogic;  
import lombok.Data;  
  
@Data  
public class User {  
    @TableId(value = "uid")  
    private Long id;  
    @TableField("username")  
    private String name;  
    private Long age;  
    private String email;  
    @TableLogic  
    private Integer isDeleted;  
}
```

> **Step3：** 测试
>
> 测试删除功能，真正执行的是修改
>
> 测试查询功能，被逻辑删除的数据默认不会被查询

## ①测试删除功能

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

![[image-20230728211503026.png]]

![[image-20230728211524318.png]]

## ②测试查询功能

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

![[image-20230728213805547.png]]