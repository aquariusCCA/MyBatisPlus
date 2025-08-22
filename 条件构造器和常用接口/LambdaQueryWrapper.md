---
up:
  - "[[MyBatis Plus 課程描述]]"
---
```java
@Test
public void test11(){
	/**
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

![[image-20230729161625911.png]]

![[image-20230729161724976.png]]