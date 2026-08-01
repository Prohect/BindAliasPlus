# DebugOverlayAlias (src/client/java/com/github/prohect/alias/builtinAlias/DebugOverlayAlias.java)

显示或隐藏调试浮层（F3 界面）的内置别名——FPS 图表、坐标、实体数量、区块缓存等。继承 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 开关模式。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.DebugOverlayAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.DebugOverlayAlias>
```

## 静态初始化

_无。_

## 备注

注册名为 `"builtinDebugOverlay"`。用法：`+debugOverlay` 显示，`-debugOverlay` 隐藏。与大多数基于按键的其他别名不同，调试浮层**不**通过原版 `KeyBinding` 驱动——F3 键在 Minecraft 的按键系统看到它之前就在 GLFW 层被拦截。因此，此别名完全绕过按键绑定系统，使用 `MinecraftClient.getInstance().getDebugHud()`：调用 `shouldShowDebugHud()` 检查当前状态，然后在期望状态与当前状态不同时调用 `toggleDebugHud()` 切换。

文本输入界面打开时按下事件被抑制。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AdvancementsAlias](../AdvancementsAlias.java/AdvancementsAlias.md) | 基于按键的切换 |
| [DebugHud](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a) | 原版类：调试饼图、FPS 图表、区块边界等（Yarn：`DebugHud`；Mojang：`DebugEntries`） |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
