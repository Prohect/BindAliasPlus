# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/ReapplyAlias.java）

将动作名称解析为内置别名，如果按键当前被按住则调用 `reapplyToGameKeyMapping()`。

## 语法

```java
public com.github.prohect.alias.builtinAlias.ReapplyAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 动作名称（例如 `"forward"`、`"attack"`、`"+forward"`、`"sneak"`、`"-left"`） |

## 备注

**算法：**

1. 如果 `args` 为 null 或空白，记录一条警告并返回。
2. 从 `args` 中去掉 `+` 或 `-` 前缀得到 `cleanName`。
3. 派生内置名称：`"builtin" + 首字母大写的 cleanName`。
4. 先在 `aliasesWithArgs` 中查找别名，然后在 `aliasesWithArgs_notSuggested` 中查找。
5. 如果找到且是 `flag == true` 的 `BuiltinAliasWithBooleanArgs`，调用 `reapplyToGameKeyMapping()`。
6. 否则，记录一条关于别名未找到或未被按住的警告。

**解析示例：**
- `"forward"` → `"builtinForward"`
- `"+sneak"` → `"builtinSneak"`
- `"-attack"` → `"builtinAttack"`
- `"playerList"` → `"builtinPlayerList"`
- `"openInventory"` → `"builtinOpenInventory"`

**返回值：** `this`（流畅式返回）。

**副作用：** 将按住的按键重新断言到游戏的按键映射状态。如果按键当前未被按住，则为无操作。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ReapplyAlias](ReapplyAlias.md) | 类概览 |
| [SUPPORTED_ACTIONS](SUPPORTED_ACTIONS.md) | 所有受支持的动作名称 |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/reapplyToGameKeyMapping.md) | 在匹配的别名上调用的方法 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
