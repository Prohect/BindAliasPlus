# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/WaitAlias.java）

两个重载：已弃用的单参数 `run(String)` 和链执行期间使用的活跃双参数 `run(String, String)`。

## 语法

```java
// 已弃用的单参数重载
public com.github.prohect.alias.builtinAlias.WaitAlias run(java.lang.String)

// 活跃的双参数重载
public com.github.prohect.alias.builtinAlias.WaitAlias run(java.lang.String, java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 刻数（验证 >= 0）。通过 `VarAlias.resolveInt()` 支持变量。 |
| definition | String | 等待后要执行的别名链的其余部分（仅在双参数重载中） |

## 备注

**单参数 `run(String args)` — 已弃用：**

1. 通过 `parseArgs(args)` 解析 `args`——将 `flag` 设置为刻数。
2. 如果 `flag < 0`，记录一条错误：刻数必须为正。
3. **不**实际调度任何等待——这是遗留的占位。

**双参数 `run(String args, String definition)` — 活跃：**

1. 通过 `parseArgs(args)` 解析 `args`。支持变量解析。
2. 如果 `flag > 0`：创建 `WaitAliasRecord(flag, definition, false)` 并添加到 `tasksWaiting`。
3. 如果 `flag == 0`：立即执行——`new UserAlias(definition).run("")`。
4. 如果 `flag < 0`：记录一条错误。

**任务生命周期：** 每个刻，`MinecraftClientMixin` 对 `tasksWaiting` 中的所有条目调用 `WaitAliasRecord.tick()`。当计数器到达 0 时，记录执行其定义并从列表中移除自身。

**返回值：** `this`（流畅式返回）。

**副作用：** 调度延迟的别名链执行。**不**阻塞游戏——在等待期间其他别名和游戏逻辑继续运行。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [WaitAlias](WaitAlias.md) | 类概览 |
| [WaitAliasRecord](../WaitAliasRecord.java/WaitAliasRecord.md) | 延迟任务记录 |
| [tasksWaiting](tasksWaiting.md) | 任务列表 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
