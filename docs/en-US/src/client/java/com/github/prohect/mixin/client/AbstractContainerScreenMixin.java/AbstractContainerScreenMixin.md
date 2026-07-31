# AbstractContainerScreenMixin (src/client/java/com/github/prohect/mixin/client/AbstractContainerScreenMixin.java)

## Syntax

```java
@Mixin(AbstractContainerScreen.class)
public abstract class com.github.prohect.mixin.client.AbstractContainerScreenMixin
```

## Static Initializer

_None._

## Remarks

Mixes into `net.minecraft.client.gui.screens.inventory.AbstractContainerScreen` to pin the hovered slot to the agent's slot 14 (player-inventory containerSlot 13) when [`FreeCursorAlias.freeCursor`](../../alias/builtinAlias/FreeCursorAlias.java/freeCursor.md) is active. Vanilla recomputes `hoveredSlot` from the OS mouse position every frame via `getHoveredSlot`; overriding that single method makes `+drop` and `swapSlot` operations deterministically target slot 14 regardless of where the host cursor rests. When freeCursor is off, hover behavior is unmodified.

## See Also

| Item | Description |
|------|-------------|
| [FreeCursorAlias](../../alias/builtinAlias/FreeCursorAlias.java/README.md) | Source of the `freeCursor` flag gating this behavior |
| [pinHoveredSlotTo14](pinHoveredSlotTo14.md) | The `@Inject` that overrides `getHoveredSlot` |
