# tick method (src/client/java/com/github/prohect/mixin/client/MinecraftClientMixin.java)

## Syntax

```java
private void tick(org.spongepowered.asm.mixin.injection.callback.CallbackInfo)
```

## Parameters

| Name | Type           | Description                   |
| ---- | -------------- | ----------------------------- |
| `ci` | `CallbackInfo` | Mixin callback info (unused). |

## Remarks

Drives per-tick background tasks: deferred alias execution (WaitAlias) and continuous item dropping (DropAlias).

Algorithm:

1. **WaitAlias**: iterates `WaitAlias.tasksWaiting` list. For each `WaitAliasRecord`, calls `tick()` which decrements the countdown. If `tick()` returns non-zero (task still active), the size counter is decremented to skip re-processing already-completed tasks.

2. **DropAlias**: looks up the `DropAlias` instance from `Alias.aliasesWithArgs_notSuggested.get("builtinDrop")`. If found and it's a `DropAlias` instance, calls `dropAlias.tickDrop()` which:
   - In a container screen: clicks the hovered slot with `THROW` action.
   - In 3D world: increments `keyDrop.clickCount` to trigger a drop.

Side effects: dispatches queued WaitAlias tasks and triggers item drops. Both can produce network packets and inventory changes.

Callers: called by the Mixin framework at the start of every `Minecraft.tick()`, approximately 20 times per second (at 20 TPS).

## See Also

| Item                                                                              | Description                    |
| --------------------------------------------------------------------------------- | ------------------------------ |
| [MinecraftClientMixin](MinecraftClientMixin.md)                                   | Owning mixin class             |
| [WaitAlias](../../../alias/builtinAlias/WaitAlias.java/WaitAlias.md)              | Deferred execution driven here |
| [DropAlias.tickDrop](../../../alias/builtinAlias/DropAlias.java/DropAlias.md)     | Continuous drop logic          |
| [WaitAlias.tasksWaiting](../../../alias/builtinAlias/WaitAlias.java/WaitAlias.md) | The task list consumed here    |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
