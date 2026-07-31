# tick method (src/client/java/com/github/prohect/alias/builtinAlias/WaitAliasRecord.java)

Decrements the tick counter. When it reaches 0, executes the deferred definition and removes this record from the waiting list.

## Syntax

```java
public int tick()
```

## Remarks

**Algorithm:**

1. Decrement `ticks`.
2. If `ticks == 0`:
   a. If `reapplyToGameKeyMapping` is true:
      - Look up `definition` in `aliasesWithArgs` and `aliasesWithArgs_notSuggested`.
      - If found and it's a `BuiltinAliasWithBooleanArgs`, call `reapplyToGameKeyMapping()`.
   b. Otherwise:
      - Create `new UserAlias(definition)` and call `run("")`.
   c. Remove `this` from `WaitAlias.tasksWaiting`.
   d. Return 1 (task performed).
3. Return 0 (task still waiting).

**Return value:** 1 if the deferred definition was executed, 0 if still waiting.

**Concurrent modification:** Since this method calls `tasksWaiting.remove(this)` during iteration, the calling code (in `MinecraftClientMixin`) must handle concurrent modification safely.

**Side effects:** When ticks reach 0, executes the deferred alias chain or re-applies a held key. This can trigger any range of side effects depending on what the definition contains.

## See Also

| Item | Description |
|------|-------------|
| [WaitAliasRecord](WaitAliasRecord.md) | Class overview |
| [WaitAlias](../WaitAlias.java/run.md) | Creates instances of this record |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
