# tick 方法（src/client/java/com/github/prohect/alias/builtinAlias/WaitAliasRecord.java）

递减刻计数器。到达 0 时，执行延迟的定义并将此记录从等待列表中移除。

## 语法

```java
public int tick()
```

## 备注

**算法：**

1. 递减 `ticks`。
2. 如果 `ticks == 0`：
   a. 如果 `reapplyToGameKeyMapping` 为 true：
      - 在 `aliasesWithArgs` 和 `aliasesWithArgs_notSuggested` 中查找 `definition`。
      - 如果找到且是 `BuiltinAliasWithBooleanArgs`，调用 `reapplyToGameKeyMapping()`。
   b. 否则：
      - 创建 `new UserAlias(definition)` 并调用 `run("")`。
   c. 从 `WaitAlias.tasksWaiting` 中移除 `this`。
   d. 返回 1（任务已执行）。
3. 返回 0（任务仍在等待）。

**返回值：** 延迟定义已执行则返回 1，仍在等待则返回 0。

**并发修改：** 由于此方法在遍历期间调用 `tasksWaiting.remove(this)`，调用代码（在 `MinecraftClientMixin` 中）必须安全处理并发修改。

**副作用：** 当刻数到达 0 时，执行延迟的别名链或重新应用按住的按键。根据定义包含的内容，这可能触发各种副作用。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [WaitAliasRecord](WaitAliasRecord.md) | 类概览 |
| [WaitAlias](../WaitAlias.java/run.md) | 创建此记录的实例 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
