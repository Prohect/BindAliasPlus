# unlockAction 方法（src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java）

解锁先前被锁定的原版游戏按键或自定义用户别名。由 `LockAlias` 和 `LockAlias_Unlock` 共用。

## 语法

```java
static void unlockAction(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `actionType` | `String` | 游戏按键动作（例如 `"attack"`、`"gameKey:forward"`）或自定义别名名称 |

## 备注

1. 通过 `getKeyBindingForAction(actionType)` 查找动作的 `KeyBinding`。
2. **如果找到原版 KeyBinding：**
   - 从 `savedBoundKeys` 中移除保存的按键。如果没有保存的按键（动作未被锁定），该方法其余部分为无操作。
   - 从 `LOCKED_PHYSICAL_KEYS` 中移除保存的按键。
   - 恢复原始按键：`keyBinding.key = savedKey`。
   - 调用 `KeyBinding.resetMapping()` 应用恢复。
   - 调用 `unlockModBoundKeys(actionType)` 移除模组按键锁定，但仅当这些按键不被另一个被锁定的动作继续需要时。
3. **如果未找到原版 KeyBinding（null）：**
   - 回退到 `unlockAliasByName(actionType)`——将 `actionType` 当作自定义用户别名名称并解锁其物理按键。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [lockAction](lockAction.md) | 逆操作：锁定按键/别名 |
| [unlockAliasByName](unlockAliasByName.md) | 解锁绑定到自定义别名名称的按键 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
