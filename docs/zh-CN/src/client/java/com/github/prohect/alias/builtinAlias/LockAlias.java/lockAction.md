# lockAction 方法（src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java）

锁定原版游戏按键或自定义用户别名。由 `LockAlias`、`LockAlias_OnLock` 和 `LockAlias_Unlock` 共用。

## 语法

```java
static void lockAction(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `actionType` | `String` | 游戏按键动作（例如 `"attack"`、`"gameKey:forward"`）或自定义别名名称 |

## 备注

1. 通过 `getKeyBindingForAction(actionType)` 查找该动作的 `KeyMapping`。
2. **若找到原版 KeyMapping：**
   - 检查该动作是否已锁定（`savedBoundKeys.containsKey(actionType)`）。若是，返回（空操作——幂等）。
   - 保存原始按键：`savedBoundKeys.put(actionType, keyBinding.key)`。
   - 将原始按键加入 `LOCKED_PHYSICAL_KEYS`。
   - 将 KeyMapping 的按键替换为 `LOCK_PLACEHOLDER`（`InputConstants.UNKNOWN`）。
   - 调用 `KeyMapping.resetMapping()` 使更改在全局生效。
   - 调用 `lockModBoundKeys(actionType)` 同时屏蔽其别名指向该动作的模组绑定按键。
3. **若未找到原版 KeyMapping（null）：**
   - 回退到 `lockAliasByName(actionType)`——将 `actionType` 视为自定义用户别名名称，锁定绑定到它的所有物理按键。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [unlockAction](unlockAction.md) | 反向操作：解锁按键/别名 |
| [lockAliasByName](lockAliasByName.md) | 锁定绑定到自定义别名名称的按键 |
| [getKeyBindingForAction](getKeyBindingForAction.md) | 将动作类型映射到 KeyMapping |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
