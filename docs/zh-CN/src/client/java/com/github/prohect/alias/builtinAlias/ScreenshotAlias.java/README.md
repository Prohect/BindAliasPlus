# ScreenshotAlias

按下事件时截取 Minecraft 截图的开关别名。用法：`+screenshot`（松开 `-screenshot` 为空操作）。

## 字段

_无公共/受保护字段（从 `BuiltinAliasWithBooleanArgs` 继承 `flag`）。_

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `run(String args)` | flag=1 时：通过带 `keyScreenshot` 按键的 `handleGlobalKeyPress` 截取截图 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [LogAlias](../LogAlias.java/README.md) | 写入模组日志（另一个输出别名） |
| [SayAlias](../SayAlias.java/README.md) | 发送聊天消息（另一个通信别名） |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
