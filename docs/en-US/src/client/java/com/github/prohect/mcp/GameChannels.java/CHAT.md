# CHAT field (src/client/java/com/github/prohect/mcp/GameChannels.java)

## Syntax

```java
public static final String CHAT = "chat"
```

## Remarks

Channel name constant for game chat messages (server, system, and player messages). Fed by [`ChatComponentMixin`](../../mixin/client/ChatComponentMixin.java/README.md) which captures all three `ChatComponent` message-entry points. Messages are plain-text strings as returned by `Component.getString()`. Non-coalescing: each message is a separate entry.

## See Also

| Item | Description |
|------|-------------|
| [ChatComponentMixin](../../mixin/client/ChatComponentMixin.java/README.md) | Feeder of this channel |
| [MOD](MOD.md) | The mod-log channel |
| [SOUND](SOUND.md) | The sound-event channel |
| [RECIPE](RECIPE.md) | The recipe-unlock channel |
