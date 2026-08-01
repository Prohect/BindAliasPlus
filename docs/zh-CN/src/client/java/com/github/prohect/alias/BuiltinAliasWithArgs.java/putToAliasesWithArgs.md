# putToAliasesWithArgs 方法（src/client/java/com/github/prohect/alias/BuiltinAliasWithArgs.java）

## 语法

```java
public T putToAliasesWithArgs()
```

## 返回值

返回 `this`，以支持流畅的构建器链式调用。

## 备注

无键重载——以 `this.builtinAliasName` 作为键，将 `this` 注册到 `Alias.aliasesWithArgs`。等价于调用父接口的 `AliasWithArgs.putToAliasesWithArgs(this.builtinAliasName)`。

这是具体内置别名首选的注册方法，因为名称在构造时已知，无需重复传入。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [putToAliasesWithArgs_notSuggested](putToAliasesWithArgs_notSuggested.md) | 不建议变体 |
| [builtinAliasName](builtinAliasName.md) | 用于注册的键 |
| [AliasWithArgs.putToAliasesWithArgs](AliasWithArgs.java/putToAliasesWithArgs.md) | 带显式键的接口默认方法 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
