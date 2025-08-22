---
up:
  - "[[MyBatis Plus 課程描述]]"
---
```java
@Test
public void testDelete(){
	// 通过多个 id 实现批量删除
	// DELETE FROM user WHERE id IN ( ? , ? , ? )
	// List<Long> idList = Arrays.asList(1L, 2L, 3L);
	Collection<Long> idList = new ArrayList<>();
	idList.add(1L);
	idList.add(2L);
	idList.add(3L);
	int result = userMapper.deleteBatchIds(idList);
	System.out.println("受影响的行数: " + result);
}
```

**运行结果**

![[image-20230728133237394.png]]

![[image-20230728133434114.png]]