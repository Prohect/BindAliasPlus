# AliasWithoutArgs

仅按名称触发（无参数）的别名的标记接口。提供 `putToAliasesWithoutArgs` / `putToAliasesWithoutArgs_notSuggested` 注册方法。

所有用户定义的别名（`UserAlias`）都实现此接口，若干内置的单动作别名也是如此。

## 字段

_无。_

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [putToAliasesWithoutArgs](putToAliasesWithoutArgs.md) | `default T putToAliasesWithoutArgs(String key)` | 注册到 `aliasesWithoutArgs`（建议） |
| [putToAliasesWithoutArgs_notSuggested](putToAliasesWithoutArgs_notSuggested.md) | `default T putToAliasesWithoutArgs_notSuggested(String key)` | 注册到 `aliasesWithoutArgs_notSuggested`（内部） |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AliasWithArgs](AliasWithArgs.java/AliasWithArgs.md) | 带参数的对应另一类 |
| [BuiltinAliasWithoutArgs](BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | 提供无键重载的抽象基类 |
| [UserAlias](UserAlias.java/UserAlias.md) | 唯一非内置的 AliasWithoutArgs |
| [Alias](Alias.java/Alias.md) | 声明注册映射的根接口 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
