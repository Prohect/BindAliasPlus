# UserAlias

用户定义的别名链——别名系统中的主要执行器。将空格分隔的定义字符串解析为各次别名调用，并通过全局注册映射分派它们。

直接实现 `AliasWithoutArgs<UserAlias>`（**不**扩展 `BuiltinAliasWithoutArgs`）。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `aliases` | `ArrayDeque<AliasRecord>` | 已解析的别名调用内部队列（由 `decodeArgs2Alias` 填充） |
| `args` | `String` | 原始定义字符串（构造后不可变） |
| `fromCFG` | `boolean` | 此别名是否从 CFG 文件加载 |
| `predefined` | `boolean` | 此别名是否受保护、不可覆盖 |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [decodeArgs2Alias](decodeArgs2Alias.md) | `private void decodeArgs2Alias(String)` | 将原始定义字符串解析为 `AliasRecord` 队列 |
| [getDefinitionString](getDefinitionString.md) | `String getDefinitionString()` | 返回原始定义字符串 |
| [isFromCFG](isFromCFG.md) | `boolean isFromCFG()` | CFG 加载标志的获取方法 |
| [setFromCFG](setFromCFG.md) | `void setFromCFG(boolean)` | CFG 加载标志的设置方法 |
| [isPredefined](isPredefined.md) | `boolean isPredefined()` | 保护标志的获取方法 |
| [run](run.md) | `UserAlias run(String)` | 执行别名链（入口点） |
| [runInternal](runInternal.md) | `void runInternal(List<UserAlias>)` | 带循环检测和链展开的递归执行 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AliasWithoutArgs](AliasWithoutArgs.java/AliasWithoutArgs.md) | UserAlias 实现的接口 |
| [AliasRecord](AliasRecord.java/AliasRecord.md) | 内部队列使用的 record 类型 |
| [Alias](Alias.java/Alias.md) | 根接口——声明映射和解析工具 |
| [AliasAlias](builtinAlias/AliasAlias.java/AliasAlias.md) | 创建 / 覆盖 UserAlias 的内置别名 |
| [BindAliasClient](BindAliasClient.java/BindAliasClient.md) | 注册 CFG 加载的 UserAlias 的位置 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
