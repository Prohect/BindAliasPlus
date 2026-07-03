# run method (src/client/java/com/github/prohect/alias/builtinAlias/WaitAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                  |
| ------ | -------- | ------------------------------------------------------------ |
| `args` | `String` | A positive integer representing the number of ticks to wait. |

## Remarks

Deprecated entry point that creates a simple `WaitAliasRecord` with an empty definition string. Parses `args` via `parseArgs()` (inherited from `BuiltinAliasWithIntegerArgs`) to set `flag` to the tick count.

If `flag > 0`, adds `new WaitAliasRecord(flag, "", false)` to `tasksWaiting`. If `flag <= 0`, logs an error.

**Side effects**: Adds a record to `tasksWaiting`. The empty definition means nothing happens after the wait — this overload is only useful as a pure delay.

**Callers**: Invoked by the alias dispatch system for legacy `wait\N` usage (no deferred definition). The overload `run(String, String)` is preferred for new usage.

**Error handling**: Logs error for non-positive tick counts.

Return value: Returns `this`.

## See Also

| Item                                                          | Description                        |
| ------------------------------------------------------------- | ---------------------------------- |
| [WaitAliasRecord](../WaitAliasRecord.java/WaitAliasRecord.md) | The record added to `tasksWaiting` |
| [tasksWaiting](tasksWaiting.md)                               | The global wait queue              |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
