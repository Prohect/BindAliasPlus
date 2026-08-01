# putToAliasesWithArgs_notSuggested 方法（src/client/java/com/github/prohect/alias/AliasWithArgs.java）

## 语法

```java
public default T putToAliasesWithArgs_notSuggested(String key)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `key` | `String` | 要注册的别名名称 |

## 返回值

返回 `this`，以支持流畅的构建器链式调用。

## 备注

与 `putToAliasesWithArgs` 相同，但改为注册到 `Alias.aliasesWithArgs_notSuggested`。此映射中的别名**不**显示在命令建议中，但完全可以执行——`UserAlias.run()` 在别名链执行期间会检查此映射（在 `aliasesWithoutArgs_notSuggested` 之后、`aliasesWithArgs` 之前）。

用于用户不应在自动补全中看到的内部别名（例如 `builtinDrop`、`builtinLock`）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [putToAliasesWithArgs](putToAliasesWithArgs.md) | 建议变体 |
| [BuiltinAliasWithArgs.putToAliasesWithArgs_notSuggested](BuiltinAliasWithArgs.java/putToAliasesWithArgs_notSuggested.md) | 无键重载 |
| [aliasesWithArgs_notSuggested](Alias.java/aliasesWithArgs_notSuggested.md) | 此方法写入的映射 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
