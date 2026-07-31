# AdvancementsAlias

Switch alias for the vanilla advancements key binding (keyAdvancements / L key). Inherits the `+name`/`-name` pattern from `BuiltinAliasWithBooleanArgs`. The advancements screen opens on key release (polled by `Gui.consumeClick()`).

## Fields

| Name | Type | Description |
|------|------|-------------|
| _(none beyond `BuiltinAliasWithBooleanArgs.flag`)_ | `boolean` | Inherited: true for `+advancements`, false for `-advancements` |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `AdvancementsAlias run(String args)` | Press or release `options.keyAdvancements`; suppresses press on text-input screens |

## See Also

| Item | Description |
|------|-------------|
| [PlayerListAlias](../PlayerListAlias.java/PlayerListAlias.md) | Player list toggle |
| [DebugOverlayAlias](../DebugOverlayAlias.java/DebugOverlayAlias.md) | Debug overlay toggle |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
