# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java）

内置锁定命令的主要入口点。解析 `actionType\flag` 并分派给 `lockAction()` 或 `unlockAction()`。

## 语法

```java
public com.github.prohect.alias.builtinAlias.LockAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 格式：`actionType\flag`，其中 actionType 是游戏按键动作或别名名称，flag 为 `"1"`（锁定）或 `"0"`（解锁） |

## 备注

1. 按别名参数分隔符（`\`）拆分参数——期望恰好 2 个部分（`actionType` 和 `flag`）。如果不是恰好 2 个部分，记录一条警告并返回。
2. 解析 flag：`"1"` 表示锁定，其他任何值表示解锁。
3. 分派：
   - 如果锁定：调用 `lockAction(actionType)`。
   - 如果解锁：调用 `unlockAction(actionType)`。

**示例：**
- `builtinLock\attack\1` — 锁定攻击键
- `builtinLock\attack\0` — 解锁攻击键
- `builtinLock\gameKey:forward\1` — 锁定前进移动（使用 gameKey 前缀）
- `builtinLock\myAlias\1` — 锁定绑定到自定义别名 `myAlias` 的物理按键

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [lockAction](lockAction.md) | 按名称锁定原版按键或自定义别名 |
| [unlockAction](unlockAction.md) | 按名称解锁原版按键或自定义别名 |
| [LockAlias_OnLock.run()](../LockAlias_OnLock.java/run.md) | 面向用户的 `+lockKey` 包装器 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
