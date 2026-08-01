# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/LockAlias_OnLock.java）

锁定游戏按键或自定义别名。`LockAlias.lockAction()` 的薄包装器。

## 语法

```java
public com.github.prohect.alias.builtinAlias.LockAlias_OnLock run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `actionType` | `String` | 要锁定的动作：游戏按键动作（`gameKey:attack`）或自定义别名名称 |

## 备注

仅委托给 `LockAlias.lockAction(actionType)`。完整的锁定算法请参阅该方法。

**示例：**
- `+lockKey\gameKey:forward` — 锁定前进移动键
- `+lockKey\myMacro` — 锁定绑定到 `myMacro` 的物理按键

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [LockAlias.lockAction()](../LockAlias.java/lockAction.md) | 此方法委托的实现 |
| [LockAlias_Unlock.run()](../LockAlias_Unlock.java/run.md) | 反向操作：解锁 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
