# ScreenshotAlias (src/client/java/com/github/prohect/alias/builtinAlias/ScreenshotAlias.java)

Switch alias that triggers a Minecraft screenshot via the vanilla F2 codepath. Extends `BuiltinAliasWithBooleanArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.ScreenshotAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.ScreenshotAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `builtinScreenshot` (internal, exposed via `+screenshot` / `-screenshot`).

**Behavior:** On the `+screenshot` (flag=1) event, calls `mc.handleGlobalKeyPress(mc.options.keyScreenshot.key, false)` — the exact same codepath as a native F2 keypress. The `-screenshot` (flag=0) event is a no-op (returns immediately).

**Why a BooleanArgs alias instead of one-shot:** This follows the pattern of all screen-triggering aliases using `+/-` notation for consistency. Only the `+` form actually takes action.

**Implementation rationale:** Rather than calling internal `Screenshot` class methods directly (which may vary across Minecraft branches), this alias routes through the public `handleGlobalKeyPress` API, ensuring compatibility across Mojang/Yarn mappings.

**Screen suppression:** The press event is cancelled when `Alias.isUnderTextInputScreen()` returns true.

**Requirements:** `mc.player` must be non-null. Returns silently if null.

**Side effects:** Saves a PNG screenshot to the Minecraft screenshots directory. The filename follows vanilla conventions (timestamp-based).

## See Also

| Item | Description |
|------|-------------|
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Base class for switch aliases |
| [SayAlias](../SayAlias.java/SayAlias.md) | Send chat (another communication alias) |
| [LogAlias](../LogAlias.java/LogAlias.md) | Write to mod log (another output alias) |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
