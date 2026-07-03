# run method (src/client/java/com/github/prohect/alias/UserAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                                                                                              |
| ------ | -------- | ---------------------------------------------------------------------------------------------------------------------------------------- |
| `args` | `String` | Trigger arguments from the key-binding system. Not used in this implementation — the alias parses its own `this.args` definition string. |

## Remarks

Primary execution entry point for user-defined aliases.

Algorithm:

1. Call `decodeArgs2Alias(this.args)` to populate the `aliases` deque.
2. Dequeue entries one by one. For each entry:
   - Look up `aliasRecord.aliasName()` in the registries in order:
     `aliasesWithoutArgs` → `aliasesWithoutArgs_notSuggested` →
     `aliasesWithArgs_notSuggested` → `aliasesWithArgs`.
   - **If not found**: skip (no-op).
   - **If `UserAlias`**: delegate to `runInternal()` with a call-chain list
     containing `this`.
   - **If `WaitAlias`**: package all remaining queue entries into a deferred
     definition string, call `waitAlias.run(aliasRecord.args(), definitionLeft)`,
     and return immediately.
   - **Otherwise (default)**: if the alias is a `BuiltinAliasWithArgs` on the
     screen blacklist, check screen state:
     - No screen open → execute normally.
     - Screen open and args is `"0"` → execute (allow key release).
     - Screen open and args is not `"0"` → skip (suppressed).
     - If not blacklisted, execute normally with `alias.run(aliasRecord.args())`.

The `args` parameter from the method signature is unused — this implementation
always parses `this.args` (the definition string stored at construction).

## Return value

Returns `this` for fluent chaining.

## See Also

| Item                                                        | Description                                                            |
| ----------------------------------------------------------- | ---------------------------------------------------------------------- |
| [runInternal](runInternal.md)                               | Recursive dispatch with loop detection (called for nested UserAliases) |
| [decodeArgs2Alias](decodeArgs2Alias.md)                     | Parses definitions before dispatch                                     |
| [Alias.blackList4Screen](../Alias.java/blackList4Screen.md) | Screen blacklist checked during dispatch                               |
| [WaitAlias](builtinAlias/WaitAlias.java/WaitAlias.md)       | Special-cased alias that short-circuits dispatch                       |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
