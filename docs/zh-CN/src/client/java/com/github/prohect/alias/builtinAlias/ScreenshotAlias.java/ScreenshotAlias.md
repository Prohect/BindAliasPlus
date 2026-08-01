# ScreenshotAlias (src/client/java/com/github/prohect/alias/builtinAlias/ScreenshotAlias.java)

通过原版 F2 代码路径触发 Minecraft 截图的开关别名。继承 `BuiltinAliasWithBooleanArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.ScreenshotAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.ScreenshotAlias>
```

## 静态初始化

_无。_

## 备注

**别名名称：** `builtinScreenshot`（内部，通过 `+screenshot` / `-screenshot` 暴露）。

**行为：** 在 `+screenshot`（flag=1）事件时，调用 `ScreenshotRecorder.saveScreenshot(mc.runDirectory, mc.getFramebuffer(), msg -> mc.execute(() -> mc.inGameHud.getChatHud().addMessage(msg)))`——与原生 F2 按键按下完全相同的代码路径。`-screenshot`（flag=0）事件是无操作（立即返回）。

**为什么用 BooleanArgs 别名而不是一次性别名：** 这遵循所有界面触发别名使用 `+/-` 表示法的一致性模式。只有 `+` 形式实际采取行动。

**实现理由：** 直接使用游戏的帧缓冲区和运行目录调用 `ScreenshotRecorder.saveScreenshot()`。成功消息通过 `mc.execute()` 发布到游戏内聊天 HUD，以确保它在渲染线程上运行。（Yarn：`ScreenshotRecorder.saveScreenshot()`；Mojang：`handleGlobalKeyPress()`）

**界面抑制：** 当 `Alias.isUnderTextInputScreen()` 返回 true 时，按下事件被取消。

**要求：** `mc.player` 必须非空。为 null 时静默返回。

**副作用：** 将 PNG 截图保存到 Minecraft 截图目录。文件名遵循原版约定（基于时间戳）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | 开关别名的基类 |
| [SayAlias](../SayAlias.java/SayAlias.md) | 发送聊天（另一个通信别名） |
| [LogAlias](../LogAlias.java/LogAlias.md) | 写入模组日志（另一个输出别名） |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
