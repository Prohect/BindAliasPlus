# aliasesWithArgs 字段（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
public static final HashMap<String, AliasWithArgs<?>> aliasesWithArgs
```

所有接受参数的内置别名的全局映射。键是 `builtinAliasName` 字符串（例如 `"slot"`、`"yaw"`、`"var"`、`"say"`）。由 `BuiltinAliasWithArgs.putToAliasesWithArgs()` 在 `BindAliasClient.onInitializeClient()` 的客户端初始化期间填充。

## 备注

这是**主要**的接受参数映射——命令建议即由此派生。此处的别名会向用户建议。

**读取方**：`UserAlias.run()` 和 `UserAlias.runInternal()` 在执行别名链时从此映射查找别名（在查找顺序中最后检查）。`reapply` 别名会遍历此映射和 `aliasesWithArgs_notSuggested` 中所有布尔参数别名。`KeyBoardMixin` 将按键事件路由到此映射和 `withoutArgs` 映射中找到的别名。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
