# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/LeftAlias.java）

通过操纵原版左侧移按键绑定来处理 `+left`（按下）和 `-left`（松开）。

## 语法

```java
public com.github.prohect.alias.builtinAlias.LeftAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 按下（`+left`）为 `"1"`，松开（`-left`）为 `"0"` |

## 备注

1. 调用 `parseArgs(args)` 设置 `this.flag`。
2. **界面抑制（仅按下、仅文本输入界面）：** 如果 `flag` 为 true 且文本输入界面已打开，则立即返回。左侧移在非文本界面上有效。松开事件始终处理。
3. 获取 `MinecraftClient.getInstance().options.leftKey` 并调用 `setPressed(flag)`，按下时额外执行 `timesPressed++`。

移动由 `KeyboardInputMixin` 每刻应用，它读取 `leftKey.isDown()`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [RightAlias.run()](../RightAlias.java/run.md) | 右侧移的相同模式 |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | 读取按键状态用于移动 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
