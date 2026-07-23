# run method (src/client/java/com/github/prohect/alias/builtinAlias/DebugOverlayAlias.java)

Shows or hides the F3 debug overlay.

## Syntax

```java
public DebugOverlayAlias run(String args)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | `String` | `"1"` (show / `+debugOverlay`) or `"0"` (hide / `-debugOverlay`) |

## Remarks

- Calls `Minecraft.debugEntries.setOverlayVisible(flag)` to control the overlay.
- The debug keys (F3 series) are intercepted at the GLFW level, not polled via `KeyMapping`, so direct API access is used instead of key simulation.
- The overlay includes: FPS, TPS, coordinates, facing direction, biome, entity count, memory usage, chunk updates, and render distance.

## See Also

| Item | Description |
|------|-------------|
| [DebugOverlayAlias](DebugOverlayAlias.md) | Class documentation |

_Documented for Commit: [2003c5c](https://github.com/Prohect/BindAliasPlus/tree/2003c5c648f2214e6b8c099a0db1ec96a130246b)_
