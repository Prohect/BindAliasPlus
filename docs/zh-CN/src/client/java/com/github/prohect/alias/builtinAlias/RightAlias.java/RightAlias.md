# RightAlias (src/client/java/com/github/prohect/alias/builtinAlias/RightAlias.java)

模拟按住/松开右侧移键（D）的开关别名（`+right` / `-right`）。继承 `BuiltinAliasWithBooleanArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.RightAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.RightAlias>
```

## 静态初始化

_无。_

## 备注

**别名名称：** `builtinRight`（内部，通过 `+right` / `-right` 暴露）。

**行为：**
- `+right`（flag=1）：按下原版 `rightKey` 按键绑定（D），使玩家向右侧移。
- `-right`（flag=0）：松开 `rightKey` 按键绑定。

此别名与 `LeftAlias` 完全相同，只是针对相反的水平移动方向。

**界面抑制：** 当 `Alias.isUnderTextInputScreen()` 返回 true 时，按下事件（`+right`）被取消。松开事件从不被抑制。在非文本界面上，别名继续工作（与在所有界面上都被抑制的 `AttackAlias` 和 `UseAlias` 不同）。

**重新应用行为：** 继承自 `BuiltinAliasWithBooleanArgs`——界面切换后，如果 `flag` 为 true，`reapplyToGameKeyMapping()` 重新应用按键。在 `ReapplyAlias.SUPPORTED_ACTIONS` 中列为 `"right"`。`+right` 键的移动注入也由 `KeyboardInputMixin` 处理。

**移动注入：** 与原版不同，此别名直接在 `KeyBinding` 上触发 `key.setPressed(flag)`，由 `KeyboardInputMixin.tick()` 拦截以注入移动冲量，即使在游戏窗口没有焦点时也能工作。这与需要窗口焦点的原版按键按下队列不同。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [LeftAlias](../LeftAlias.java/LeftAlias.md) | 相反的水平移动（左侧移） |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | 前进移动 |
| [BackAlias](../BackAlias.java/BackAlias.md) | 后退移动 |
| [ReapplyAlias](../ReapplyAlias.java/ReapplyAlias.md) | 界面切换后重新应用被按住的按键 |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | 开关别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
