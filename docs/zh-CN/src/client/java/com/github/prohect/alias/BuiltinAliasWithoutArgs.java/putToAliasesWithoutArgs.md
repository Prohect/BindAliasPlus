# putToAliasesWithoutArgs 方法（src/client/java/com/github/prohect/alias/BuiltinAliasWithoutArgs.java）

## 语法

```java
public T putToAliasesWithoutArgs()
```

## 返回值

返回 `this`，以支持流畅的构建器链式调用。

## 备注

无键重载——以 `this.builtinAliasName` 作为键，将 `this` 注册到 `Alias.aliasesWithoutArgs`。这是 `esc`、`toggleInventory`、`swapHand` 等内置无参数别名的标准注册方式。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [putToAliasesWithoutArgs_notSuggested](putToAliasesWithoutArgs_notSuggested.md) | 不建议变体 |
| [builtinAliasName](builtinAliasName.md) | 用于注册的键 |
| [AliasWithoutArgs.putToAliasesWithoutArgs](AliasWithoutArgs.java/putToAliasesWithoutArgs.md) | 带显式键的接口默认方法 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
