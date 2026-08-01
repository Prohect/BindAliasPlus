# JumpAlias

原版跳跃按键绑定（jumpKey / 空格键）的开关别名。继承 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 模式。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| _（除 `BuiltinAliasWithBooleanArgs.flag` 外无其他）_ | `boolean` | 继承：`+jump` 为 true，`-jump` 为 false |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `JumpAlias run(String args)` | 按下或松开 `options.jumpKey`；仅在文本输入界面上抑制按下 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SneakAlias](../SneakAlias.java/SneakAlias.md) | 潜行键的对应实现 |
| [SprintAlias](../SprintAlias.java/SprintAlias.md) | 疾跑键的对应实现 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
