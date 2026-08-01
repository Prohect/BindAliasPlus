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

1. 通过 `getKeyBindingForAction(actionType)` 查找动作的 `KeyBinding`。
2. **如果找到原版 KeyBinding：**
   - 检查动作是否已被锁定（`savedBoundKeys.containsKey(actionType)`）。如果是，则返回（无操作——幂等）。
   - 保存原始按键：`savedBoundKeys.put(actionType, keyBinding.key)`。
   - 将原始按键添加到 `LOCKED_PHYSICAL_KEYS`。
   - 将 KeyBinding 的按键替换为 `LOCK_PLACEHOLDER`（`InputUtil.UNKNOWN_KEY`）。
   - 调用 `KeyBinding.updateKeysByCode()` 将更改应用到整个系统。
   - 调用 `lockModBoundKeys(actionType)` 以同时阻止任何别名指向此动作的模组绑定按键。
3. **如果未找到原版 KeyBinding（null）：**
   - 回退到 `lockAliasByName(actionType)`——将 `actionType` 当作自定义用户别名名称，并锁定绑定到它的所有物理按键。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [unlockAction](unlockAction.md) | 逆操作：解锁按键/别名 |
| [lockAliasByName](lockAliasByName.md) | 锁定绑定到自定义别名名称的按键 |
| [getKeyBindingForAction](getKeyBindingForAction.md) | 将动作类型映射到 KeyBinding |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
