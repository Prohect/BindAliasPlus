# lockAliasByName 方法（src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java）

锁定绑定到指定用户别名名称的所有物理按键。该别名仍可通过 `builtinRunAlias` 以编程方式触发。

## 语法

```java
static void lockAliasByName(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `aliasName` | `String` | 物理按键绑定应被锁定的用户别名的名称 |

## 备注

1. 检查别名是否已被锁定（`LOCKED_ALIAS_KEYS.containsKey(aliasName)`）。如果是，则立即返回。
2. 遍历 `BindAliasClient.BINDING_PLUS`，找出所有 `aliasNameOnKeyPressed()` 或 `aliasNameOnKeyReleased()` 与 `aliasName` 匹配的物理按键。
3. 如果未找到任何按键，记录一条警告并返回——该别名没有物理绑定。
4. 将找到的按键添加到 `LOCKED_PHYSICAL_KEYS`（mixin 检查以阻止输入的集合）。
5. 在 `LOCKED_ALIAS_KEYS` 中记录映射，以便稍后解锁。
6. 记录成功日志，包含别名名称和被阻止的按键数量。

锁定后，按下绑定到此别名的物理按键没有效果，但该别名仍可通过 `builtinRunAlias` 执行。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [unlockAliasByName](unlockAliasByName.md) | 反向操作：为别名解锁按键 |
| [lockAction](lockAction.md) | 锁定原版游戏按键 |
| [LOCKED_PHYSICAL_KEYS](LOCKED_PHYSICAL_KEYS.md) | mixin 检查的集合 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
