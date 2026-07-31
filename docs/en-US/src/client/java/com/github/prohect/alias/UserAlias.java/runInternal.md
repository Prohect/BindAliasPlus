# runInternal method (src/client/java/com/github/prohect/alias/UserAlias.java)

## Syntax

```java
public void runInternal(List<UserAlias> userAliasesCallChains)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `userAliasesCallChains` | `List<UserAlias>` | The accumulated call chain — first element must be the root `UserAlias` of the chain. Used to detect infinite recursion. |

## Remarks

The recursive execution variant called when a `UserAlias` is nested inside another `UserAlias`'s chain. Same dispatch logic as `run()`, with two key differences:

1. **Infinite loop detection**: Before executing a nested `UserAlias`, checks if it is already in `userAliasesCallChains`. If so, logs a warning (`"infinite loop detected checking UserAliasesCallChains"`) and skips it.
2. **WaitAlias unwinding**: When a `WaitAlias` is encountered, it not only collects remaining items from this alias's queue but also **unwinds the entire call chain** — collecting leftover items from every parent alias in `userAliasesCallChains` (iterating in reverse, from innermost to root). This ensures the deferred continuation captures the full remaining chain, not just the current sub-chain.

**How WaitAlias unwinding works**: After collecting this alias's remaining queue items into `definitionLeft`, the method iterates `userAliasesCallChains` in reverse order. For each parent alias, it drains that alias's remaining `aliases` queue into `definitionLeft`, reconstructing the chain with the appropriate dividers. This ensures that when the wait timer fires, the rest of the original chain (at all nesting levels) executes correctly.

## See Also

| Item | Description |
|------|-------------|
| [run](run.md) | The non-recursive entry point — delegates to this for nested UserAliases |
| [WaitAlias](builtinAlias/WaitAlias.java/WaitAlias.md) | Causes the chain unwinding described above |
| [AliasRecord](AliasRecord.java/AliasRecord.md) | The queue items being drained during unwinding |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
