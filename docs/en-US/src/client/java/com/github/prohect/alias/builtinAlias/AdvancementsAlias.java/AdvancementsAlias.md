# AdvancementsAlias (src/client/java/com/github/prohect/alias/builtinAlias/AdvancementsAlias.java)

Screen-control alias that opens the advancements/progress screen by pressing the advancements key (default: L).

## Syntax

```java
public class AdvancementsAlias extends BuiltinAliasWithBooleanArgs<AdvancementsAlias>
```

## Static Initializer

_None._

## Remarks

Usage: `+advancements` presses L, `-advancements` releases it.

The advancements screen opens on key *release* (Minecraft's `consumeClick()` pattern in `Gui.java`), so the typical workflow is:

1. `+advancements` — press L
2. `wait\2` — brief delay
3. `-advancements` — release L (screen opens)

Uses `KeyMapping.setDown()` and increments `clickCount` on press.

## See Also

| Item | Description |
|------|-------------|
| [run](run.md) | Entry point |

_Documented for Commit: [2003c5c](https://github.com/Prohect/BindAlias/tree/2003c5c648f2214e6b8c099a0db1ec96a130246b)_
