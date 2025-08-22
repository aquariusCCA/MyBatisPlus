---
up:
  - "[[MyBatis Plus 課程描述]]"
  - "[[IService]]"
---
```java
package com.codechenxi.mybatis_plus.service;  
  
import com.baomidou.mybatisplus.extension.service.IService;  
import com.codechenxi.mybatis_plus.entity.User;  
  
/**  
 * UserService继承IService模板提供的基础功能  
 */  
public interface UserService extends IService<User> {  
  
}
```

```java
package com.codechenxi.mybatis_plus.service.impl;  
  
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;  
import com.codechenxi.mybatis_plus.entity.User;  
import com.codechenxi.mybatis_plus.mapper.UserMapper;  
import com.codechenxi.mybatis_plus.service.UserService;  
import org.springframework.stereotype.Service;  
  
/**  
 * ServiceImpl实现了IService，提供了IService中基础功能的实现  
 * 若ServiceImpl无法满足业务需求，则可以使用自定的UserService定义方法，并在实现类中实现   
*/  
@Service  
public class UserServiceImpl  extends ServiceImpl<UserMapper, User> implements UserService {  
}
```