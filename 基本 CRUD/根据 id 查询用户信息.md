---
up:
  - "[[MyBatis Plus 課程描述]]"
---

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

![[image-20230728134313578.png]]

