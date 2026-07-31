# runInternal method (src/client/java/com/github/prohect/alias/UserAlias.java)

## Syntax

```java
public void runInternal(java.util.List<com.github.prohect.alias.UserAlias>)
```

## Parameters

| Name                    | Type              | Description                                                                                                                                   |
| ----------------------- | ----------------- | --------------------------------------------------------------------------------------------------------------------------------------------- |
| `userAliasesCallChains` | `List<UserAlias>` | The call chain of `UserAlias` instances. The first element must be the root alias that initiated the chain. Used for infinite-loop detection. |

## Remarks

Recursive dispatch with infinite-loop detection and enhanced `WaitAlias` handling.

Called by `run()` when a nested `UserAlias` is encountered, and recursively
by itself for deeper nesting.

Algorithm (similar to `run()` with two key differences):

1. Call `decodeArgs2Alias(this.args)` to populate the `aliases` deque.
2. Dequeue entries one by one. For each entry:
   - Look up in registries with the same priority order as `run()`.
   - **If not found**: skip.
   - **If `UserAlias`**:
     - Check if the nested alias is already in `userAliasesCallChains`.
     - If yes: log a warning about infinite loop and skip.
     - If no: recursively call `userAlias.runInternal()` with the chain
       extended by that alias.
   - **If `WaitAlias`**: Package remaining entries from both the current
     queue AND the root alias's queue into a deferred definition string.
     Drain the root alias's queue (the first element of `userAliasesCallChains`)
     completely. Call `waitAlias.run(aliasRecord.args(), definitionLeft)` and return.
   - **Otherwise**: same screen-blacklist logic as `run()`.

The `WaitAlias` handling in `runInternal()` differs from `run()` by also
draining the root alias's remaining queue entries. This ensures that
deferred executions within a call chain include all pending work, not
just the current alias's queue.

## See Also

| Item                                                  | Description                                          |
| ----------------------------------------------------- | ---------------------------------------------------- |
| [run](run.md)                                         | Primary dispatch (calls this for nested UserAliases) |
| [decodeArgs2Alias](decodeArgs2Alias.md)               | Parses definitions before dispatch                   |
| [WaitAlias](builtinAlias/WaitAlias.java/WaitAlias.md) | Special-cased with root-queue draining               |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
