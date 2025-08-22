---
up:
  - "[[MyBatis Plus 課程描述]]"
---
> QueryWrapper 两两条件之间默认是 and 的关系

# 例1：组装查询条件

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

![[image-20230729104911766.png]]

---

# 例2：组装排序条件

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

![[image-20230729105408651.png]]

---

# 例3：组装删除条件

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

![[image-20230729105731845.png]]

---

# 例4：使用 QueryWrapper 实现修改功能

```java
@Test
public void test04(){
	/**
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

![[image-20230729110344339.png]]

![[image-20230729110412539.png]]

---

# 例5：条件的优先级

```java
@Test
public void test05(){
	/**
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

![[image-20230729111306278.png]]

![[image-20230729111339060.png]]

---

# 例6：组装 select 子句

```java
@Test
public void test06(){
	/**
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

![[image-20230729111834645.png]]

---

# 例7：实现子查询

```java
@Test
public void test07(){
	/**
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