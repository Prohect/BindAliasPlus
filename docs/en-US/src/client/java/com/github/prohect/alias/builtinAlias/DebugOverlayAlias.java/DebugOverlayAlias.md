# DebugOverlayAlias (src/client/java/com/github/prohect/alias/builtinAlias/DebugOverlayAlias.java)

Agent-tooling alias that shows or hides the F3 debug overlay.

## Syntax

```java
public class DebugOverlayAlias extends BuiltinAliasWithBooleanArgs<DebugOverlayAlias>
```

## Static Initializer

_None._

## Remarks

Usage: `+debugOverlay` shows the overlay, `-debugOverlay` hides it.

Calls `debugEntries.setOverlayVisible(flag)` directly — bypasses the GLFW-level F3 key interception that Minecraft uses. The debug overlay shows FPS, coordinates, entity counts, chunk info, and other diagnostic data.

Intended to give an agent runtime information about the game state without needing to intercept raw rendering.

## See Also

| Item | Description |
|------|-------------|
| [run](run.md) | Entry point |

_Documented for Commit: [2003c5c](https://github.com/Prohect/BindAlias/tree/2003c5c648f2214e6b8c099a0db1ec96a130246b)_
