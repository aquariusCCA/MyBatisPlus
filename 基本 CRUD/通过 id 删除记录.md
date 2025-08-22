---
up:
  - "[[MyBatis Plus 課程描述]]"
---
```java
@Test
public void testDelete(){
	// 通过 id 删除用户信息
	// DELETE FROM user WHERE id = ?
	int result = userMapper.deleteById(1684794476528246786L);
	System.out.println("受影响的行数" + result);
}
```

**运行结果**

![[image-20230728132036476.png]]

![[image-20230728132118295.png]]
