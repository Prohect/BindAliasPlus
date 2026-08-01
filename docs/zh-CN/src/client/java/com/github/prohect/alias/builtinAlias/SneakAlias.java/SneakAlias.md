# SneakAlias (src/client/java/com/github/prohect/alias/builtinAlias/SneakAlias.java)

模拟按住/松开潜行键（Shift）的开关别名（`+sneak` / `-sneak`）。继承 `BuiltinAliasWithBooleanArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.SneakAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.SneakAlias>
```

## 静态初始化

_无。_

## 备注

**别名名称：** `builtinSneak`（内部，通过 `+sneak` / `-sneak` 暴露）。

**行为：**
- `+sneak`（flag=1）：按下原版 `sneakKey` 按键绑定（Shift），使玩家潜行/蹲下。潜行防止从边缘掉落并降低玩家高度。
- `-sneak`（flag=0）：松开 `sneakKey` 按键绑定。

**界面抑制：** 当 `Alias.isUnderTextInputScreen()` 返回 true 时，按下事件（`+sneak`）被取消。松开事件从不被抑制。在非文本界面（物品栏、容器等）上，别名继续工作。

**重新应用行为：** 继承自 `BuiltinAliasWithBooleanArgs`——界面切换后，如果 `flag` 为 true，`reapplyToGameKeyMapping()` 重新应用按键。在 `ReapplyAlias.SUPPORTED_ACTIONS` 中列为 `"sneak"`。

**移动注入：** 与其他移动别名一样，这通过 `key.setPressed(flag)` 直接设置按键绑定状态，由 `KeyboardInputMixin` 拦截以实现与窗口焦点无关的移动。按下事件时递增 `timesPressed`，使游戏注册初始按下动作。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SprintAlias](../SprintAlias.java/SprintAlias.md) | 疾跑键（另一个修饰性移动键） |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | 前进移动 |
| [ReapplyAlias](../ReapplyAlias.java/ReapplyAlias.md) | 界面切换后重新应用被按住的按键 |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | 开关别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
