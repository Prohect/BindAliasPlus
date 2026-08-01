# aliasesWithoutArgs_notSuggested 字段（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
public static final HashMap<String, AliasWithoutArgs<?>> aliasesWithoutArgs_notSuggested
```

内部 / 不建议的不接受参数别名的全局映射。结构与 `aliasesWithoutArgs` 相同，但用于不应出现在用户建议中的别名（例如 `LockAlias_OnLock`、`LockAlias_Unlock`、视图切换类别名如 `FPS`/`TPS`/`TPS2`）。

## 备注

由 `BuiltinAliasWithoutArgs.putToAliasesWithoutArgs_notSuggested()` 填充。在 `UserAlias.run()` 查找顺序中第二个被检查，位于 `aliasesWithoutArgs` 之后。这些别名正常执行，但从自动补全中隐藏。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
