# BackAlias

原版后退移动键（backKey / S 键）的开关别名。继承 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 模式。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| _（除 `BuiltinAliasWithBooleanArgs.flag` 外无其他）_ | `boolean` | 继承：`+back` 为 true，`-back` 为 false |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `BackAlias run(String args)` | 按下或松开 `options.backKey`；仅在文本输入界面上抑制按下 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | 前进移动的对应实现 |
| [LeftAlias](../LeftAlias.java/LeftAlias.md) | 左侧移的对应实现 |
| [RightAlias](../RightAlias.java/RightAlias.md) | 右侧移的对应实现 |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | 读取移动键状态的 mixin |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
