# WaitAliasRecord（src/client/java/com/github/prohect/alias/builtinAlias/WaitAliasRecord.java）

保存别名链定义并在指定数量的刻过后执行它的延迟任务记录。这是一个简单的可变记录式类，不是 Java `record`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.WaitAliasRecord
```

## 静态初始化器

_无。_

## 备注

**用途：** 表示单个延迟的别名链。当刻数 > 0 时由 `WaitAlias.run(String, String)` 创建。每个游戏刻，`MinecraftClientMixin` 对 `WaitAlias.tasksWaiting` 中的每个记录调用 `tick()`。

**生命周期：**
1. 以 `ticks`（倒计时）、`definition`（别名链字符串）和 `reapplyToGameKeyMapping` 标志创建。
2. 每个刻调用 `tick()` → 递减 `ticks`。
3. 当 `ticks` 到达 0 时：
   - 如果 `reapplyToGameKeyMapping` 为 true：查找名称匹配 `definition` 的内置别名并调用 `reapplyToGameKeyMapping()`。
   - 否则：创建新的 `UserAlias(definition)` 并调用 `run("")` 执行延迟的链。
4. 从 `WaitAlias.tasksWaiting` 中移除 `this`。

**字段：**

| 字段 | 类型 | 说明 |
|-------|------|-------------|
| `ticks` | int | 剩余刻数。每个客户端刻递减。 |
| `definition` | String（final） | 刻数到达 0 时要执行的别名链定义。 |
| `reapplyToGameKeyMapping` | boolean | 为 true 时，definition 被视为用于 `reapplyToGameKeyMapping()` 的内置别名名称。 |

**reapplyToGameKeyMapping 模式：** 当此标志为 true 时，`definition` 字段被假定为简单的别名名称（不是完整链）。到期时，记录在 `aliasesWithArgs` 和 `aliasesWithArgs_notSuggested` 中查找该名称，并在匹配的 `BuiltinAliasWithBooleanArgs` 实例上调用 `reapplyToGameKeyMapping()`。这在内部用于界面切换后延迟的按键重新应用。

**线程安全：** 仅从游戏线程访问。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [WaitAlias](../WaitAlias.java/WaitAlias.md) | `tasksWaiting` 列表的创建者和持有者 |
| [UserAlias](../../UserAlias.java/UserAlias.md) | 到期时调用的链执行器 |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | `reapplyToGameKeyMapping` 模式的目标 |
| [MinecraftClientMixin](../../../mixin/MinecraftClientMixin.java/MinecraftClientMixin.md) | 刻驱动 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
