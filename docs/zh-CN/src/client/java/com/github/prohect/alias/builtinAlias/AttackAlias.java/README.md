# AttackAlias

原版攻击（左键）按键绑定的开关别名。继承自 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 模式。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| _（除 `BuiltinAliasWithBooleanArgs.flag` 外无其他）_ | `boolean` | 继承自基类：`+attack` 为 true，`-attack` 为 false |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `AttackAlias run(String args)` | 按下或松开 `options.keyAttack`；在文本输入界面下抑制按下事件 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UseAlias](../UseAlias.java/UseAlias.md) | 右键对应别名 |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | 基类：parseArgs、reapplyToGameKeyMapping |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | 移动键开关别名 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
