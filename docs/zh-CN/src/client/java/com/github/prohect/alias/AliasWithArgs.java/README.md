# AliasWithArgs

接受参数的内置别名的标记接口。提供 `putToAliasesWithArgs` / `putToAliasesWithArgs_notSuggested` 注册方法。

**约束**：只有内置别名实现此接口。用户别名始终实现 `AliasWithoutArgs`。

## 字段

_无。_

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [putToAliasesWithArgs](putToAliasesWithArgs.md) | `default T putToAliasesWithArgs(String key)` | 注册到 `aliasesWithArgs`（建议） |
| [putToAliasesWithArgs_notSuggested](putToAliasesWithArgs_notSuggested.md) | `default T putToAliasesWithArgs_notSuggested(String key)` | 注册到 `aliasesWithArgs_notSuggested`（内部） |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AliasWithoutArgs](AliasWithoutArgs.java/AliasWithoutArgs.md) | 无参数对应的另一类 |
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | 提供无键重载的抽象基类 |
| [Alias](Alias.java/Alias.md) | 声明注册映射的根接口 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
