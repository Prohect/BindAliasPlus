# WaitAliasRecord

延迟任务记录：保存别名链定义，在 N 刻过后执行它。由 `WaitAlias` 和 `MinecraftClientMixin` 刻驱动使用。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `ticks` | `int` | 距离执行的剩余刻数（与游戏刻同单位） |
| `definition` | `String`（final） | 到期时执行的别名链定义字符串 |
| `reapplyToGameKeyMapping` | `boolean` | 为 true 时，definition 是用于按键重新应用的内置别名名称 |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [tick](tick.md) | `tick()` | 递减计数器；到达 0 时执行定义 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [WaitAlias](../WaitAlias.java/README.md) | 创建者和任务列表持有者 |
| [UserAlias](../../UserAlias.java/README.md) | 到期时调用的链执行器 |
| [MinecraftClientMixin](../../../mixin/MinecraftClientMixin.java/README.md) | 刻驱动 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
