# putToAliasesWithoutArgs_notSuggested 方法（src/client/java/com/github/prohect/alias/BuiltinAliasWithoutArgs.java）

## 语法

```java
public T putToAliasesWithoutArgs_notSuggested()
```

## 返回值

返回 `this`，以支持流畅的构建器链式调用。

## 备注

无键重载——以 `this.builtinAliasName` 作为键，将 `this` 注册到 `Alias.aliasesWithoutArgs_notSuggested`。在此注册的别名可执行，但从用户建议中隐藏。用于 `FPS`、`TPS`、`TPS2` 等内部 / 视图切换类别名，以及锁定包装别名。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [putToAliasesWithoutArgs](putToAliasesWithoutArgs.md) | 建议变体 |
| [builtinAliasName](builtinAliasName.md) | 用于注册的键 |
| [aliasesWithoutArgs_notSuggested](Alias.java/aliasesWithoutArgs_notSuggested.md) | 目标映射 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
