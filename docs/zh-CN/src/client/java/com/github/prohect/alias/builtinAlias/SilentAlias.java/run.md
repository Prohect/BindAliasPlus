# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/SilentAlias.java）

切换 `BindAliasClient` 上的全局静默模式标志。

## 语法

```java
public com.github.prohect.alias.builtinAlias.SilentAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | `"1"` 启用静默模式，`"0"` 禁用 |

## 备注

**算法：**

1. 通过 `parseArgs(args)` 解析 `args`——设置 `this.flag`（"1" 为 true，"0" 为 false）。
2. 设置 `BindAliasClient.silentMode = flag`。

**返回值：** `this`（流畅式返回）。

**副作用：** 设置全局 `silentMode` 标志。为 true 时，大多数内置别名的反馈消息（INFO 和 WARN 级别）在聊天界面中被抑制。错误级消息和 `log` 别名通常不被抑制。

**无界面抑制：** 在任何界面上都能工作，包括文本输入界面。这明确不是游戏操作。

**行为检查：** 许多内置别名在记录反馈消息前会检查 `BindAliasClient.silentMode`——例如 `UnloadCFGAliasesAlias`、`UnloadCFGVarsAlias` 等。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SilentAlias](SilentAlias.md) | 类概览 |
| [LogAlias](../LogAlias.java/run.md) | 写入模组日志（不被抑制） |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
