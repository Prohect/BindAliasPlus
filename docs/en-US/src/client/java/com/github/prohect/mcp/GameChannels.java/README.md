# GameChannels

Channel-based message hub feeding the MCP response envelope. Maintains four named channels (`chat`, `mod`, `sound`, `recipe`) with independent buffers, thread-safe posting, and coalescing for repeating sounds. A Log4j appender on the `"bind-alias"` logger feeds the `mod` channel.

## Fields

| Name | Type | Description |
|------|------|-------------|
| [CHAT](CHAT.md) | `String` (static, `"chat"`) | Channel for game chat messages (server/system/player) |
| [MOD](MOD.md) | `String` (static, `"mod"`) | Channel for the mod's own log output |
| [SOUND](SOUND.md) | `String` (static, `"sound"`) | Channel for sound events (coalescing) |
| [RECIPE](RECIPE.md) | `String` (static, `"recipe"`) | Channel for newly unlocked recipe notifications |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [post](post.md) | `static void post(String channel, String message)` | Posts a new, independent message entry to a channel |
| [postCoalescing](postCoalescing.md) | `static void postCoalescing(String channel, String key, String message)` | Posts a message with coalescing by key — updates in place if an undrained entry with the same key exists |
| [drain](drain.md) | `static Map<String, List<String>> drain()` | Returns new messages since the previous drain; zero-cost when nothing new |
| [resetAll](resetAll.md) | `static void resetAll()` | Marks all channels as read (called on world join) |
| [init](init.md) | `static void init()` | Registers the Log4j appender for the `MOD` channel |

## See Also

| Item | Description |
|------|-------------|
| [StateTracker](StateTracker.java/README.md) | Drains channels into the MCP response envelope |
| [ChatComponentMixin](../../mixin/client/ChatComponentMixin.java/README.md) | Feeds `CHAT` |
| [SoundCapture](SoundCapture.java/README.md) | Feeds `SOUND` |
| [ClientPacketListenerMixin](../../mixin/client/ClientPacketListenerMixin.java/README.md) | Feeds `RECIPE` |
