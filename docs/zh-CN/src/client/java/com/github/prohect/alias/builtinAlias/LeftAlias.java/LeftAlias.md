# LeftAlias (src/client/java/com/github/prohect/alias/builtinAlias/LeftAlias.java)

模拟左移侧移移动键（A 键）的内置别名。继承自 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 开关模式。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.LeftAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.LeftAlias>
```

## 静态初始化

_无。_

## 备注

注册名为 `"builtinLeft"`。用法：`+left` 按下，`-left` 松开。文本输入界面上按下事件会被抑制；在非文本 GUI 界面上有效。移动通过 `KeyboardInputMixin` 注入。

实现操纵 `Minecraft.options.keyLeft`：

- `setDown(flag)` — 按住或松开按键
- `clickCount++` — 按下时递增点击计数

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [RightAlias](../RightAlias.java/RightAlias.md) | 右移侧移对应别名 |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | 前进移动 |
| [BackAlias](../BackAlias.java/BackAlias.md) | 后退移动 |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | 读取 keyLeft 状态以驱动移动 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
