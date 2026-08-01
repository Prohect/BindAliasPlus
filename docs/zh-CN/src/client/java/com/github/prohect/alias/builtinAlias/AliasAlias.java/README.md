# AliasAlias

向服务器发送 `/alias` 命令以在运行时定义或重新定义用户别名的字符串参数别名。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| _（除继承外无）_ | | |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `AliasAlias run(String args)` | 规范化参数并向服务器发送 `/alias <name> <definition>` |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BindAlias](../BindAlias.java/BindAlias.md) | 向服务器发送 `/bind` 命令 |
| [BuiltinAliasWithStringArgs](../../BuiltinAliasWithStringArgs.java/BuiltinAliasWithStringArgs.md) | 字符串参数别名的基类 |
| [UserAlias](../../UserAlias.java/UserAlias.md) | 用户别名的本地表示 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
