# lockModBoundKeys 方法（src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java）

阻止 `BINDING_PLUS` 中绑定别名指向被锁定游戏动作的物理按键。

## 语法

```java
private static void lockModBoundKeys(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `actionType` | `String` | 裸游戏动作名称（例如 `"attack"`、`"forward"`） |

## 备注

当原版游戏按键被锁定时，玩家仍可能通过自定义模组按键绑定间接触发该动作——例如，一个将 R 键映射到 `+attack` 的 `BINDING_PLUS` 条目。此方法可防止这种绕过。

**算法：**

1. 从 `ACTION_ALIAS_PATTERNS` 中查找 `actionType` 的别名名称模式（例如 `["+attack", "-attack", "builtinAttack"]`）。如果没有模式，则返回。
2. 遍历 `BindAliasClient.BINDING_PLUS`，对每个按键/绑定对：
   - 检查 `binding.aliasNameOnKeyPressed()` 是否匹配任何模式（意味着按下此键会触发被锁定的动作）。
   - 检查 `binding.aliasNameOnKeyReleased()` 是否匹配任何模式（意味着松开此键会触发被锁定的动作）。
   - 还通过 `aliasTargetsLockedAction()` 检查这些名称，该函数递归检查 `UserAlias` 定义——如果用户别名的定义包含被锁定的动作，其按键也会被阻止。
3. 将任何匹配的物理按键添加到 `LOCKED_PHYSICAL_KEYS`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [unlockModBoundKeys](unlockModBoundKeys.md) | 反向操作：移除模组按键锁定 |
| [aliasTargetsLockedAction](aliasTargetsLockedAction.md) | 通过 UserAlias 定义的递归检查 |
| [ACTION_ALIAS_PATTERNS](static-init.md) | 由静态初始化器填充的模式映射 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
