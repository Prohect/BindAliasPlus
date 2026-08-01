# BuiltinAliasWithArgs

所有接受参数的内置别名的抽象基类。存储 `builtinAliasName` 并提供无键注册重载。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| [builtinAliasName](builtinAliasName.md) | `@NotNull String` | 用于注册和查找的名称 |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [putToAliasesWithArgs](putToAliasesWithArgs.md) | `T putToAliasesWithArgs()` | 使用 `builtinAliasName` 注册到 `aliasesWithArgs` |
| [putToAliasesWithArgs_notSuggested](putToAliasesWithArgs_notSuggested.md) | `T putToAliasesWithArgs_notSuggested()` | 使用 `builtinAliasName` 注册到 `aliasesWithArgs_notSuggested` |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AliasWithArgs](AliasWithArgs.java/AliasWithArgs.md) | 此类实现的接口 |
| [BuiltinAliasWithBooleanArgs](BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | 布尔子类 |
| [BuiltinAliasWithIntegerArgs](BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) | 整数子类 |
| [BuiltinAliasWithDoubleArgs](BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) | 双精度子类 |
| [BuiltinAliasWithStringArgs](BuiltinAliasWithStringArgs.java/BuiltinAliasWithStringArgs.md) | 字符串子类 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
