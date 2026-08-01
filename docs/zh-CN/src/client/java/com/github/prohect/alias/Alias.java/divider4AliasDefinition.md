# divider4AliasDefinition 字段（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
public static final char divider4AliasDefinition = ' '
```

在别名链中分隔各次别名调用的字符。默认为空格（`' '`）。

## 备注

`getDefinitions()` 使用它来拆分链，例如将 `"+attack slot\1 wait\5 -attack"` 拆分为各条定义。`BuiltinAliasWithStringArgs` 将其覆盖为 `';'`，因为其别名（例如 `say`、`alias`、`sendCommand`）的参数中可能包含空格。

当 `UserAlias` 在 `WaitAlias` 之后重建延迟链时，会用此分隔符拼接剩余条目。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
