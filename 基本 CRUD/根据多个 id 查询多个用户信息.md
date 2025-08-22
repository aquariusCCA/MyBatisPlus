---
up:
  - "[[MyBatis Plus 課程描述]]"
---
```java
@Test
public void testSelect(){
	// 根据多个 id 查询多个用户信息
	// SELECT id,name,age,email FROM user WHERE id IN ( ? , ? )
	// List<Long> idList = Arrays.asList(1L, 5L);
	Collection<Long> idList  = new ArrayList<>();
	idList.add(1L);
	idList.add(2L);
	idList.add(3L);
	List<User> list = userMapper.selectBatchIds(idList);
	list.forEach(System.out::println);
}
```

**运行结果**

![[image-20230728134639135.png]]