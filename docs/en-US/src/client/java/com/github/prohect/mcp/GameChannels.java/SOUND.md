# SOUND field (src/client/java/com/github/prohect/mcp/GameChannels.java)

## Syntax

```java
public static final String SOUND = "sound"
```

## Remarks

Channel name constant for sound events. Fed by [`SoundCapture`](SoundCapture.java/README.md), which is registered as a `SoundEventListener` on the client `SoundManager` — the same hook the vanilla subtitle overlay uses. Only subtitle-audible sounds within hearing range are reported. Message format: `[client_tick:N] SoundName [yaw±N pitch±N D.Dm]`. This is the only coalescing channel: repeating sounds with the same name are updated in place with an `" xN"` counter rather than appended.

## See Also

| Item | Description |
|------|-------------|
| [SoundCapture](SoundCapture.java/README.md) | Feeder of this channel |
| [postCoalescing](postCoalescing.md) | The coalescing post method used for this channel |
| [CHAT](CHAT.md) | The chat channel |
| [MOD](MOD.md) | The mod-log channel |
| [RECIPE](RECIPE.md) | The recipe-unlock channel |
