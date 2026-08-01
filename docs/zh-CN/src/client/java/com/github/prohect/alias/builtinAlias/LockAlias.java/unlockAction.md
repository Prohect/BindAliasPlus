# unlockAction 方法（src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java）

解锁先前锁定的原版游戏按键或自定义用户别名。由 `LockAlias` 和 `LockAlias_Unlock` 共用。

## 语法

```java
static void unlockAction(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `actionType` | `String` | 游戏按键动作（例如 `"attack"`、`"gameKey:forward"`）或自定义别名名称 |

## 备注

1. 通过 `getKeyBindingForAction(actionType)` 查找该动作的 `KeyMapping`。
2. **若找到原版 KeyMapping：**
   - 从 `savedBoundKeys` 移除已保存的按键。若不存在已保存按键（动作未锁定），该方法其余部分为空操作。
   - 从 `LOCKED_PHYSICAL_KEYS` 移除已保存的按键。
   - 恢复原始按键：`keyBinding.key = savedKey`。
   - 调用 `KeyMapping.resetMapping()` 使恢复生效。
   - 调用 `unlockModBoundKeys(actionType)` 移除模组绑定按键锁定，但仅当这些按键不再被另一个锁定的动作需要时。
3. **若未找到原版 KeyMapping（null）：**
   - 回退到 `unlockAliasByName(actionType)`——将 `actionType` 视为自定义用户别名名称并解锁其物理按键。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [lockAction](lockAction.md) | 反向操作：锁定按键/别名 |
| [unlockAliasByName](unlockAliasByName.md) | 解锁绑定到自定义别名名称的按键 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
