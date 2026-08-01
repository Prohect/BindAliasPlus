# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/LockAlias_Unlock.java）

解锁游戏按键或自定义别名。`LockAlias.unlockAction()` 的薄包装器。

## 语法

```java
public com.github.prohect.alias.builtinAlias.LockAlias_Unlock run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `actionType` | `String` | 要解锁的动作：游戏按键动作（`gameKey:attack`）或自定义别名名称 |

## 备注

仅委托给 `LockAlias.unlockAction(actionType)`。完整的解锁算法请参阅该方法。

**示例：**
- `-lockKey\gameKey:forward` — 解锁前进移动键
- `-lockKey\myMacro` — 解锁绑定到 `myMacro` 的物理按键

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [LockAlias.unlockAction()](../LockAlias.java/unlockAction.md) | 此方法委托的实现 |
| [LockAlias_OnLock.run()](../LockAlias_OnLock.java/run.md) | 反向操作：锁定 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
