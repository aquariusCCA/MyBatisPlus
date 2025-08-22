---
up:
  - "[[MyBatis Plus 課程描述]]"
---
> 在真正开发的过程中，组装条件是常见的功能，而这些条件数据来源于用户输入，是可选的，因此我们在组装这些条件时，必须先判断用户是否选择了这些条件，若选择则需要组装该条件，若没有选择则一定不能组装，以免影响 SQL 执行的结果

# 1. 思路一

> 模拟开发中组装条件的情况

```java
@Test
public void test09(){
	/**
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

![[image-20230729160143294.png]]

![[image-20230729160532629.png]]

---

# 2. 思路二

> 上面的实现方案没有问题，但是代码比较复杂，我们可以使用带 condition 参数的重载方法构建查询条件，简化代码的编写

```java
@Test
public void test10(){
	/**
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

![[image-20230729161008980.png]]

![[image-20230729161152770.png]]