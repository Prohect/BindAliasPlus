# BackAlias

原版后退移动键（keyDown / S 键）的开关别名。继承自 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 模式。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| _（除 `BuiltinAliasWithBooleanArgs.flag` 外无其他）_ | `boolean` | 继承自基类：`+back` 为 true，`-back` 为 false |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `BackAlias run(String args)` | 按下或松开 `options.keyDown`；仅抑制文本输入界面的按下事件 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | 前进移动对应别名 |
| [LeftAlias](../LeftAlias.java/LeftAlias.md) | 左移侧移对应别名 |
| [RightAlias](../RightAlias.java/RightAlias.md) | 右移侧移对应别名 |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | 读取移动键状态的 mixin |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
