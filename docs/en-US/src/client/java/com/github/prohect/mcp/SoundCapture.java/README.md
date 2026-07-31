# SoundCapture

Feeds the `SOUND` channel by implementing `SoundInstanceListener` on the client `SoundManager`. Reports subtitle-audible sounds with precise directional info (yaw/pitch relative to the listener, rounded to 20° steps, plus distance).

## Fields

| Name | Type | Description |
|------|------|-------------|
| `INSTANCE` | `SoundCapture` (static, private, singleton) | The singleton instance registered on the sound manager |
| `DEG_STEP` | `double` (static, private, `20.0`) | Direction quantization step in degrees |

## Methods

**Lifecycle:**

| Name | Signature | Description |
|------|-----------|-------------|
| [register](register.md) | `static void register()` | Registers the singleton on the client `SoundManager` |

**Sound event handler:**

| Name | Signature | Description |
|------|-----------|-------------|
| [onSoundPlayed](onSoundPlayed.md) | `void onSoundPlayed(SoundInstance sound, WeightedSoundSet soundSet, float range)` | `SoundInstanceListener` callback — posts to `SOUND` channel with direction |

**Direction formatting (shared with GameStateCollector):**

| Name | Signature | Description |
|------|-----------|-------------|
| [directionOf](directionOf.md) | `static String directionOf(ClientPlayerEntity p, double dx, double dy, double dz)` | 3D direction formatter: `"yaw±N pitch±N D.Dm"` or `"here D.Dm"` |

**Helpers:**

| Name | Signature | Description |
|------|-----------|-------------|
| [clamp](clamp.md) | `static int clamp(double deg)` | Rounds angle to nearest 20° step |
| [normalize180](normalize180.md) | `static double normalize180(double deg)` | Normalizes angle to (-180, 180] |
| [signed](signed.md) | `static String signed(int v)` | Always-signed integer formatting |
| [fmt1](fmt1.md) | `static String fmt1(double v)` | 1-decimal-place double formatting |

## See Also

| Item | Description |
|------|-------------|
| [GameChannels.SOUND](GameChannels.java/SOUND.md) | Destination channel |
| [GameStateCollector.playersJson](GameStateCollector.java/playersJson.md) | Reuses `directionOf` for player formatting |
