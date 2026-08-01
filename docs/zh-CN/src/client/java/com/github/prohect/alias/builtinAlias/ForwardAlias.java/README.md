# ForwardAlias

原版前进移动键（forwardKey / W 键）的开关别名。继承 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 模式。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| _（除 `BuiltinAliasWithBooleanArgs.flag` 外无其他）_ | `boolean` | 继承：`+forward` 为 true，`-forward` 为 false |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `ForwardAlias run(String args)` | 按下或松开 `options.forwardKey`；仅在文本输入界面上抑制按下 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BackAlias](../BackAlias.java/BackAlias.md) | 后退移动 |
| [LeftAlias](../LeftAlias.java/LeftAlias.md) | 左侧移 |
| [RightAlias](../RightAlias.java/RightAlias.md) | 右侧移 |
| [KeyboardInputMixin](../../../mixin/KeyboardInputMixin.java/KeyboardInputMixin.md) | 读取移动键状态的 mixin |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
