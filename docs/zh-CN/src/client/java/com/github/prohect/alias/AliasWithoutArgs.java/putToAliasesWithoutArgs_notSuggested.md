# putToAliasesWithoutArgs_notSuggested 方法（src/client/java/com/github/prohect/alias/AliasWithoutArgs.java）

## 语法

```java
public default T putToAliasesWithoutArgs_notSuggested(String key)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `key` | `String` | 要注册的别名名称 |

## 返回值

返回 `this`，以支持流畅的构建器链式调用。

## 备注

与 `putToAliasesWithoutArgs` 相同，但改为注册到 `Alias.aliasesWithoutArgs_notSuggested`。此映射中的别名**不**显示在命令建议中，但在 `UserAlias.run()` 查找顺序中第二个被检查。

用于 `FPS`、`TPS`、`TPS2` 等内部 / 视图切换类别名，以及锁定包装别名。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [putToAliasesWithoutArgs](putToAliasesWithoutArgs.md) | 建议变体 |
| [BuiltinAliasWithoutArgs.putToAliasesWithoutArgs_notSuggested](BuiltinAliasWithoutArgs.java/putToAliasesWithoutArgs_notSuggested.md) | 无键重载 |
| [aliasesWithoutArgs_notSuggested](Alias.java/aliasesWithoutArgs_notSuggested.md) | 此方法写入的映射 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
