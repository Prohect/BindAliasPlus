# WaitAliasRecord (src/client/java/com/github/prohect/alias/builtinAlias/WaitAliasRecord.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.WaitAliasRecord
```

## Static Initializer

_None._

## Remarks

A deferred execution record that counts down game ticks and then executes a stored alias definition or reapplies a game key mapping.

**Purpose**: Implements the `wait` alias behavior — delay a subsequent alias action by a specified number of ticks. Two modes of operation:

- **Normal mode** (`reapplyToGameKeyMapping == false`): When the timer expires, creates a new `UserAlias(definition)` and calls `run("")`.
- **Reapply mode** (`reapplyToGameKeyMapping == true`): When the timer expires, looks up the alias by name and calls `reapplyToGameKeyMapping()` on it.

**Lifecycle**: Created by `WaitAlias.run()` and added to `WaitAlias.tasksWaiting`. Self-removes from the list when the timer expires.

**Thread safety**: Not thread-safe (render-thread only). The `tick()` method is called from the render thread's tick handler.

**Key collaborators**: Created by `[WaitAlias](WaitAlias.java/WaitAlias.md)`. Consumed by the tick handler. Interacts with `Alias.aliasesWithArgs` and `Alias.aliasesWithArgs_notSuggested` for reapply lookups.

## See Also

| Item                                        | Description                      |
| ------------------------------------------- | -------------------------------- |
| [WaitAlias](../WaitAlias.java/WaitAlias.md) | Creates instances of this record |
| [tick](tick.md)                             | Per-tick countdown and execution |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
