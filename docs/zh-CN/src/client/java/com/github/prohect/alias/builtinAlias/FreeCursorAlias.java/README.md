# FreeCursorAlias

为开发者便利而切换自由光标模式的开关别名。启用后，系统光标保持自由，而游戏的鼠标逻辑（按住挖掘、相机旋转）照常运行。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| [freeCursor](freeCursor.md) | `public static boolean` | `MouseMixin` 读取的标志；为 true 时跳过操作系统级光标锁定 |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `FreeCursorAlias run(String args)` | 以安全的锁定状态切换启用/禁用自由光标模式 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [MouseMixin](../../../mixin/MouseMixin.java/MouseMixin.md) | 读取 `freeCursor` 以跳过操作系统级锁定调用 |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | 开关别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
