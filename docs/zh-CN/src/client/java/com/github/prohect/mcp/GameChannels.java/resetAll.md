# resetAll 方法（src/client/java/com/github/prohect/mcp/GameChannels.java）

## 语法

```java
public static void resetAll()
```

## 备注

将每个 channel 的 `lastSent` 游标推进到其当前 `cursor`，从而把所有 channel 标记为已读。另外清空所有合并用 `byKey` map。此方法在加入世界时调用，这样玩家进入世界前在标题界面累积的过期噪音就不会投递给 MCP 调用方。线程安全。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [drain](drain.md) | 返回消息的常规 drain |
| [StateTracker.reset](StateTracker.java/reset.md) | 同样在加入世界时重置状态跟踪 |
