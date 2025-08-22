---
up:
  - "[[MyBatis Plus 課程描述]]"
---
>  经过以上的测试，MyBatis-Plus 在实现 CRUD 时，会默认将 id 作为主键列，并在插入数据时，默认基于雪花算法的策略生成 id。

# 1. 问题

**若实体类和表中表示主键的不是 id，而是其他字段，例如 uid，MyBatis-Plus 会自动识别 uid 为主键列吗？**

**步骤：**

- **把实体类中的属性 id 改为 uid，将表中的字段 id 也改为 uid，测试添加功能。**
- **程序抛出异常：Field 'uid' doesn't have a default value ，说明 MyBatis-Plus 没有将 uid 作为主键赋值。**

```java
package com.codechenxi.mybatis_plus.entity;  
  
import lombok.Data;  
  
@Data  
public class User {  
    private Long uid;  
    private String name;  
    private Long age;  
    private String email;  
}
```

![[image-20230728201225935.png]]

```java
@Test
public void testInsert(){
	// 实现新增用户信息
	// INSERT INTO user ( uid, name, age, email ) VALUES ( ?, ?, ?, ? )
	User user = new User();
	user.setName("张三");
	user.setAge(23L);
	user.setEmail("zhangsan@codechenxi.com");
	int result = userMapper.insert(user);
	System.out.println("受影响的行数" + result);
	System.out.println("uid：" + user.getUid());
}
```

![[image-20230728201448622.png]]

---

# 2. 通过 @TableId 解决问题

> 在实体类中 uid 属性上通过 @Tabled 将其标识为主键，即可成功执行 SQL 语句。

```java
package com.codechenxi.mybatis_plus.entity;  
  
import com.baomidou.mybatisplus.annotation.TableId;  
import lombok.Data;  
  
@Data  
public class User {  
    @TableId  
    private Long uid;  
    private String name;  
    private Long age;  
    private String email;  
}
```

**运行结果**

![[image-20230728201818177.png]]

![[image-20230728201851643.png]]

---

# 3. @Tabled 的 value 属性

> 若实体类中主键对应的属性为 id，而表中标识主键的字段为 uid，此时若只在属性 id 上添加注解 @TableId，而表中标识主键的是字段 uid
>
> 此时需要通过 @TableId 注解的 value 属性，指定表中的主键字段， @TableId("uid") 或 @TableId(value="uid")

**步骤：**

- **将实体类中主键对应的属性改为 id，测试查询功能。**
- **程序抛出异常：Unknown column 'id' in 'field list'，说明 MyBatis-Plus 没找到实体类属性对应表中的字段。**

- **此时需要通过 @TableId 注解的 value 属性，指定表中的主键字段**

![[image-20230728202118931.png]]

```java
package com.codechenxi.mybatis_plus.entity;  
  
import com.baomidou.mybatisplus.annotation.TableId;  
import lombok.Data;  
  
@Data  
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

---

# 4. @TableId 的 type 属性

> `type` 屬性用來定義主鍵策略。**優先順序**：字段註解 `@TableId.type` ＞ 全局 `id-type` ＞ 預設 `ASSIGN_ID`。

## ① 常見主鍵策略

| 值                         | 描述                                            |
| ------------------------- | --------------------------------------------- |
| **IdType.ASSIGN\_ID（預設）** | 基於雪花算法生成 `id`，與資料庫是否自增無關（非連號、僅趨勢遞增）。          |
| **IdType.AUTO**           | 使用資料庫自增策略（**務必**確保資料庫欄位已設為 `AUTO_INCREMENT`）。 |

## ② 建表：先確保主鍵為自增（若要用 `AUTO`）

```sql
CREATE TABLE t_user
(
    uid   BIGINT UNSIGNED AUTO_INCREMENT COMMENT '主鍵ID' PRIMARY KEY,
    name  VARCHAR(30) NULL COMMENT '姓名',
    age   INT         NULL COMMENT '年齡',
    email VARCHAR(50) NULL COMMENT '郵箱'
);
```

> 說明：本例主鍵列名為 `uid`，不是預設的 `id`。

## ③（可選）配置全局主鍵策略與表前綴

```yml
mybatis-plus:
  global-config:
    db-config:
      # 將 entity `User` 對應到表 `t_user`
      table-prefix: t_
      # 全局主鍵策略（可被 @TableId 覆寫）
      id-type: auto
```

> 若未設定 `table-prefix: t_`，請在實體上使用 `@TableName("t_user")` 明確指定。

## ④ 實體映射：以註解指定主鍵欄位與策略（覆寫全局）

```java
@Data
// 若未使用 table-prefix，可開啟此行
// @TableName("t_user")
public class User {
    // 將屬性 id 對應到資料庫主鍵欄位 uid，並使用資料庫自增
    @TableId(value = "uid", type = IdType.AUTO)
    private Long id;

