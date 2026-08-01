# divider4AliasDefinition 字段（src/client/java/com/github/prohect/alias/BuiltinAliasWithStringArgs.java）

## 语法

```java
public static final char divider4AliasDefinition = ';'
```

覆盖默认的别名定义分隔符。使用 `';'`（分号）而不是 `' '`（空格），因为字符串参数别名的参数中可能包含空格。

## 备注

此字段**遮蔽**（而非覆盖）`Alias.divider4AliasDefinition`。当 `UserAlias` 在 `WaitAlias` 之后重建延迟链时，会检查别名是否为 `BuiltinAliasWithStringArgs` 的实例，如果是，则使用此分号分隔符而不是默认的空格。

具体子类（`SayAlias`、`AliasAlias`、`BindAlias`、`UnbindAlias`、`SendCommandAlias`、`LogAlias`、`LocalSayAlias`、`ReapplyAlias`、`ReloadCFGAlias`、`RunAlias`、`ApplyRecipeAlias`）都受益于此覆盖，因为它们的字符串参数天然包含空格（例如 `say\"hello world"`）。

在 `UserAlias.run()` 中重建延迟链时，`AliasAlias`、`BindAlias` 或 `UnbindAlias` 类型的别名会得到特殊处理：其参数中的分号会转换回空格，并且别名会用 `;` 分隔符重新编码。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [Alias.divider4AliasDefinition](Alias.java/divider4AliasDefinition.md) | 被此字段遮蔽的默认空格分隔符 |
| [UserAlias.run](UserAlias.java/run.md) | 链重建时使用此分隔符的位置 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
