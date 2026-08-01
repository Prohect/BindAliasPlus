# GameChannels（src/client/java/com/github/prohect/mcp/GameChannels.java）

## 语法

```java
public final class com.github.prohect.mcp.GameChannels
```

## 静态初始化

_参见 [static-init](static-init.md)。_

## 备注

基于 channel（消息通道）的消息中心，为 MCP 响应 envelope（状态信封）供数。维护四个命名 channel——`chat`、`mod`、`sound`、`recipe`——各自拥有独立的按插入顺序排列的缓冲区与单调递增的游标。channel 是线程安全的，支持两种发布模式：

- **标准发布**（`post`）：每条消息作为新条目追加。用于聊天、模组日志与配方消息，这些场景下每个事件相互独立。
- **合并发布**（`postCoalescing`）：同 key 的未排空条目就地更新（替换文本、累加计数）而非追加。用于声音 channel，将重复声音（脚步声、环境噼啪声）折叠为一行带 `" xN"` 计数的更新行。

所有 channel 的缓冲上限为 `MAX_BUFFER = 100` 条；缓冲区满时淘汰最旧条目。`drain()` 方法返回自上次 drain 以来发布的消息，无新消息到达时零开销。挂在 `"bind-alias"` 日志器上的 Log4j appender 将模组自身日志输出送入 `mod` channel。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [StateTracker.finish](StateTracker.java/finish.md) | `drain()` 的唯一调用方——将 channel 排空进 MCP 响应 envelope |
| [ChatComponentMixin](../../mixin/client/ChatComponentMixin.java/README.md) | 为 `CHAT` channel 供数 |
| [ClientPacketListenerMixin](../../mixin/client/ClientPacketListenerMixin.java/README.md) | 为 `RECIPE` channel 供数 |
| [SoundCapture](SoundCapture.java/README.md) | 为 `SOUND` channel 供数 |
| [init](init.md) | 为 `MOD` channel 注册 Log4j appender |
| [post](post.md) | 标准（非合并）消息发布 |
| [postCoalescing](postCoalescing.md) | 合并消息发布（声音 channel） |
| [drain](drain.md) | 返回自上次 drain 以来的新消息 |
