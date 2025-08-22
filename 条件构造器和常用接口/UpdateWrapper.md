---
up:
  - "[[MyBatis Plus 課程描述]]"
---
# 1) 只用 Wrapper 指定 SET（不需自動填充）

適用：你所有要更新的欄位都用 wrapper.set(...) 或 setSql(...) 指定；不依賴 MetaObjectHandler 自動填充。

```java
UpdateWrapper<User> uw = new UpdateWrapper<>();
uw.like("username", "a")                            // username LIKE '%a%'
  .and(w -> w.gt("age", 20).or().isNull("email"))   // AND (age > 20 OR email IS NULL)
  .set("username", "lcx")
  .set("email", "codechenxi@126.com")
  .set("age", 23);

int rows = userMapper.update(null, uw); // ← entity 傳 null：只用 wrapper 的 SET
```

會生成類似：

```sql
UPDATE t_user
   SET username=?, email=?, age=?
 WHERE is_deleted=0
   AND (username LIKE ? AND (age > ? OR email IS NULL));
```

---

# 2) 用 entity 提供 SET（觸發「自動填充」與「只更新非 null 欄位」）

適用：你有共通欄位（如 update_time）要靠 MetaObjectHandler 自動填，或只想更新 entity 中「非 null」的欄位。

```java
User entity = new User();
entity.setName("lcx");
entity.setEmail("codechenxi@126.com");
// entity.setAge(null);  // null 就不會被 SET

UpdateWrapper<User> uw = new UpdateWrapper<>();
uw.like("username", "a")
  .and(w -> w.gt("age", 20).or().isNull("email"));

int rows = userMapper.update(entity, uw); // ← entity 不能為 null：需要觸發自動填充
```

---