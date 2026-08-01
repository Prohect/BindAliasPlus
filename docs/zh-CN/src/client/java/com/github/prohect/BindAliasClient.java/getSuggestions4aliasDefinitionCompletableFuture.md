# getSuggestions4aliasDefinitionCompletableFuture 方法（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
private static java.util.concurrent.CompletableFuture<com.mojang.brigadier.suggestion.Suggestions> getSuggestions4aliasDefinitionCompletableFuture(com.mojang.brigadier.suggestion.SuggestionsBuilder)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `builder` | `SuggestionsBuilder` | Brigadier 建议构建器，携带当前已输入的内容 |

## 返回值

一个用于命令参数域中 tab 补全的 `CompletableFuture<Suggestions>`。

## 备注

为别名定义字符串提供上下文感知的 tab 补全建议（用于 `/alias`、`/bind`、`/runAlias` 命令）。行为取决于当前输入位置：

1. **空白输入**：建议所有已知的 `aliasesWithoutArgs` 和 `aliasesWithArgs` 名称。

2. **位于参数值内**（光标在别名调用中某个 `\` 之后，且不在新的别名名称位置）：
   - 对于 `+lockKey` / `-lockKey`：建议来自 `LockAlias.SUPPORTED_ACTIONS` 的可锁定动作名称，以及已有的 `UserAlias` 名称。
   - 对于 `reapply`：建议来自 `ReapplyAlias.SUPPORTED_ACTIONS` 的支持动作。
   - 对于接受数字参数的别名（`BuiltinAliasWithIntegerArgs`、`BuiltinAliasWithDoubleArgs`、`SwapSlotAlias`）：建议来自 `GENERAL_VARIABLES` 和/或 `CONTAINER_SLOT_VARIABLES` 的匹配变量名，按数字类型兼容性过滤。

3. **位于新的别名名称位置**（光标在空格分隔符之后）：建议按部分标记过滤的所有已知别名名称。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [Alias](../alias/Alias.java/Alias.md) | 提供建议列表的注册映射 |
| [VarAlias](../alias/builtinAlias/VarAlias.java/VarAlias.md) | 数字参数建议的变量映射 |
| [LockAlias](../alias/builtinAlias/LockAlias.java/LockAlias.md) | `+lockKey` 建议的可锁定动作列表 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
