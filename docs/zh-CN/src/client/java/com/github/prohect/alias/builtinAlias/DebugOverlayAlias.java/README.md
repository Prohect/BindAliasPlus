# DebugOverlayAlias

调试浮层（F3 界面）的开关别名。与基于按键的别名不同，它直接调用 `debugEntries.setOverlayVisible()`，绕过 GLFW 层的 F3 拦截。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| _（除 `BuiltinAliasWithBooleanArgs.flag` 外无其他）_ | `boolean` | 继承自基类：`+debugOverlay` 为 true，`-debugOverlay` 为 false |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `DebugOverlayAlias run(String args)` | 通过 `debugEntries.setOverlayVisible()` 显示/隐藏调试浮层 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AdvancementsAlias](../AdvancementsAlias.java/AdvancementsAlias.md) | 基于按键的切换 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
