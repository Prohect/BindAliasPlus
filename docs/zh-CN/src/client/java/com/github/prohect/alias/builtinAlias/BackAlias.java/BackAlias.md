# BackAlias (src/client/java/com/github/prohect/alias/builtinAlias/BackAlias.java)

模拟后退移动键（S 键）的内置别名。继承自 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 开关模式。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.BackAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.BackAlias>
```

## 静态初始化

_无。_

## 备注

注册名为 `"builtinBack"`。用法：`+back` 按下，`-back` 松开。文本输入界面打开时按下事件会被抑制，但与攻击/使用（在所有界面上被屏蔽）不同，移动键在非文本界面（物品栏、工作台等）上**确实**有效。移动通过 `KeyboardInputMixin` 注入，该 mixin 读取原版 KeyMapping 的按下状态。

实现操纵 `Minecraft.options.keyDown`：

- `setDown(flag)` — 按住或松开按键
- `clickCount++` — 按下时递增点击计数

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | 前进移动对应别名 |
| [LeftAlias](../LeftAlias.java/LeftAlias.md) | 左移侧移对应别名 |
| [RightAlias](../RightAlias.java/RightAlias.md) | 右移侧移对应别名 |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | 读取 keyDown 状态以驱动移动 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
