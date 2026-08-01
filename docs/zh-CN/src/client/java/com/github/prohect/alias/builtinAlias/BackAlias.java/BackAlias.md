# BackAlias (src/client/java/com/github/prohect/alias/builtinAlias/BackAlias.java)

模拟后退移动键（S 键）的内置别名。继承 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 开关模式。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.BackAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.BackAlias>
```

## 静态初始化

_无。_

## 备注

注册名为 `"builtinBack"`。用法：`+back` 按下，`-back` 松开。文本输入界面打开时按下事件被抑制，但移动键在非文本界面（物品栏、工作台等）上**确实**有效，与在所有界面上都被阻止的 Attack/Use 不同。移动通过 `KeyboardInputMixin` 注入，它读取原版 KeyBinding 的按下状态。

实现操纵 `MinecraftClient.getInstance().options.backKey`：

- `setPressed(flag)` — 按住或松开按键
- `timesPressed++` — 按下时递增点击计数

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | 前进移动的对应实现 |
| [LeftAlias](../LeftAlias.java/LeftAlias.md) | 左侧移的对应实现 |
| [RightAlias](../RightAlias.java/RightAlias.md) | 右侧移的对应实现 |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | 读取 backKey 状态用于移动 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
