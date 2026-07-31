# tick method (src/client/java/com/github/prohect/alias/builtinAlias/WaitAliasRecord.java)

## Syntax

```java
public int tick()
```

## Parameters

| Name     | Type | Description |
| -------- | ---- | ----------- |
| _(none)_ |      |             |

## Remarks

Called every game tick to count down the delay timer and execute the deferred action when it expires.

**Algorithm**:

1. If `ticks > 0`: decrement `ticks` and return `0` (still waiting).
2. If `ticks <= 0` and `reapplyToGameKeyMapping` is true:
   - Look up `definition` (the alias name) in `Alias.aliasesWithArgs` and `Alias.aliasesWithArgs_notSuggested`.
   - If found and the alias is a `BuiltinAliasWithBooleanArgs`, call `alias.reapplyToGameKeyMapping()`.
   - Remove `this` from `WaitAlias.tasksWaiting` and return `1`.
3. If `ticks <= 0` and `reapplyToGameKeyMapping` is false:
   - Create a new `UserAlias(definition)` and call `run("")` to execute the deferred alias chain.
   - Remove `this` from `WaitAlias.tasksWaiting` and return `1`.

**Side effects**: Creates and executes a `UserAlias` (normal mode) or calls `reapplyToGameKeyMapping()` (reapply mode). Self-removes from `WaitAlias.tasksWaiting`.

**Callers**: Called from the tick handler (likely `MinecraftClientMixin`) for each record in `WaitAlias.tasksWaiting`.

Return value: `1` if the task was performed (timer expired), `0` if still waiting.

## See Also

| Item                                                        | Description                      |
| ----------------------------------------------------------- | -------------------------------- |
| [WaitAlias.tasksWaiting](../WaitAlias.java/tasksWaiting.md) | The queue this record belongs to |
| [WaitAlias](../WaitAlias.java/WaitAlias.md)                 | Creates instances of this record |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
