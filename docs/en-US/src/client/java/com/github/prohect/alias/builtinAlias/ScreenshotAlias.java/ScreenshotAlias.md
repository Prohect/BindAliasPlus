# ScreenshotAlias (src/client/java/com/github/prohect/alias/builtinAlias/ScreenshotAlias.java)

Agent-tooling alias that captures a screenshot via Minecraft's `Screenshot.grab()` method.

## Syntax

```java
public class ScreenshotAlias extends BuiltinAliasWithBooleanArgs<ScreenshotAlias>
```

## Static Initializer

_None._

## Remarks

Usage: `+screenshot` triggers a capture, `-screenshot` is a no-op (release).

Calls `Screenshot.grab(gameDirectory, renderTarget, callback)` directly, bypassing the key-binding system since `handleGlobalKeyPress` (26.2-only) is not available on 26.1.x.

The screenshot is saved to the game's screenshot directory with a timestamped filename, same as pressing F2.

## See Also

| Item | Description |
|------|-------------|
| [run](run.md) | Entry point for alias execution |

_Documented for Commit: [2003c5c](https://github.com/Prohect/BindAliasPlus/tree/2003c5c648f2214e6b8c099a0db1ec96a130246b)_
