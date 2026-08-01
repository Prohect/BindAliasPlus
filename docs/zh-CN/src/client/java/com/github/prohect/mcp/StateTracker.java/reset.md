# reset 方法（src/client/java/com/github/prohect/mcp/StateTracker.java）

## 语法

```java
public static synchronized void reset()
```

## 备注

遗忘基线状态快照：把 `last` 置为空 map，将 `lastContainer`、`lastHotbarItems`、`lastHotbarEmpty` 置为 null，并把 `baselineJoinTick` 重置为 `Long.MIN_VALUE`。在加入世界/断开连接时调用，使下一次 `begin()` 调用无论 `full` 参数如何都被强制为完整模式（包含所有状态成员）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [begin](begin.md) | 下一次调用将产生完整快照 |
