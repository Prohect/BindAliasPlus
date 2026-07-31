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
3. Gets `mc.getDebugHud()`, calls `shouldShowDebugHud()` to check the current state, then calls `toggleDebugHud()` when the desired state differs from the current state — bypassing the vanilla KeyBinding system because the F3 key is intercepted at the GLFW level and not exposed as a pollable key binding.

No `KeyBinding.setPressed()` or `timesPressed` manipulation — just the `DebugHud.toggleDebugHud()` visibility toggle.

## See Also

| Item | Description |
|------|-------------|
| [AdvancementsAlias.run()](../AdvancementsAlias.java/run.md) | Key-based toggle pattern |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
