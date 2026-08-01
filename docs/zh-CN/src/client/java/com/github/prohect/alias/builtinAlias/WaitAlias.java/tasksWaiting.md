# tasksWaiting 字段（src/client/java/com/github/prohect/alias/builtinAlias/WaitAlias.java）

所有当前正在等待的延迟别名任务的静态列表。

## 语法

```java
public static final java.util.ArrayList<com.github.prohect.alias.builtinAlias.WaitAliasRecord> tasksWaiting
```

## 备注

**用途：** 保存所有进行中的 `WaitAliasRecord` 实例。每个条目代表一个等待其刻计数器到期后执行的延迟别名链。

**写入者：**
- `WaitAlias.run(String, String)` — 当 `flag > 0` 时添加新记录。
- `WaitAliasRecord.tick()` — 当计数器到达 0 时移除记录（自我移除）。

**读取者：** `MinecraftClientMixin` — 每个游戏刻遍历并对每个条目调用 `.tick()`。MCP nap 机制也与此列表交互。

**线程安全：** 仅从游戏线程访问。`ArrayList` 在无同步的情况下使用。

**重要行为：** 列表在 `MinecraftClientMixin` 刻期间被遍历。由于 `WaitAliasRecord.tick()` 在计数器到期时会从列表中移除 `this`，遍历必须处理并发修改。mixin 通过反向遍历或在移除前收集到期任务来避免此问题。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
