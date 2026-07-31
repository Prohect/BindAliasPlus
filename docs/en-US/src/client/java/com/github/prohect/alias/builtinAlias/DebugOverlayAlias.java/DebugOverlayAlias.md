# DebugOverlayAlias (src/client/java/com/github/prohect/alias/builtinAlias/DebugOverlayAlias.java)

Builtin alias that shows or hides the debug overlay (F3 screen) — FPS graph, coordinates, entity counts, chunk cache, etc. Inherits the `+name`/`-name` switch pattern from `BuiltinAliasWithBooleanArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.DebugOverlayAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.DebugOverlayAlias>
```

## Static Initializer

_None._

## Remarks

Registered as `"builtinDebugOverlay"`. Usage: `+debugOverlay` to show, `-debugOverlay` to hide. Unlike most other key-based aliases, the debug overlay is **not** driven through a vanilla `KeyBinding` — the F3 key is intercepted at the GLFW level before Minecraft's key system sees it. Therefore, this alias bypasses the key binding system entirely and uses `MinecraftClient.getInstance().getDebugHud()`: it calls `shouldShowDebugHud()` to check the current state, then `toggleDebugHud()` to flip it when the desired state differs.

Press events are suppressed when a text-input screen is open.

## See Also

| Item | Description |
|------|-------------|
| [AdvancementsAlias](../AdvancementsAlias.java/AdvancementsAlias.md) | Key-based toggle |
| [DebugHud](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a) | Vanilla class: debug pie, FPS graph, chunk borders, etc. (Yarn: `DebugHud`; Mojang: `DebugEntries`) |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
