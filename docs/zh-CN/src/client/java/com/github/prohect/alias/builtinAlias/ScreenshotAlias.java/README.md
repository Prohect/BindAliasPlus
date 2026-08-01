# ScreenshotAlias

在按下事件时拍摄 Minecraft 截图的开关别名。用法：`+screenshot`（松开 `-screenshot` 为无操作）。

## 字段

_无公共/受保护字段（从 `BuiltinAliasWithBooleanArgs` 继承 `flag`）。_

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `run(String args)` | flag=1 时：通过 `ScreenshotRecorder.saveScreenshot()` 拍摄截图 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [LogAlias](../LogAlias.java/README.md) | 写入模组日志（另一个输出别名） |
| [SayAlias](../SayAlias.java/README.md) | 发送聊天消息（另一个通信别名） |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
