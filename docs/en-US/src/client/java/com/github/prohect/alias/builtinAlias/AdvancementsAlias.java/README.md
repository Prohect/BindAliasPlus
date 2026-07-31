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

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
