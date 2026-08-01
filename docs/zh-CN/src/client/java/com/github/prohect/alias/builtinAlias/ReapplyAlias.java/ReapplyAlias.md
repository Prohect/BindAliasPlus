# ReapplyAlias（src/client/java/com/github/prohect/alias/builtinAlias/ReapplyAlias.java）

在界面切换后手动重新断言单个按住的开关别名（BooleanArgs）的内置别名。继承 `BuiltinAliasWithArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.ReapplyAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.ReapplyAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `reapply` — 用法：`reapply\action`。

**用途：** 原版 Minecraft 在打开新界面（`setScreen` 事件）时会松开所有按住的按键。此别名通过调用相应内置别名上的 `reapplyToGameKeyMapping()` 重新断言单个按住的按键，但仅当其 `flag` 当前为 true（即界面切换前该按键正被按住）时才执行。

**受支持的动作：** 在 `SUPPORTED_ACTIONS` 中定义：
- `attack`、`use`、`forward`、`back`、`left`、`right`、`jump`、`sneak`、`sprint`、`drop`、`openInventory`、`playerList`

**解析方式：** 获取动作名称，去掉任何 `+`/`-` 前缀，然后通过添加 `"builtin"` 前缀并将首字母大写来派生内部内置名称（例如 `forward` → `builtinForward`）。在 `aliasesWithArgs` 和 `aliasesWithArgs_notSuggested` 注册表中查找此名称。如果找到且是 `flag == true` 的 `BuiltinAliasWithBooleanArgs`，则调用 `reapplyToGameKeyMapping()`。

**错误处理：** 如果未提供动作名称，或解析出的内置别名未找到或当前未被按住，则记录一条警告。

**典型用法：** 在界面切换后的 UserAlias 序列末尾调用 `reapply\forward` 以重新断言按住的移动键。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | 提供 `reapplyToGameKeyMapping()` 的基类 |
| [WaitAlias](../WaitAlias.java/WaitAlias.md) | 延迟执行，可用于在界面切换后安排重新应用 |
| [SUPPORTED_ACTIONS](SUPPORTED_ACTIONS.md) | 受支持动作名称列表 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
