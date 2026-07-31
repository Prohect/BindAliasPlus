# run method (src/client/java/com/github/prohect/alias/builtinAlias/DebugOverlayAlias.java)

Handles `+debugOverlay` (show) and `-debugOverlay` (hide) by directly toggling the debug overlay visibility.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.DebugOverlayAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | `"1"` for show (`+debugOverlay`), `"0"` for hide (`-debugOverlay`) |

## Remarks

1. Calls `parseArgs(args)` to set `this.flag`.
2. **Screen suppression (press only):** If `flag` is true and a text-input screen is open, returns immediately. Release events always process.
3. Calls `Minecraft.debugEntries.setOverlayVisible(flag)` directly — bypassing the vanilla KeyMapping system because the F3 key is intercepted at the GLFW level and not exposed as a pollable key binding.

No `KeyMapping.setDown()` or `clickCount` manipulation — just the overlay visibility toggle.

## See Also

| Item | Description |
|------|-------------|
| [AdvancementsAlias.run()](../AdvancementsAlias.java/run.md) | Key-based toggle pattern |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
