# tickDrop method (src/client/java/com/github/prohect/alias/builtinAlias/DropAlias.java)

Called every client tick from `MinecraftClientMixin` while `flag` is true. Drives continuous dropping after an initial delay matching the OS key-repeat gap.

## Syntax

```java
public void tickDrop()
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| _(none)_ | | |

## Remarks

1. If `flag` is false, returns immediately (not currently held).
2. Increments `ticksHeld`. If `ticksHeld <= INITIAL_DELAY_TICKS` (3 ticks), returns — the initial delay matches vanilla's OS key-repeat behavior where the OS waits ~250ms before starting key repeats.
3. After the delay, checks the current screen:
   - **Container screen:** If the hovered slot has an item, calls `slotClicked(hoveredSlot, hoveredSlot.index, button, ContainerInput.THROW)` with button=1 (Ctrl held) or button=0 (single item).
   - **3D game (screen == null):** Increments `mc.options.keyDrop.clickCount++` to fire another drop event.
   - **Other screens (not containers, not null):** No action — key drops only make sense in container screens and the 3D world.

The `MinecraftClientMixin` calls `tickDrop()` unconditionally on every client tick, with the DropAlias instance retrieved via the static alias registry.

## See Also

| Item | Description |
|------|-------------|
| [run](run.md) | Initial press/release handler |
| [MinecraftClientMixin](../../../mixin/MinecraftClientMixin.java/MinecraftClientMixin.md) | Calls `tickDrop()` each client tick |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
