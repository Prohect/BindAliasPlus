# tickDrop method (src/client/java/com/github/prohect/alias/builtinAlias/DropAlias.java)

## Syntax

```java
public void tickDrop()
```

## Parameters

| Name     | Type | Description |
| -------- | ---- | ----------- |
| _(none)_ |      |             |

## Remarks

Called every client tick from `MinecraftClientMixin` while `flag` is `true`. Drives continuous dropping after an initial delay of `INITIAL_DELAY_TICKS` (3 ticks), matching the OS key-repeat gap that vanilla relies on.

**Algorithm**:

1. If `flag` is false, return immediately.
2. Increment `ticksHeld`.
3. If `ticksHeld <= INITIAL_DELAY_TICKS` (3), return — don't drop yet (the first drop already fired in `run()`).
4. If in a container screen (`AbstractContainerScreen`): drop the hovered item via `slotClicked(…, THROW)`, respecting Ctrl for whole-stack drops.
5. If no screen is open (3D game): increment `keyDrop.clickCount++` to trigger a vanilla drop.

**Side effects**: Sends slot-click packets (container) or triggers vanilla drop processing (3D). Mutates `ticksHeld`.

**Callers**: Called from `MinecraftClientMixin` in the client tick loop.

## See Also

| Item          | Description                              |
| ------------- | ---------------------------------------- |
| [run](run.md) | Press/release lifecycle that sets `flag` |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
