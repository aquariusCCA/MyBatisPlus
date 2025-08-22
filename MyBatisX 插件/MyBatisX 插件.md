---
up:
  - "[[MyBatis Plus 課程描述]]"
---
> MyBatis-Plus 为我们提供了强大的 mapper 和 service 模板，能够大大的提高开发效率，但是在真正开发过程中，MyBatis-Plus 并不能为我们解决所有问题，例如一些复杂的 SQL，多表联查，我们就需要自己去编写代码和 SQL 语句，我们该如何快速的解决这个问题呢，这个时候可以使用 MyBatisX 插件 MyBatisX 一款基于 IDEA 的快速开发插件，为效率而生。

**MyBatisX插件用法：https://baomidou.com/pages/ba5b24/

---

# 1. 步骤

### 1. 安装插件

![[image-20230729185903669.png]]

### 2. 重启 IDEA

略

---

# 2. 定位操作

> 配置一个映射文件

![[image-20230729190129309.png]]

![[image-20230729190148854.png]]

---

# 3. 代码快速生成

### 1. 新建工程

略
### 2. 引入依赖

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

	<!-- mybatis-plus 启动器 -->
	<dependency>
		<groupId>com.baomidou</groupId>
		<artifactId>mybatis-plus-spring-boot3-starter</artifactId>
		<version>3.5.12</version>
	</dependency>

	<!-- lombok 用于简化实体类开发 -->
	<dependency>
		<groupId>org.projectlombok</groupId>
		<artifactId>lombok</artifactId>
		<scope>annotationProcessor</scope>
	</dependency>

	<!-- mysql 驱动 -->
	<dependency>
		<groupId>com.mysql</groupId>
		<artifactId>mysql-connector-j</artifactId>
		<scope>runtime</scope>
	</dependency>
</dependencies>
```

### 3. 配置 yml 文件

```yml
spring:
  # 配置数据源信息
  datasource:
    # 配置数据源类型
    type: com.zaxxer.hikari.HikariDataSource
    # 配置连接数据库信息
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false
    username: root
    password: Lins860210SStar
```

### 4. 连接数据库

![[image-20230729190601558.png]]

![[image-20230729190638523.png]]

### 5. 配置 MybatisX-Generator

![[image-20230729190723977.png]]

![[image-20230729190853036.png]]

![[image-20230729190936077.png]]

---

# 4. 快速生成CRUD

> 既补全了方法，又在映射文件生成对应的 SQL 语句

### 1. 增加

![[image-20230729191106051.png]]

> 按住 alt + 回车

![[image-20230729191146688.png]]

![[image-20230729191213478.png]]

![[image-20230729191240403.png]]

### 2. 删除

![[image-20230729191435681.png]]

![[image-20230729191453898.png]]

![[image-20230729191514687.png]]

### 3. 修改

![[image-20230729191601192.png]]

![[image-20230729191619297.png]]

![[image-20230729191636486.png]]

### 4. 查找


![[image-20230729191737615.png]]

![[image-20230729191753928.png]]

![[image-20230729191808521.png]]



