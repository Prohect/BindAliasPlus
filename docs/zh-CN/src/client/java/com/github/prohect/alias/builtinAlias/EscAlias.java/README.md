# EscAlias

用于关闭界面和切换暂停菜单的整数参数别名。支持仅关闭（`esc\0`）和切换（`esc\1`）两种模式。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| _（除 `BuiltinAliasWithIntegerArgs.flag` 外无）_ | `int` | 继承：0 表示仅关闭，1 表示切换 |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `EscAlias run(String args)` | 关闭当前界面；在切换模式下可选地打开暂停菜单 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BuiltinAliasWithIntegerArgs](../../BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) | 整数参数别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
