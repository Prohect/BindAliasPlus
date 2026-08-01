# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/DebugOverlayAlias.java）

通过直接切换调试浮层的可见性来处理 `+debugOverlay`（显示）和 `-debugOverlay`（隐藏）。

## 语法

```java
public com.github.prohect.alias.builtinAlias.DebugOverlayAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 显示（`+debugOverlay`）为 `"1"`，隐藏（`-debugOverlay`）为 `"0"` |

## 备注

1. 调用 `parseArgs(args)` 设置 `this.flag`。
2. **界面抑制（仅按下）：** 如果 `flag` 为 true 且文本输入界面已打开，则立即返回。松开事件始终处理。
3. 获取 `mc.getDebugHud()`，调用 `shouldShowDebugHud()` 检查当前状态，然后在期望状态与当前状态不同时调用 `toggleDebugHud()`——绕过原版 KeyBinding 系统，因为 F3 键在 GLFW 层被拦截，不作为可轮询的按键绑定暴露。

不涉及 `KeyBinding.setPressed()` 或 `timesPressed` 操纵——只有 `DebugHud.toggleDebugHud()` 的可见性切换。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AdvancementsAlias.run()](../AdvancementsAlias.java/run.md) | 基于按键的切换模式 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
