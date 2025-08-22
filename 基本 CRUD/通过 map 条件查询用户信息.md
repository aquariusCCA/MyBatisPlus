---
up:
  - "[[MyBatis Plus 課程描述]]"
---
```java
@Test
public void testSelect(){
	// 根据 map 集合中的条件查询用户信息
	// SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
	Map<String,Object> map = new HashMap<String,Object>();
	map.put("name","Jack");
	map.put("age",20);
	List<User> list = userMapper.selectByMap(map);
	list.forEach(System.out::println);
}
```

**运行结果**

![[image-20230728134803929.png]]