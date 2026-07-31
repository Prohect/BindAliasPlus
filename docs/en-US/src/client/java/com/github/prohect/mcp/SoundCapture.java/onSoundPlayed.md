# onSoundPlayed method (src/client/java/com/github/prohect/mcp/SoundCapture.java)

## Syntax

```java
@Override
public void onSoundPlayed(SoundInstance sound, WeightedSoundSet soundSet, float range)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `sound` | `SoundInstance` | The sound being played, providing spatial position (`getX/Y/Z`) |
| `soundSet` | `WeightedSoundSet` | The sound set definition, providing the subtitle text via `getSubtitle()` |
| `range` | `float` | The audible range of this sound (may be `Float.POSITIVE_INFINITY` for global sounds) |

## Remarks

Implementation of `SoundInstanceListener#onSoundPlayed` — the callback invoked by the client `SoundManager` whenever a sound is played to the listener. This is the same hook the vanilla subtitle overlay uses, so exactly the sounds that would show a HUD caption are reported.

**Processing steps:**

1. **Subtitle check:** Calls `soundSet.getSubtitle()`. If `null` (sound has no subtitle), returns immediately — only subtitle-audible sounds are captured.
2. **Player check:** Gets `MinecraftClient.getInstance().player`. If `null` (not in a world), returns.
3. **Audibility gate:** Computes the squared distance from the sound source to the player and skips sounds beyond `range` (mirroring `SubtitleOverlay.isAudibleFrom`). If `range` is infinite, all sounds pass.
4. **Direction formatting:** Calls `directionOf(p, dx, dy, dz)` to compute the yaw/pitch relative to the player's view and distance, formatted as `"yaw±N pitch±N D.Dm"` or `"here D.Dm"`.
5. **Channel posting:** Posts the formatted message `"[client_tick:N] subtitle [direction]"` to `GameChannels.SOUND` via `GameChannels.postCoalescing`.

**Coalescing:** `postCoalescing` deduplicates identical messages arriving within the same tick (e.g., multiple zombies groaning simultaneously), so the SOUND channel reports each distinct subtitle+direction pair at most once per tick.

**Error handling:** The entire method body is wrapped in a try-catch that silently swallows exceptions — this prevents any failure in the MCP sound capture from breaking the vanilla sound engine.

The 26.x (Mojang) equivalent was called `onPlaySound` and implemented `SoundEventListener#onPlaySound(SoundInstance, WeighedSoundEvents, float)` — the rename reflects the 1.21.x Yarn mapping where both the listener interface and its method changed names.

## See Also

| Item | Description |
|------|-------------|
| [SoundCapture](SoundCapture.md) | The enclosing class |
| [directionOf](directionOf.md) | Direction formatter called by this method |
| [GameChannels.postCoalescing](../../mcp/GameChannels.java/postCoalescing.md) | Channel posting with deduplication |
| [register](register.md) | Registers this listener on the sound manager |

*Documented for Commit: [9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0](https://github.com/Prohect/BindAlias/tree/9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0)*
