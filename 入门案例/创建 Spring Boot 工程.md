---
up:
  - "[[MyBatis Plus 課程描述]]"
---
# 1. 初始化工程

**使用 Spring Initializr 快速初始化一个 Spring Boot 工程。**

![[image-20230728095406012.png]]

![[image-20230728092855073.png]]

![[image-20230728095556734.png]]

---

# 2. 引入依赖

```xml
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter</artifactId>
	</dependency>

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-test</artifactId>
		<scope>test</scope>
	</dependency>

	<!--  mybatis-plus 启动器      -->
	<dependency>
		<groupId>com.baomidou</groupId>
		<artifactId>mybatis-plus-spring-boot3-starter</artifactId>
		<version>3.5.12</version>
	</dependency>

	<!--  lombok 用于简化实体类开发      -->
	<dependency>
		<groupId>org.projectlombok</groupId>
		<artifactId>lombok</artifactId>
		<scope>annotationProcessor</scope>
	</dependency>

	<!--   mysql 驱动     -->
	<dependency>
		<groupId>com.mysql</groupId>
		<artifactId>mysql-connector-j</artifactId>
		<scope>runtime</scope>
	</dependency>
</dependencies>
```

---