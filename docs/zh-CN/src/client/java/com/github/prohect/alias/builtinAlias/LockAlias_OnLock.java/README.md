# LockAlias_OnLock

委托给 `LockAlias.lockAction()` 的面向用户的 `+lockKey` 包装器。同时支持原版游戏按键动作和自定义别名名称。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| _（除继承外无）_ | | |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `LockAlias_OnLock run(String actionType)` | 通过 `LockAlias.lockAction()` 锁定游戏按键或自定义别名 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [LockAlias](../LockAlias.java/LockAlias.md) | 核心锁定实现 |
| [LockAlias_Unlock](../LockAlias_Unlock.java/LockAlias_Unlock.md) | 反向 `-lockKey` 包装器 |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | 基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
