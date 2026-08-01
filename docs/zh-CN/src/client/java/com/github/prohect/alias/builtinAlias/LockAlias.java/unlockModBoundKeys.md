# unlockModBoundKeys 方法（src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java）

从 `LOCKED_PHYSICAL_KEYS` 中移除先前由 `lockModBoundKeys()` 添加的物理按键阻止——但仅当这些按键不再被另一个被锁定动作需要时。

## 语法

```java
private static void unlockModBoundKeys(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `actionType` | `String` | 正在解锁的裸游戏动作名称（例如 `"attack"`、`"forward"`） |

## 备注

**算法：**

1. 从 `ACTION_ALIAS_PATTERNS` 中查找 `actionType` 的别名名称模式。如果没有模式，则返回。
2. 通过遍历 `BINDING_PLUS` 并使用 `aliasTargetsLockedAction()` 检查，构建一个可能移除的按键集合。
3. 对每个候选按键：
   - 检查每一个**其他**仍被锁定的动作（`savedBoundKeys.keySet()` 排除 `actionType`），看该动作是否也需要阻止此按键。
   - 如果任何其他动作仍需要该按键（其模式与该绑定匹配），则该按键**不**被移除——它保留在 `LOCKED_PHYSICAL_KEYS` 中。
   - 仅当没有其他被锁定动作需要该按键时，才将其从 `LOCKED_PHYSICAL_KEYS` 中移除。

这可以防止多个游戏动作同时被锁定且共享模组绑定按键时过早解除按键阻止。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [lockModBoundKeys](lockModBoundKeys.md) | 反向操作：阻止模组绑定按键 |
| [aliasTargetsLockedAction](aliasTargetsLockedAction.md) | 通过 UserAlias 定义的递归检查 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
