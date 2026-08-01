# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/ScreenshotAlias.java）

解析 +/- 参数并在按下事件时通过原版 F2 按键路径触发截图。

## 语法

```java
public com.github.prohect.alias.builtinAlias.ScreenshotAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | `"1"` 截取截图，`"0"` 为空操作 |

## 备注

**算法：**

1. 通过 `parseArgs(args)` 解析 `args`——设置 `this.flag`。
2. 若 flag 为 false（松开），立即返回（空操作）。
3. 若 `isUnderTextInputScreen()` 为 true，立即返回。
4. 若 `mc.player` 为 null，立即返回。
5. 调用 `mc.handleGlobalKeyPress(mc.options.keyScreenshot.key, false)`——触发原版截图管线（与按下 F2 相同）。

**返回值：** `this`（流畅式返回）。

**副作用：** 截取截图并保存到 Minecraft 截图目录。使用原版基于时间戳的文件名。

**界面抑制：** 文本输入界面打开时被取消。

**注意：** 只有 `+screenshot`（flag=1）形式会执行操作。`-screenshot` 为空操作。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ScreenshotAlias](ScreenshotAlias.md) | 类概览 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
