# divider4AliasArgs 字段（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
public static final char divider4AliasArgs = '\\'
```

在单次别名调用中，用于分隔别名名称与其参数、以及分隔各参数之间的字符。值为反斜杠（`\`）。

## 备注

`getDefinitionSplits()` 使用它来拆分定义，例如将 `"slot\3"` 拆分为 `["slot", "3"]`，或将 `"swapSlot\1\c2"` 拆分为 `["swapSlot", "1", "c2"]`。`UserAlias` 重建延迟链时也会用到它：参数用此分隔符重新拼接。

选择反斜杠是因为它是一个安全、非字母数字的字符，在模组自身语法之外，不太可能出现在 Minecraft 命令参数或聊天消息中。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
