---
up:
  - "[[MyBatis Plus 課程描述]]"
---

```java
@Test
public void test12(){
	/**
	 * 将用户名中包含有 a 并且（年龄大于 20 或邮箱为 null）的用户信息修改
	 * UPDATE
	 *  t_user
	 * SET username=?,email=?,age=? WHERE is_deleted=0 AND (username LIKE ? AND (age > ? OR email IS NULL))
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

![[image-20230729162739762.png]]

![[image-20230729162811592.png]]