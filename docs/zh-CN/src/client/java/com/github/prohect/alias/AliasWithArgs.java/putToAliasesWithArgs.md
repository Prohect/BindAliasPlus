# putToAliasesWithArgs 方法（src/client/java/com/github/prohect/alias/AliasWithArgs.java）

## 语法

```java
public default T putToAliasesWithArgs(String key)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `key` | `String` | 要注册的别名名称 |

## 返回值

返回 `this`，以支持流畅的构建器链式调用。

## 备注

将 `this` 别名以给定的 `key` 注册到全局 `Alias.aliasesWithArgs` 映射中。这是**建议**注册路径——在此注册的别名会出现在命令建议中。

此方法是**原始键**变体：它接受显式的键参数。`BuiltinAliasWithArgs` 子类提供了使用 `this.builtinAliasName` 的无键重载。这个原始键变体是为了灵活性而存在（例如，如果别名需要以与其内置名称不同的名称注册）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [putToAliasesWithArgs_notSuggested](putToAliasesWithArgs_notSuggested.md) | 不建议变体 |
| [BuiltinAliasWithArgs.putToAliasesWithArgs](BuiltinAliasWithArgs.java/putToAliasesWithArgs.md) | 使用 `builtinAliasName` 的无键重载 |
| [aliasesWithArgs](Alias.java/aliasesWithArgs.md) | 此方法写入的映射 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
