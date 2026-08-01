# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/LeftAlias.java）

通过操纵原版左移侧移按键绑定来处理 `+left`（按下）和 `-left`（松开）。

## 语法

```java
public com.github.prohect.alias.builtinAlias.LeftAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | `"1"` 表示按下（`+left`），`"0"` 表示松开（`-left`） |

## 备注

1. 调用 `parseArgs(args)` 设置 `this.flag`。
2. **界面抑制（仅按下、仅文本输入界面）：** 若 `flag` 为 true 且文本输入界面打开，则立即返回。左移侧移在非文本界面上有效。松开事件始终处理。
3. 获取 `Minecraft.options.keyLeft`，按下时调用 `setDown(flag)` 并执行 `clickCount++`。

移动由 `KeyboardInputMixin` 每刻应用，它读取 `keyLeft.isDown()`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [RightAlias.run()](../RightAlias.java/run.md) | 右移侧移的相同模式 |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | 读取按键状态以驱动移动 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
