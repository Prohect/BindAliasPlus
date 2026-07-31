# HandledScreenMixin (src/client/java/com/github/prohect/mixin/client/HandledScreenMixin.java)

## Syntax

```java
@Mixin(HandledScreen.class)
public abstract class com.github.prohect.mixin.client.HandledScreenMixin
```

## Static Initializer

_None._

## Remarks

Mixes into `net.minecraft.client.gui.screen.ingame.HandledScreen` to override the hovered-slot calculation when `FreeCursorAlias.freeCursor` is active. Vanilla recomputes `focusedSlot` from the OS mouse position every frame (`renderMain` → `getSlotAt`); by injecting at `RETURN` of `getSlotAt(double, double)` and replacing the return value with the player inventory slot whose `index` equals `FORCED_HOVER_INDEX` (13, 0-based — maps to container slot 14), the free host cursor becomes irrelevant to hover. This makes `+drop` and swap operations deterministically target slot 14 regardless of where the OS cursor rests. When `freeCursor` is off (normal grabbed-cursor play), the injection returns early and hover behavior is untouched.

The constant `FORCED_HOVER_INDEX = 13` is chosen because container slots beyond the container's own slots are typically the player inventory area, and index 13 (the 14th 0-based index) corresponds to a predictable "agent slot" in most container screen layouts.

## See Also

| Item | Description |
|------|-------------|
| [pinFocusedSlotTo14](pinFocusedSlotTo14.md) | The `@Inject` that replaces the hovered slot |
| [FreeCursorAlias.freeCursor](../../../alias/builtinAlias/FreeCursorAlias.java/freeCursor.md) | The flag gating this injection |
| [MouseMixin](../MouseMixin.java/README.md) | Suppresses OS cursor grab during freeCursor |

*Documented for Commit: [9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0](https://github.com/Prohect/BindAlias/tree/9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0)*
