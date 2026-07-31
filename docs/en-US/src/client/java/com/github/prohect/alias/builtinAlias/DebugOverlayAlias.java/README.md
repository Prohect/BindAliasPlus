# DebugOverlayAlias

Switch alias for the debug overlay (F3 screen). Unlike key-based aliases, it directly calls `debugEntries.setOverlayVisible()` bypassing the GLFW-level F3 interception.

## Fields

| Name | Type | Description |
|------|------|-------------|
| _(none beyond `BuiltinAliasWithBooleanArgs.flag`)_ | `boolean` | Inherited: true for `+debugOverlay`, false for `-debugOverlay` |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `DebugOverlayAlias run(String args)` | Show/hide the debug overlay via `debugEntries.setOverlayVisible()` |

## See Also

| Item | Description |
|------|-------------|
| [AdvancementsAlias](../AdvancementsAlias.java/AdvancementsAlias.md) | Key-based toggle |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
