# putToAliasesWithoutArgs 方法（src/client/java/com/github/prohect/alias/AliasWithoutArgs.java）

## 语法

```java
public default T putToAliasesWithoutArgs(String key)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `key` | `String` | 要注册的别名名称 |

## 返回值

返回 `this`，以支持流畅的构建器链式调用。

## 备注

将 `this` 别名以给定的 `key` 注册到全局 `Alias.aliasesWithoutArgs` 映射中。这是**建议**注册路径——在此注册的别名会出现在命令建议中，并在 `UserAlias.run()` 的别名链执行期间被**首先**检查。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [putToAliasesWithoutArgs_notSuggested](putToAliasesWithoutArgs_notSuggested.md) | 不建议变体 |
| [BuiltinAliasWithoutArgs.putToAliasesWithoutArgs](BuiltinAliasWithoutArgs.java/putToAliasesWithoutArgs.md) | 使用 `builtinAliasName` 的无键重载 |
| [aliasesWithoutArgs](Alias.java/aliasesWithoutArgs.md) | 此方法写入的映射 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
