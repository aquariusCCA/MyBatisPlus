---
up:
  - "[[MyBatis Plus 課程描述]]"
---
- **通用 Service CRUD 封装 IService 接口，进一步封装 CRUD 采用:**
	- **get 查询单行**
	- **remove 删除**
	- **list 查询集合**
	- **page 分页**

- **<font style="color:red">前缀命名方式区分 Mapper 层避免混淆。</font>**

- **泛型 T 为任意实体对象。**

- **<font style="color:red">建议如果存在自定义通用 Service 方法的可能，请创建自己的 IBaseService 继承 MyBatis-Plus 提供的基类。</font>**

- **官方地址：https://baomidou.com/pages/49cc81/#service-crud-%E6%8E%A5%E5%8F%A3**