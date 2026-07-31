# SoundCapture (src/client/java/com/github/prohect/mcp/SoundCapture.java)

## Syntax

```java
public final class SoundCapture implements net.minecraft.client.sounds.SoundEventListener
```

## Static Initializer

_None._

## Remarks

Feeds the [`GameChannels.SOUND`](GameChannels.java/SOUND.md) channel. Implements `SoundEventListener` and is registered on the client `SoundManager` — the same hook the vanilla subtitle overlay uses, so exactly the sounds that would show a HUD caption are reported.

Message format: `[client_tick:N] SoundName [yaw±N pitch±N D.Dm]`, e.g., `[client_tick:123] Zombie groans [yaw-40 pitch+20 4.2m]`. The direction is the yaw/pitch of the sound source **relative to the listener's view at the moment the sound was heard**, rounded to 20° steps (coarse on purpose — the ear is not a protractor). Sounds at the listener's own position collapse to `here D.Dm`. Repeats of the same sound coalesce via `GameChannels.postCoalescing`.

The `directionOf` method is also reused by `GameStateCollector.playersJson` for player directional formatting.

## See Also

| Item | Description |
|------|-------------|
| [GameChannels.SOUND](GameChannels.java/SOUND.md) | Destination channel |
| [GameChannels.postCoalescing](GameChannels.java/postCoalescing.md) | Coalescing post for repeating sounds |
| [register](register.md) | Registers on the sound manager |
| [onPlaySound](onPlaySound.md) | The event listener callback |
| [directionOf](directionOf.md) | 3D direction formatter (also used by `playersJson`) |
