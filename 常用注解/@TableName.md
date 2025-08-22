---
up:
  - "[[MyBatis Plus 課程描述]]"
---
 > 经过以上的测试，在使用 MyBatis-Plus 实现基本的 CRUD 时，并没有指定要操作的表，只是在 Mapper 接口继承 BaseMapper 时，设置了泛型 User，而操作的表为 user 表。
>
> 由此得出结论，MyBatis-Plus 在确定操作的表时，由 BaseMapper 的泛型决定，即实体类型决定，且默认操作的表名和实体类型的类名一致。

---

# 1. 问题

**若实体类类型的类名和要操作的表的表名不一致，会出现什么问题？**

**步骤：**
- **将表 user 更名为 t_user，测试查询功能。**
- **程序抛出异常：Table 'mybatis_plus.user' doesn't exist，因为现在的表名为 t_user，而默认操作的表名和实体类型的类名一致，即 user 表。**

![[image-20230211201258230.png]]

---

# 2. 通过 @TableName 解决问题

> 在实体类类型上添加 @TableName("t_user")，标识实体类对应的表，即可成功执行 SQL 语句。

```java
package com.codechenxi.mybatis_plus.entity;  
  
import com.baomidou.mybatisplus.annotation.TableName;  
import lombok.Data;  
  
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

![[image-20230728144245537.png]]

---

# 3. 通过全局配置解决问题

> 在开发的过程中，经常遇到以上的问题，即实体类所对应的表都有固定的前缀，例如 t_ 或 tbl_
>
> 此时，可以使用 MyBatis-Plus 提供的全局配置，为实体类所对应的表名设置默认的前缀，那么就不需要在每个实体类上通过 @TableName 标识实体类对应的表。

```yml
mybatis-plus:  
  # 配置MyBatis-Plus操作表的默认前缀
  global-config:  
    db-config:  
      table-prefix: t_
```

**运行结果**

![[image-20230728144245537.png]]