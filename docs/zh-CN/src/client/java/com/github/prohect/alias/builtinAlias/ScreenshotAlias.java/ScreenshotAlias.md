# ScreenshotAlias (src/client/java/com/github/prohect/alias/builtinAlias/ScreenshotAlias.java)

通过原版 F2 代码路径触发 Minecraft 截图的开关别名。继承自 `BuiltinAliasWithBooleanArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.ScreenshotAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.ScreenshotAlias>
```

## 静态初始化

_无。_

## 备注

**别名名称：** `builtinScreenshot`（内部，通过 `+screenshot` / `-screenshot` 暴露）。

**行为：** 在 `+screenshot`（flag=1）事件时，调用 `mc.handleGlobalKeyPress(mc.options.keyScreenshot.key, false)`——与原生 F2 按键按下完全相同的代码路径。`-screenshot`（flag=0）事件为空操作（立即返回）。

**为何用 BooleanArgs 别名而非一次性别名：** 这是为了遵循所有触发界面的别名使用 `+/-` 记号的模式，保持一致性。只有 `+` 形式实际执行操作。

**实现理由：** 与其直接调用内部 `Screenshot` 类的方法（可能因 Minecraft 分支而异），此别名通过公共的 `handleGlobalKeyPress` API 路由，确保跨 Mojang/Yarn 映射的兼容性。

**界面抑制：** 当 `Alias.isUnderTextInputScreen()` 返回 true 时，按下事件被取消。

**要求：** `mc.player` 必须非 null。若为 null 则静默返回。

**副作用：** 将 PNG 截图保存到 Minecraft 截图目录。文件名遵循原版惯例（基于时间戳）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | 开关别名的基类 |
| [SayAlias](../SayAlias.java/SayAlias.md) | 发送聊天（另一个通信别名） |
| [LogAlias](../LogAlias.java/LogAlias.md) | 写入模组日志（另一个输出别名） |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
