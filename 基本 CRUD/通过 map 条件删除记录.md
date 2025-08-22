---
up:
  - "[[MyBatis Plus 課程描述]]"
---
```java
@Test
public void testDelete(){
	// 根据 map 集合中所设置的条件来删除用户信息
	// DELETE FROM user WHERE name = ? AND age = ?

	Map<String,Object> map = new HashMap<String,Object>();
	map.put("name","sandy");
	map.put("age",21L);
	int result = userMapper.deleteByMap(map);
	System.out.println("受影响的行数: " + result);
}
```

**运行结果**

![[image-20230728133653406.png]]

![[image-20230728133732008.png]]