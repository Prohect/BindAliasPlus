# SilentAlias（src/client/java/com/github/prohect/alias/builtinAlias/SilentAlias.java）

切换静默模式——抑制或恢复聊天界面中的模组反馈消息——的开关别名（`+silent` / `-silent`）。继承 `BuiltinAliasWithBooleanArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.SilentAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.SilentAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `builtinSilent`（内部，通过 `+silent` / `-silent` 暴露）。

**行为：**
- `+silent`（flag=1）：启用静默模式——设置 `BindAliasClient.silentMode = true`。大多数模组反馈消息（info、warn）在聊天界面中被抑制。
- `-silent`（flag=0）：禁用静默模式——设置 `BindAliasClient.silentMode = false`。反馈消息恢复正常。

**被抑制的内容：** 许多内置别名（卸载操作、slot、var 等）在其日志记录中检查 `silentMode` 标志。为 true 时，信息性反馈消息跳过日志记录。错误级日志和 `log` 别名输出通常**不**被抑制。

**无界面抑制：** 这是配置设置，不是游戏输入——它在任何界面上都能工作，包括文本输入界面。源码注释明确指出："这不是游戏操作，因此我们不需要取消文本输入界面的按下事件。"

**不要混淆：** 物品上的 `silent` 标签或原版 `/stopsound` 命令。此静默模式只抑制本地聊天界面中的模组内置反馈消息。

**典型用例：** 在运行一串会向聊天界面刷屏反馈消息的别名之前使用 `+silent`，然后使用 `-silent` 恢复正常反馈。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [LogAlias](../LogAlias.java/LogAlias.md) | 写入模组日志（不被静默模式抑制） |
| [BindAliasClient](../../../BindAliasClient.java/BindAliasClient.md) | 存储 `silentMode` 的客户端入口点 |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | 开关别名的基类 |
| [FreeCursorAlias](../FreeCursorAlias.java/FreeCursorAlias.md) | 另一个非游戏类开关别名 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
