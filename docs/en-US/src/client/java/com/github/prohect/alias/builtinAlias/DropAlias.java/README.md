# DropAlias

Switch alias for the vanilla drop-item key binding (keyDrop / Q key). Supports immediate first drop, continuous dropping after an OS-style key-repeat delay, and special handling for container screens. Overrides `reapplyToGameKeyMapping()` to avoid extra drops after screen transitions.

## Fields

| Name | Type | Description |
|------|------|-------------|
| `INITIAL_DELAY_TICKS` | `static final int` | Delay before continuous drops begin (3 ticks, matching OS key-repeat gap) |
| `ticksHeld` | `private long` | Ticks elapsed since last press; reset to 0 on release |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `DropAlias run(String args)` | Press/release handler: immediate first drop + container-screen routing |
| [tickDrop](tickDrop.md) | `void tickDrop()` | Per-tick continuous drop driver, called from `MinecraftClientMixin` |
| [reapplyToGameKeyMapping](reapplyToGameKeyMapping.md) | `void reapplyToGameKeyMapping()` | Restore held state after screen transitions without extra drop |

## See Also

| Item | Description |
|------|-------------|
| [MinecraftClientMixin](../../../mixin/MinecraftClientMixin.java/MinecraftClientMixin.md) | Calls `tickDrop()` each client tick |
| [ReapplyAlias](../ReapplyAlias.java/ReapplyAlias.md) | Calls `reapplyToGameKeyMapping()` after screen transitions |
| [AttackAlias](../AttackAlias.java/AttackAlias.md) | Simpler switch alias without continuous tick driver |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
