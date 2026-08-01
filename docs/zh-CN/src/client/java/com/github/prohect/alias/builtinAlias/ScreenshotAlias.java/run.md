# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/ScreenshotAlias.java）

解析 +/- 参数并在按下事件时通过原版 F2 按键路径触发截图。

## 语法

```java
public com.github.prohect.alias.builtinAlias.ScreenshotAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | `"1"` 拍摄截图，`"0"` 为无操作 |

## 备注

**算法：**

1. 通过 `parseArgs(args)` 解析 `args`——设置 `this.flag`。
2. 如果 flag 为 false（松开），则立即返回（无操作）。
3. 如果 `isUnderTextInputScreen()` 为 true，则立即返回。
4. 如果 `mc.player` 为 null，则立即返回。
5. 调用 `ScreenshotRecorder.saveScreenshot(mc.runDirectory, mc.getFramebuffer(), msg -> mc.execute(() -> mc.inGameHud.getChatHud().addMessage(msg)))`——触发原版截图流程（与按 F2 相同）。（Yarn：`ScreenshotRecorder.saveScreenshot()`；Mojang：`handleGlobalKeyPress()`）

**返回值：** `this`（流畅返回）。

**副作用：** 拍摄截图并将其保存到 Minecraft 截图目录。使用原版基于时间戳的文件名。

**界面抑制：** 文本输入界面打开时被取消。

**注意：** 只有 `+screenshot`（flag=1）形式会采取行动。`-screenshot` 是无操作。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ScreenshotAlias](ScreenshotAlias.md) | 类概览 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
