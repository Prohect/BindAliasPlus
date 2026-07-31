# GameChannels (src/client/java/com/github/prohect/mcp/GameChannels.java)

## Syntax

```java
public final class com.github.prohect.mcp.GameChannels
```

## Static Initializer

_See [static-init](static-init.md)._

## Remarks

Channel-based message hub feeding the MCP response envelope. Maintains four named channels — `chat`, `mod`, `sound`, `recipe` — each with independent insertion-ordered buffers and monotonic cursors. Channels are thread-safe and support two posting modes:

- **Standard posting** (`post`): each message is appended as a new entry. Used for chat, mod, and recipe messages where each event is independent.
- **Coalescing posting** (`postCoalescing`): undrained entries with the same key are updated in place (text replaced, counter incremented) instead of appended. Used for the sound channel to collapse repeating sounds (footsteps, ambient crackles) into one updating line with an `" xN"` counter.

All channels are bounded to `MAX_BUFFER = 100` entries; oldest entries are evicted when the buffer is full. The `drain()` method returns messages posted since the previous drain and is zero-cost when nothing new arrived. A Log4j appender on the `"bind-alias"` logger feeds the `mod` channel with the mod's own log output.

## See Also

| Item | Description |
|------|-------------|
| [StateTracker.finish](StateTracker.java/finish.md) | The only caller of `drain()` — drains channels into the MCP response envelope |
| [ChatComponentMixin](../../mixin/client/ChatComponentMixin.java/README.md) | Feeds the `CHAT` channel |
| [ClientPacketListenerMixin](../../mixin/client/ClientPacketListenerMixin.java/README.md) | Feeds the `RECIPE` channel |
| [SoundCapture](SoundCapture.java/README.md) | Feeds the `SOUND` channel |
| [init](init.md) | Registers the Log4j appender for the `MOD` channel |
| [post](post.md) | Standard (non-coalescing) message post |
| [postCoalescing](postCoalescing.md) | Coalescing message post (sound channel) |
| [drain](drain.md) | Returns new messages since the previous drain |
