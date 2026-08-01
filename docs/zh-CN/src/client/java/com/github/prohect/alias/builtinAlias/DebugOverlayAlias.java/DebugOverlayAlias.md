# DebugOverlayAlias (src/client/java/com/github/prohect/alias/builtinAlias/DebugOverlayAlias.java)

显示或隐藏调试浮层（F3 界面）的内置别名——FPS 图表、坐标、实体数量、区块缓存等。继承自 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 开关模式。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.DebugOverlayAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.DebugOverlayAlias>
```

## 静态初始化

_无。_

## 备注

注册名为 `"builtinDebugOverlay"`。用法：`+debugOverlay` 显示，`-debugOverlay` 隐藏。与大多数其他基于按键的别名不同，调试浮层**不**通过原版 `KeyMapping` 驱动——F3 键在 Minecraft 的按键系统看到它之前就在 GLFW 层被拦截。因此，此别名完全绕过按键绑定系统，直接设置 `Minecraft.debugEntries.setOverlayVisible(flag)`。

文本输入界面打开时，按下事件会被抑制。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AdvancementsAlias](../AdvancementsAlias.java/AdvancementsAlias.md) | 基于按键的切换 |
| [DebugEntries](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a) | 原版类：调试饼图、FPS 图表、区块边界等 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
