# BuiltinAliasWithDoubleArgs

单个参数为 `double` 的内置别名的抽象基类。`setYaw` 和 `setPitch` 使用此类。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| [flag](flag.md) | `double` | 解析后的 double 值（默认 `0.0`） |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [parseArgs](parseArgs.md) | `void parseArgs(String args)` | 通过变量查找或 `Double.parseDouble` 解析参数 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BuiltinAliasWithIntegerArgs](BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) | 整数对应类 |
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | 父类 |
| [VarAlias.resolveDouble](builtinAlias/VarAlias.java/resolveDouble.md) | parseArgs 使用的变量解析 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
