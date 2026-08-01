# aliasesWithArgs_notSuggested 字段（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
public static final HashMap<String, AliasWithArgs<?>> aliasesWithArgs_notSuggested
```

内部 / 不建议的内置接受参数别名的全局映射。结构与 `aliasesWithArgs` 相同，但用于不应出现在面向用户的命令建议中的别名（例如 `builtinDrop`、`builtinLock`）。

## 备注

由 `BuiltinAliasWithArgs.putToAliasesWithArgs_notSuggested()` 填充。这些别名仍然可执行——`UserAlias.run()` 在别名链执行期间会检查此映射——但建议引擎（`bind` 命令、自动补全）会跳过它们。

**查找顺序**：在 `UserAlias.run()` 中，`_notSuggested` 映射在主要映射**之后**、主要接受参数映射**之前**被检查：`aliasesWithoutArgs` → `aliasesWithoutArgs_notSuggested` → `aliasesWithArgs_notSuggested` → `aliasesWithArgs`。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
