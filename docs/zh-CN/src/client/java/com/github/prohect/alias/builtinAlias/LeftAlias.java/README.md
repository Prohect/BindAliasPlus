# LeftAlias

原版左移侧移键（keyLeft / A 键）的开关别名。继承自 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 模式。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| _（除 `BuiltinAliasWithBooleanArgs.flag` 外无其他）_ | `boolean` | 继承自基类：`+left` 为 true，`-left` 为 false |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `LeftAlias run(String args)` | 按下或松开 `options.keyLeft`；仅抑制文本输入界面的按下事件 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [RightAlias](../RightAlias.java/RightAlias.md) | 右移侧移 |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | 前进移动 |
| [BackAlias](../BackAlias.java/BackAlias.md) | 后退移动 |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | 读取移动键状态的 mixin |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