    private String name;
    private Integer age;   // 與表中 INT 更相符
    private String email;
}
```

> 重點：此處的 `IdType.AUTO` **會覆蓋** 第③步的全局 `id-type` 設定。

## ⑤ 測試：基礎新增（不手動指定 id）

```java
@Test
public void testInsert() {
    User user = new User();
    user.setName("張三");
    user.setAge(23);
    user.setEmail("zhangsan@codechenxi.com");

    int result = userMapper.insert(user);
    System.out.println("受影響的行數: " + result);
    System.out.println("id：" + user.getId()); // 這裡會拿到 DB 生成的自增 uid
}
```

> 若您看到日誌中的 SQL 顯示為 `INSERT INTO user ...`，但實際對應的是 `t_user`，那是因為已啟用 `table-prefix: t_` 的緣故。

## ⑥（可選）重置資料：截斷後再插入一批資料

> 若想從 1 開始重新自增，可使用 `TRUNCATE TABLE t_user;`，並確保 `uid` 為 `AUTO_INCREMENT`。

```mysql
INSERT INTO t_user (uid, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');
```

> 實務上，若已設為自增，**更建議**省略 `uid` 欄位，讓資料庫自動產生。

## ⑦ 特例：手動指定 id（不論策略都以您給定的值為準）

```java
@Test
public void testInsertWithManualId() {
    User user = new User();
    user.setId(100L);           // 手動指定主鍵
    user.setName("張三");
    user.setAge(23);
    user.setEmail("zhangsan@codechenxi.com");

    int result = userMapper.insert(user);
    System.out.println("受影響的行數: " + result);
    System.out.println("id：" + user.getId()); // = 100
}
```

> 結論：**只要在插入時手動賦值主鍵，MP 就不會再按 `ASSIGN_ID` 或 `AUTO` 生成。**

---

# 5. 雪花算法

## ①背景

**需要选择合适的方案去应对数据规模的增长，以应对逐渐增长的访问压力和数据量。**

**数据库的扩展方式主要包括：业务分库、主从复制、数据库分表。

## ②数据库分表

**将不同业务数据分散到不同的数据库服务器，能够支撑百万甚至千万用户规模的业务，但如果业务继续发展，同一业务的单表数据也会达到单台数据库服务器的处理瓶颈。例如，淘宝的几亿用户数据，如果全部存放在一台数据库服务器的一张表中，肯定是无法满足性能要求的，此时就需要对单表数据进行拆分。**

**单表数据拆分有两种方式：垂直分表和水平分表。示意图如下：**

![image-20230212141945282](https://cdn.jsdelivr.net/gh/CodeChenxi/cloudkaifa/MyBatisPlus/chapter04/image-20230212141945282.png)

## ③垂直分表

**<font style="color:red">垂直分表适合将表中某些不常用且占了大量空间的列拆分出去。</font>**

**例如，前面示意图中的 nickname 和 description 字段，假设是一个婚恋网站，用户在筛选其他用户的时候，主要是用 age 和 sex 两个字段进行查询，而 nickname 和 description 两个字段主要用于展示，一般不会在业务查询中用到。description 本身又比较长，因此可以将这两个字段独立到另外一张表中，这样在查询 age 和 sex 时，就能带来一定的性能提升。**

## ④水平分表

**<font style="color:red">水平分表适合表行数特别大的表</font>，有的公司要求单表行数超过 5000 万就必须进行分表，这个数字可以作为参考，但并不是绝对标准，关键还是要看表的访问性能。对于一些比较复杂的表，可能超过1000 万就要分表了；而对于一些简单的表，即使存储数据超过 1 亿行，也可以不分表。**

**但不管怎样，当看到表的数据量达到千万级别时，作为架构师就要警觉起来，因为这很可能是架构的性能瓶颈或者隐患。**

**水平分表相比垂直分表，会引入更多的复杂性，例如要求全局唯一的数据id该如何处理。**

### 主键自增

**①以最常见的用户 ID 为例，可以按照 1000000 的范围大小进行分段，1 ~ 999999 放到表 1中， 1000000 ~ 1999999 放到表2中，以此类推。**

**②复杂点：分段大小的选取。分段太小会导致切分后子表数量过多，增加维护复杂度；分段太大可能会导致单表依然存在性能问题，一般建议分段大小在 100 万至 2000 万之间，具体需要根据业务选取合适的分段大小。**

**③优点：<font style="color:red">可以随着数据的增加平滑地扩充新的表。</font>例如，现在的用户是 100 万，如果增加到 1000 万， 只需要增加新的表就可以了，原有的数据不需要动。**

**④缺点：<font style="color:red">分布不均匀。</font>假如按照 1000 万来进行分表，有可能某个分段实际存储的数据量只有 1 条，而另外一个分段实际存储的数据量有 1000 万条。

### 取模

**①同样以用户 ID 为例，假如我们一开始就规划了 10 个数据库表，可以简单地用 user_id % 10 的值来表示数据所属的数据库表编号，ID 为 985 的用户放到编号为 5 的子表中，ID 为 10086 的用户放到编号为 6 的子表中。**

**②复杂点：初始表数量的确定。表数量太多维护比较麻烦，表数量太少又可能导致单表性能存在问题。**

**③优点：<font style="color:red">表分布比较均匀。</font>**

**④缺点：<font style="color:red">扩充新的表很麻烦，所有数据都要重分布。</font>

### 雪花算法

**雪花算法是由 Twitter 公布的分布式主键生成算法，它能够保证不同表的主键的不重复性，以及相同表的主键的有序性。**

**①核心思想：**

**长度共64bit（一个long型）。**

**首先是一个符号位，1 bit标识，由于 long 基本类型在 Java 中是带符号的，最高位是符号位，正数是 0，负数是 1，所以 id 一般是正数，最高位是 0。**

**41 bit 时间截(毫秒级)，存储的是时间截的差值（当前时间截 - 开始时间截)，结果约等于69.73年。**

**10 bit 作为机器的 ID（5个 bit 是数据中心，5个 bit 的机器ID，可以部署在 1024 个节点）。**

**12 bit 作为毫秒内的流水号（意味着每个节点在每毫秒可以产生 4096 个 ID）。**

![[image-20230212154007734.png]]

**②优点：<font style="color:red">整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞，并且效率较高。</font>



