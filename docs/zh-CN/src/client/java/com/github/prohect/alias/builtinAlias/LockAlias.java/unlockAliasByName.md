# unlockAliasByName 方法（src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java）

解锁先前为指定用户别名名称锁定的物理按键。

## 语法

```java
static void unlockAliasByName(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `aliasName` | `String` | 物理按键绑定应被解锁的用户别名的名称 |

## 备注

1. 从 `LOCKED_ALIAS_KEYS` 中移除该别名名称。如果该别名未被锁定（返回 null），记录一条警告并返回。
2. 从 `LOCKED_PHYSICAL_KEYS` 中移除所有关联的按键。
3. 记录成功日志，包含别名名称和恢复的按键数量。

解锁后，按下绑定到此别名的物理按键将再次正常触发该别名。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [lockAliasByName](lockAliasByName.md) | 反向操作：为别名锁定按键 |
| [unlockAction](unlockAction.md) | 解锁原版游戏按键 |
| [LOCKED_PHYSICAL_KEYS](LOCKED_PHYSICAL_KEYS.md) | mixin 检查的集合 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
