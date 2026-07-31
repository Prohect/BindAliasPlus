# run method (src/client/java/com/github/prohect/alias/builtinAlias/WaitAlias.java)

Two overloads: a deprecated one-arg `run(String)` and the active two-arg `run(String, String)` used during chain execution.

## Syntax

```java
// Deprecated one-arg overload
public com.github.prohect.alias.builtinAlias.WaitAlias run(java.lang.String)

// Active two-arg overload
public com.github.prohect.alias.builtinAlias.WaitAlias run(java.lang.String, java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Tick count (validated >= 0). Supports variables via `VarAlias.resolveInt()`. |
| definition | String | The remainder of the alias chain to execute after waiting (only in two-arg overload) |

## Remarks

**One-arg `run(String args)` — Deprecated:**

1. Parse `args` via `parseArgs(args)` — sets `flag` to the tick count.
2. If `flag < 0`, log an error: ticks must be positive.
3. Does NOT actually schedule any wait — this is a leftover stub.

**Two-arg `run(String args, String definition)` — Active:**

1. Parse `args` via `parseArgs(args)`. Supports variable resolution.
2. If `flag > 0`: Create a `WaitAliasRecord(flag, definition, false)` and add to `tasksWaiting`.
3. If `flag == 0`: Execute immediately — `new UserAlias(definition).run("")`.
4. If `flag < 0`: Log an error.

**Task lifecycle:** Each tick, `MinecraftClientMixin` calls `WaitAliasRecord.tick()` on all entries in `tasksWaiting`. When the counter reaches 0, the record executes its definition and removes itself from the list.

**Return value:** `this` (fluent return).

**Side effects:** Schedules deferred alias chain execution. Does NOT block the game — other aliases and game logic continue to run during the wait period.

## See Also

| Item | Description |
|------|-------------|
| [WaitAlias](WaitAlias.md) | Class overview |
| [WaitAliasRecord](../WaitAliasRecord.java/WaitAliasRecord.md) | Deferred task record |
| [tasksWaiting](tasksWaiting.md) | The task list |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
