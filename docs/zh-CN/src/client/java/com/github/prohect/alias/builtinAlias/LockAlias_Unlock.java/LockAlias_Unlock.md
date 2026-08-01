# LockAlias_Unlock（src/client/java/com/github/prohect/alias/builtinAlias/LockAlias_Unlock.java）

面向用户的解锁键别名。为 `-lockKey` 开关形式包装 `LockAlias.unlockAction()`。是 `LockAlias_OnLock` 的反向操作。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.LockAlias_Unlock extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.LockAlias_Unlock>
```

## 静态初始化器

_无。_

## 备注

注册名为 `"-lockKey"`。用法：
- `-lockKey\gameKey:attack` — 解锁原版攻击键
- `-lockKey\myAlias` — 解锁绑定到自定义别名 `myAlias` 的物理按键

这是一个薄包装器：`run(actionType)` 仅调用 `LockAlias.unlockAction(actionType)`，后者分派到按键绑定恢复（对于原版按键）或 `unlockAliasByName()`（对于自定义别名）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [LockAlias_OnLock](../LockAlias_OnLock.java/LockAlias_OnLock.md) | 反向 `+lockKey` 包装器 |
| [LockAlias](../LockAlias.java/LockAlias.md) | 核心锁定实现 |
| [LockAlias.unlockAction()](../LockAlias.java/unlockAction.md) | 此别名委托的方法 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
