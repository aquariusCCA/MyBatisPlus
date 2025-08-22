---
up:
  - "[[MyBatis Plus 課程描述]]"
---
![[image-20230212165553594.png]]

- **Wrapper：条件构造抽象类，最顶端父类**
	- **AbstractWrapper ：  用于查询条件封装，生成 sql 的 where 条件**
		- **[[QueryWrapper]] ：  查询条件封装**
		- **[[UpdateWrapper]] ： Update 条件封装**
		- **AbstractLambdaWrapper ：  使用 Lambda 语法**
			- **[[LambdaQueryWrapper]] ：用于 Lambda语法使用的查询 Wrapper**
			- **[[LambdaUpdateWrapper]] ： Lambda 更新封装 Wrapper**

