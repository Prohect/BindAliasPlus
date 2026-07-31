# onPlaySound method (src/client/java/com/github/prohect/mcp/SoundCapture.java)

## Syntax

```java
@Override
public void onPlaySound(SoundInstance sound, WeighedSoundEvents soundEvent, float range)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `sound` | `SoundInstance` | The sound being played, providing world position and other properties |
| `soundEvent` | `WeighedSoundEvents` | The sound event definition, providing the subtitle text |
| `range` | `float` | The audible range; sounds beyond this distance are skipped |

## Remarks

The `SoundEventListener` callback. Processing:

1. **Subtitle check**: if `soundEvent.getSubtitle()` is `null`, the sound has no HUD caption → returns immediately.
2. **Player check**: if the local player is null, returns.
3. **Audibility check**: computes the 3D distance between the player and the sound source; if `range` is not infinite and the distance exceeds it, returns (matching vanilla subtitle overlay behavior).
4. **Posting**: formats the message as `[client_tick:N] <subtitle> [<direction>]` and posts to `GameChannels.SOUND` via `postCoalescing`, using the subtitle text as the coalescing key.

Wrapped in a try-catch to ensure sound engine exceptions never crash the client.

## See Also

| Item | Description |
|------|-------------|
| [GameChannels.postCoalescing](GameChannels.java/postCoalescing.md) | The posting method |
| [directionOf](directionOf.md) | Directional formatter |
