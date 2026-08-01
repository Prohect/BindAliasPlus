# aliasTargetsLockedAction 方法（src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java）

递归检查别名名称（或其 `UserAlias` 定义）是否指向被锁定的游戏动作。

## 语法

```java
private static boolean aliasTargetsLockedAction(java.lang.String, java.util.List<java.lang.String>)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `aliasName` | `String` | 要检查的别名名称（例如 `"+attack"`、`"myCustomAlias"`） |
| `patterns` | `List<String>` | 要匹配的动作名称模式（例如 `["+attack", "-attack", "builtinAttack"]`） |

## 返回值

如果别名（若为 `UserAlias` 则为其定义）直接或间接指向被锁定的动作，则返回 `true`。

## 备注

**算法：**

1. 如果 `aliasName` 为 null 或空，返回 `false`。
2. **直接匹配：** 如果 `patterns.contains(aliasName)`，返回 `true`。
3. **UserAlias 查找：** 在 `Alias.aliasesWithoutArgs`（以及作为回退的 `aliasesWithoutArgs_fromBindCommand`）中查找该别名。
4. 如果该别名是 `UserAlias`：
   - 获取其定义字符串并按别名定义分隔符拆分。
   - 对定义中的每个 token：
     - 按参数分隔符拆分 token 以提取别名部分。
     - **特殊的 `+lockKey`/`-lockKey` 处理：** 如果 token 以 `+lockKey` 或 `-lockKey` 开头且带有动作参数，则检查该动作（去掉 `+`/`-` 前缀）是否匹配任何裸模式。
     - **直接匹配：** 如果别名部分本身在 patterns 列表中，返回 `true`。
5. 如果未找到匹配项，返回 `false`。

这种递归检查确保：如果用户定义了类似 `doAttack swapSlot\1\9 +attack wait\1 -attack swapSlot\1\9` 的别名，那么当 `attack` 被锁定时，绑定到 `doAttack` 的任何物理按键也会被锁定。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [lockModBoundKeys](lockModBoundKeys.md) | 使用此方法查找指向被锁定动作的模组按键 |
| [UserAlias](../../UserAlias.java/UserAlias.md) | 定义被检查的用户定义别名 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
