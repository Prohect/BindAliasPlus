# BuiltinAliasWithoutArgs

不接受参数的内置别名的抽象基类。存储 `builtinAliasName` 并提供无键注册重载。所有可由按键事件触发的内置别名（esc、toggleInventory、swapHand、pickItem 等）都扩展此类。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| [builtinAliasName](builtinAliasName.md) | `@NotNull String` | 用于注册和查找的名称 |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [putToAliasesWithoutArgs](putToAliasesWithoutArgs.md) | `T putToAliasesWithoutArgs()` | 使用 `builtinAliasName` 注册到 `aliasesWithoutArgs` |
| [putToAliasesWithoutArgs_notSuggested](putToAliasesWithoutArgs_notSuggested.md) | `T putToAliasesWithoutArgs_notSuggested()` | 使用 `builtinAliasName` 注册到 `aliasesWithoutArgs_notSuggested` |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AliasWithoutArgs](AliasWithoutArgs.java/AliasWithoutArgs.md) | 此类实现的接口 |
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | 带参数的对应类 |
| [UserAlias](UserAlias.java/UserAlias.md) | 非内置的 AliasWithoutArgs——**不**扩展此类 |
| [builtinAlias](builtinAlias/README.md) | 具体实现 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
