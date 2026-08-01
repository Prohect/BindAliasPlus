# WaitAlias（src/client/java/com/github/prohect/alias/builtinAlias/WaitAlias.java）

将别名链的执行延迟指定数量的客户端刻的内置别名。继承 `BuiltinAliasWithIntegerArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.WaitAlias extends com.github.prohect.alias.BuiltinAliasWithIntegerArgs<com.github.prohect.alias.builtinAlias.WaitAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `wait` — 用法：`wait\N`，其中 N 是在执行链的其余部分之前要等待的刻数。

**两个 run() 重载：**
1. `run(String args)` — **已弃用。** 通过 `parseArgs(args)` 解析刻数但**不**对其做任何事（只验证 N >= 0）。这是早期设计遗留。
2. `run(String args, String definition)` — **活跃。** `UserAlias` 在链执行期间使用的实际实现。`definition` 参数包含等待后要执行的别名链的其余部分。

**行为：**
- `wait\N`（N > 0）：创建 `WaitAliasRecord` 并将其添加到 `tasksWaiting` 列表。每个刻，`MinecraftClientMixin` 递减所有等待任务的计数器。当计数器到达 0 时，执行延迟的定义。
- `wait\0`：立即执行定义（不等待）。在时间上相当于 NOP。
- `wait\N`（N < 0）：记录一条错误——负刻值无效。

**任务执行：** 当等待任务到期时，`WaitAliasRecord.tick()` 创建新的 `UserAlias(definition)` 并对其调用 `run("")`，从而有效地恢复链。如果 `reapplyToGameKeyMapping` 为 true，则改为对相应的内置别名调用 `reapplyToGameKeyMapping()`。

**tickPrefix 考量：** wait 别名是少数可以将执行延迟跨越多个游戏刻的别名之一。这意味着等待前捕获的状态（例如通过 `getFullState`）在延迟链执行时可能已过期。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [WaitAliasRecord](WaitAliasRecord.md) | 保存延迟任务信息的记录 |
| [MinecraftClientMixin](../../../mixin/MinecraftClientMixin.java/MinecraftClientMixin.md) | 递减 wait 计数器的刻驱动 |
| [UserAlias](../../UserAlias.java/UserAlias.md) | 调用 `run(args, definition)` 的链执行器 |
| [BuiltinAliasWithIntegerArgs](../../BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) | 整数参数别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
