# BuiltinAliasWithStringArgs

接收自由形式字符串参数的内置别名的抽象基类。将别名定义分隔符覆盖为 `;`，使包含空格的参数（说话文本、命令、别名定义）不会破坏链的解析。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| [divider4AliasDefinition](divider4AliasDefinition.md) | `static final char` | 分号 `';'`——遮蔽 `Alias.divider4AliasDefinition` |

## 方法

_此层级无方法（没有公共的 `parseArgs`；每个子类自行处理其参数解析）。从 `BuiltinAliasWithArgs` 继承注册方法。_

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | 父类 |
| [Alias.divider4AliasDefinition](Alias.java/divider4AliasDefinition.md) | 在此被覆盖的默认空格分隔符 |
| [builtinAlias](builtinAlias/README.md) | 具体的字符串参数实现（say、sendCommand、alias、...） |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
