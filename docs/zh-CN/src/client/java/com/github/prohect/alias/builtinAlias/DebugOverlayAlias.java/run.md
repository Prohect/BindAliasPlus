# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/DebugOverlayAlias.java）

通过直接切换调试浮层可见性来处理 `+debugOverlay`（显示）和 `-debugOverlay`（隐藏）。

## 语法

```java
public com.github.prohect.alias.builtinAlias.DebugOverlayAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | `"1"` 表示显示（`+debugOverlay`），`"0"` 表示隐藏（`-debugOverlay`） |

## 备注

1. 调用 `parseArgs(args)` 设置 `this.flag`。
2. **界面抑制（仅按下）：** 若 `flag` 为 true 且文本输入界面打开，则立即返回。松开事件始终处理。
3. 直接调用 `Minecraft.debugEntries.setOverlayVisible(flag)`——绕过原版 KeyMapping 系统，因为 F3 键在 GLFW 层被拦截，不作为可轮询的按键绑定暴露。

无 `KeyMapping.setDown()` 或 `clickCount` 操纵——仅切换浮层可见性。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AdvancementsAlias.run()](../AdvancementsAlias.java/run.md) | 基于按键的切换模式 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
