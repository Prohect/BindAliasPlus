# drain 方法（src/client/java/com/github/prohect/mcp/GameChannels.java）

## 语法

```java
public static Map<String, List<String>> drain()
```

## 返回值

一个按插入顺序排列的 `LinkedHashMap<String, List<String>>`（chat、mod、sound、recipe）。每个条目把 channel 名称映射到自上次 drain 以来的新消息。当没有任何 channel 有新消息时返回空 map。

## 备注

线程安全（在内部锁上同步）。对每个 channel，将全局游标与上次 drain 的 `lastSent` 快照比较。游标 > `lastSent` 的消息被收集，随后 `lastSent` 推进到当前游标。无新消息到达时零开销（所有游标都等于各自的 `lastSent`）。唯一调用方是 [`StateTracker.finish`](StateTracker.java/finish.md)，它在每次工具调用后把 channel 排空进 MCP 响应 envelope。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [StateTracker.finish](StateTracker.java/finish.md) | 唯一调用方 |
| [resetAll](resetAll.md) | 将所有 channel 标记为已读而不返回消息 |
| [post](post.md) | 标准消息发布 |
| [postCoalescing](postCoalescing.md) | 合并消息发布 |
