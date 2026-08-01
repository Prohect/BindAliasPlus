# LockAlias_OnLock（src/client/java/com/github/prohect/alias/builtinAlias/LockAlias_OnLock.java）

面向用户的锁定键别名。为 `+lockKey` 开关形式包装 `LockAlias.lockAction()`。继承 `BuiltinAliasWithArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.LockAlias_OnLock extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.LockAlias_OnLock>
```

## 静态初始化器

_无。_

## 备注

注册名为 `"+lockKey"`。用法：
- `+lockKey\gameKey:attack` — 锁定原版攻击键
- `+lockKey\myAlias` — 锁定绑定到自定义别名 `myAlias` 的物理按键

命令补全同时建议 `gameKey:*` 动作和自定义 `UserAlias` 名称。

这是一个薄包装器：`run(actionType)` 仅调用 `LockAlias.lockAction(actionType)`，后者分派到按键绑定替换（对于原版按键）或 `lockAliasByName()`（对于自定义别名）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [LockAlias_Unlock](../LockAlias_Unlock.java/LockAlias_Unlock.md) | 反向 `-lockKey` 包装器 |
| [LockAlias](../LockAlias.java/LockAlias.md) | 核心锁定实现 |
| [LockAlias.lockAction()](../LockAlias.java/lockAction.md) | 此别名委托的方法 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
