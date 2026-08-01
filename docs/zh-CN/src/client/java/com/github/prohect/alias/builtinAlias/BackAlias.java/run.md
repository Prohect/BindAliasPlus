# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/BackAlias.java）

通过操纵原版后退按键绑定来处理 `+back`（按下）和 `-back`（松开）。

## 语法

```java
public com.github.prohect.alias.builtinAlias.BackAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 按下（`+back`）为 `"1"`，松开（`-back`）为 `"0"`，由 `parseArgs(args)` 解析 |

## 备注

1. 调用 `parseArgs(args)` 设置 `this.flag`。
2. **界面抑制（仅按下、仅文本输入界面）：** 如果 `flag` 为 true 且文本输入界面已打开，则立即返回。与 Attack/Use 不同，移动键只在文本输入界面上被阻止——它们在物品栏、工作台等 GUI 界面上正常工作。松开事件仍被处理，以避免按键卡住。
3. 获取 `MinecraftClient.getInstance().options.backKey` 并调用 `setPressed(flag)`，按下时额外执行 `timesPressed++`。

移动由 `KeyboardInputMixin` 每刻读取，它检查 `backKey.isDown()` 以产生玩家的后退移动冲量。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ForwardAlias.run()](../ForwardAlias.java/run.md) | 前进的相同模式 |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | 读取按键状态用于移动 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
