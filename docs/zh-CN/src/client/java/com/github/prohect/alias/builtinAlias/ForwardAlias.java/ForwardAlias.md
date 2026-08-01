# ForwardAlias (src/client/java/com/github/prohect/alias/builtinAlias/ForwardAlias.java)

模拟前进移动键（W 键）的内置别名。继承 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 开关模式。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.ForwardAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.ForwardAlias>
```

## 静态初始化

_无。_

## 备注

注册名为 `"builtinForward"`。用法：`+forward` 按下，`-forward` 松开。文本输入界面打开时按下事件被抑制；移动在非文本 GUI 界面（物品栏等）上有效。移动通过 `KeyboardInputMixin` 注入。

实现操纵 `MinecraftClient.getInstance().options.forwardKey`：

- `setPressed(flag)` — 按住或松开按键
- `timesPressed++` — 按下时递增点击计数

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BackAlias](../BackAlias.java/BackAlias.md) | 后退移动的对应实现 |
| [LeftAlias](../LeftAlias.java/LeftAlias.md) | 左侧移的对应实现 |
| [RightAlias](../RightAlias.java/RightAlias.md) | 右侧移的对应实现 |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | 读取 forwardKey 状态用于移动 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
