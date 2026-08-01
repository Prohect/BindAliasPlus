# AttackAlias

原版攻击（左键）按键绑定的开关别名。继承 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 模式。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| _（除 `BuiltinAliasWithBooleanArgs.flag` 外无其他）_ | `boolean` | 继承：`+attack` 为 true，`-attack` 为 false |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `AttackAlias run(String args)` | 按下或松开 `options.attackKey`；在文本输入界面上抑制按下 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UseAlias](../UseAlias.java/UseAlias.md) | 右键的对应实现 |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | 基类：parseArgs、reapplyToGameKeyMapping |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | 移动键开关别名 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
