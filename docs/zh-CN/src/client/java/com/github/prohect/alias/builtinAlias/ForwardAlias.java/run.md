# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/ForwardAlias.java）

通过操纵原版前进按键绑定来处理 `+forward`（按下）和 `-forward`（松开）。

## 语法

```java
public com.github.prohect.alias.builtinAlias.ForwardAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | `"1"` 表示按下（`+forward`），`"0"` 表示松开（`-forward`） |

## 备注

1. 调用 `parseArgs(args)` 设置 `this.flag`。
2. **界面抑制（仅按下、仅文本输入界面）：** 若 `flag` 为 true 且文本输入界面打开，则立即返回。前进移动在非文本 GUI 界面上有效。松开事件始终处理。
3. 获取 `Minecraft.options.keyUp`，按下时调用 `setDown(flag)` 并执行 `clickCount++`。

移动由 `KeyboardInputMixin` 每刻应用，它读取 `keyUp.isDown()`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BackAlias.run()](../BackAlias.java/run.md) | 后退的相同模式 |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | 读取按键状态以驱动移动 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
