# SprintAlias (src/client/java/com/github/prohect/alias/builtinAlias/SprintAlias.java)

模拟按住/松开疾跑键（Ctrl）的开关别名（`+sprint` / `-sprint`）。继承 `BuiltinAliasWithBooleanArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.SprintAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.SprintAlias>
```

## 静态初始化

_无。_

## 备注

**别名名称：** `builtinSprint`（内部，通过 `+sprint` / `-sprint` 暴露）。

**行为：**
- `+sprint`（flag=1）：按下原版 `sprintKey` 按键绑定（Ctrl）。与 `+forward` 组合时，玩家疾跑。
- `-sprint`（flag=0）：松开 `sprintKey` 按键绑定。

**原版疾跑机制：** 疾跑要求疾跑键和前进键同时按住。如果玩家停止前进、撞到方块或食物耗尽（饥饿值 <= 6），疾跑会被取消。

**界面抑制：** 当 `Alias.isUnderTextInputScreen()` 返回 true 时，按下事件（`+sprint`）被取消。松开事件从不被抑制。在非文本界面上，别名继续工作。

**重新应用行为：** 继承自 `BuiltinAliasWithBooleanArgs`——界面切换后，如果 `flag` 为 true，`reapplyToGameKeyMapping()` 重新应用按键。在 `ReapplyAlias.SUPPORTED_ACTIONS` 中列为 `"sprint"`。

**移动注入：** 通过 `key.setPressed(flag)` 直接设置按键绑定状态，由 `KeyboardInputMixin` 拦截以实现与窗口焦点无关的疾跑。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SneakAlias](../SneakAlias.java/SneakAlias.md) | 潜行键（另一个修饰性移动） |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | 前进移动（实际疾跑所需） |
| [ReapplyAlias](../ReapplyAlias.java/ReapplyAlias.md) | 界面切换后重新应用被按住的按键 |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | 开关别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
