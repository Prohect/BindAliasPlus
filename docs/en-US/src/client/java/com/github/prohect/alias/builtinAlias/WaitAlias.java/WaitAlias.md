# WaitAlias (src/client/java/com/github/prohect/alias/builtinAlias/WaitAlias.java)

Builtin alias that defers execution of an alias chain by a specified number of client ticks. Extends `BuiltinAliasWithIntegerArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.WaitAlias extends com.github.prohect.alias.BuiltinAliasWithIntegerArgs<com.github.prohect.alias.builtinAlias.WaitAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `wait` — usage: `wait\N` where N is the number of ticks to wait before executing the rest of the chain.

**Two run() overloads:**
1. `run(String args)` — **Deprecated.** Parses the tick count via `parseArgs(args)` but does NOTHING with it (only validates N >= 0). This is a leftover from an earlier design.
2. `run(String args, String definition)` — **Active.** The actual implementation used by `UserAlias` during chain execution. The `definition` parameter contains the remainder of the alias chain to execute after the wait.

**Behavior:**
- `wait\N` with N > 0: Creates a `WaitAliasRecord` and adds it to `tasksWaiting` list. Each tick, `MinecraftClientMixin` decrements all waiting tasks' counters. When a counter reaches 0, the deferred definition is executed.
- `wait\0`: Executes the definition immediately (no waiting). A NOP in terms of timing.
- `wait\N` with N < 0: Logs an error — negative tick values are invalid.

**Task execution:** When a wait task expires, `WaitAliasRecord.tick()` creates a new `UserAlias(definition)` and calls `run("")` on it, effectively resuming the chain. If `reapplyToGameKeyMapping` is true, it instead calls `reapplyToGameKeyMapping()` on the corresponding builtin alias.

**tickPrefix consideration:** The wait alias is one of the few that can defer execution across multiple game ticks. This means state captured before the wait (e.g., via `getFullState`) may be stale by the time the deferred chain executes.

## See Also

| Item | Description |
|------|-------------|
| [WaitAliasRecord](WaitAliasRecord.md) | Record holding deferred task info |
| [MinecraftClientMixin](../../../mixin/MinecraftClientMixin.java/MinecraftClientMixin.md) | Tick driver that decrements wait counters |
| [UserAlias](../../UserAlias.java/UserAlias.md) | Chain executor that calls `run(args, definition)` |
| [BuiltinAliasWithIntegerArgs](../../BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) | Base class for integer-arg aliases |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
